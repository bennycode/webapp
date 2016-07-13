package com.welovecoding.api.v1.user;

import com.codahale.metrics.annotation.Timed;
import com.welovecoding.api.v1.user.dto.KeyAndPasswordDTO;
import com.welovecoding.api.v1.user.dto.UserDTO;
import com.welovecoding.config.security.util.SecurityUtils;
import com.welovecoding.data.mail.MailService;
import com.welovecoding.data.user.entity.Password;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.repository.UserRepository;
import com.welovecoding.data.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping("/api/v1/account")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private MailService mailService;

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Timed
    public ResponseEntity<?> registerAccount(@Valid UserDTO userDTO, HttpServletRequest request) {
        log.info("UserDTO: " + userDTO);
        return userRepository.findOneByLogin(userDTO.getLogin())
                .map(user -> new ResponseEntity<>("login already in use", HttpStatus.BAD_REQUEST))
                .orElseGet(() -> userRepository.findOneByEmail(userDTO.getEmail())
                        .map(user -> new ResponseEntity<>("e-mail address already in use", HttpStatus.BAD_REQUEST))
                        .orElseGet(() -> {
                            User user = userService.createUserInformation(userDTO.getLogin(), new Password(userDTO.
                                    getPassword()),
                                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail().toLowerCase(),
                                    userDTO.getLangKey());
                            String baseUrl = request.getScheme()
                                    + "://"
                                    + request.getServerName()
                                    + ":"
                                    + request.getServerPort();

                            mailService.sendActivationEmail(user, baseUrl);
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        })
                );
    }

    @RequestMapping(value = "/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<String> activateAccount(@RequestParam(value = "key") String key) {
        return Optional.ofNullable(userService.activateRegistration(key))
                .map(user -> new ResponseEntity<String>(HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserDTO> getAccount() {
        return Optional.ofNullable(userService.getUserWithAuthorities())
                .map(user -> new ResponseEntity<>(new UserDTO(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<String> saveAccount(@RequestBody UserDTO userDTO) {
        return userRepository
                .findOneByLogin(userDTO.getLogin())
                .filter(u -> u.getLogin().equals(SecurityUtils.getCurrentUserLogin()))
                .map(u -> {
                    userService.updateUserInformation(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
                            userDTO.getLangKey());
                    return new ResponseEntity<String>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/change_password",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> changePassword(@RequestBody String password) {
        if (!checkPasswordLength(password)) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
        }
        userService.changePassword(new Password(password));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/reset_password/init",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<?> requestPasswordReset(@RequestBody String mail, HttpServletRequest request) {
        return userService.requestPasswordReset(mail)
                .map(user -> {
                    String baseUrl = request.getScheme()
                            + "://"
                            + request.getServerName()
                            + ":"
                            + request.getServerPort();
                    mailService.sendPasswordResetMail(user, baseUrl);
                    return new ResponseEntity<>("e-mail was sent", HttpStatus.OK);
                }).orElse(new ResponseEntity<>("e-mail address not registered", HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/reset_password/finish",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<String> finishPasswordReset(@RequestBody KeyAndPasswordDTO keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
        }
        return userService.completePasswordReset(new Password(keyAndPassword.getNewPassword()), keyAndPassword.getKey())
                .map(user -> new ResponseEntity<String>(HttpStatus.OK)).orElse(new ResponseEntity<>(
                HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private boolean checkPasswordLength(String password) {
        return (!StringUtils.isEmpty(password)
                && password.length() >= UserDTO.PASSWORD_MIN_LENGTH
                && password.length() <= UserDTO.PASSWORD_MAX_LENGTH);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        StringBuilder builder = new StringBuilder();
        log.error(e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodException = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = methodException.getBindingResult();
            if (bindingResult != null && bindingResult.hasErrors()) {
                List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
                for (FieldError fieldError : fieldErrorList) {
                    builder.append(fieldError.getDefaultMessage()).append("\n");
                }
            }
        }
        return builder.toString();
    }
}
