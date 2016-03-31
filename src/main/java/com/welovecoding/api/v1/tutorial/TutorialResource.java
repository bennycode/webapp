package com.welovecoding.api.v1.tutorial;

import com.welovecoding.data.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.welovecoding.api.v1.tutorial.TutorialMapper.entityToDto;

@RestController
@RequestMapping("/api/v1/categories/{categorySlug}")
//@Produces("application/json")
public class TutorialResource {

    private final TutorialService tutorialService;

    @Autowired
    TutorialResource(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @RequestMapping(value = "/{tutorialSlug}", method = RequestMethod.GET)
    public TutorialDTO findCategoryBySlug(@PathVariable String tutorialSlug) {
        return entityToDto(tutorialService.findBySlug(tutorialSlug), 2);
    }

}
