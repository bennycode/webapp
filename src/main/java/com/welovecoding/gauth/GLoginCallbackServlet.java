package com.welovecoding.gauth;

import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class GLoginCallbackServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GLoginCallbackServlet.class.getName());

    @Inject
    private GoogleConnector googleConnector;
    private Plus plusClient;
    private Person user;
    private String accessToken;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getParameter("error") != null) {
            onError(request, response, request.getParameter("error"));
        } else if (null == request.getParameter("code")) {
            onError(request, response, "The 'code' URL parameter is missing");
        } else {
            try {
                accessToken = googleConnector.codeToToken(request.getParameter("code")).getAccessToken();
                plusClient = googleConnector.getPlusClient(accessToken);
                user = googleConnector.getSelfUser(plusClient);
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
                onError(request, response, ex.getMessage());
            }
            onSuccess(request, response);
        }
    }

    private void onError(HttpServletRequest request, HttpServletResponse response, String reason) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Google Plus Login Error</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Google Plus Login Error</h1>");
            out.println(String.format("<p>Reason: %s</p>", reason));
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void onSuccess(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (user != null) {
                out.println(String.format("<h1>Hello, %s!</h1>", googleConnector.getEmailAddress(user)));
            }
            out.println(String.format("<p>Access token: </p><pre>%s</pre>", accessToken));
            out.println("</body>");
            out.println("</html>");
        }
    }
}
