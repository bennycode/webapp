package com.welovecoding.gauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class GLoginServlet extends AbstractAuthorizationCodeServlet {

    @Inject
    private GoogleConnector googleConnector;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
    }

    @Override
    protected String getRedirectUri(HttpServletRequest request) {
        return googleConnector.getRedirectUri();
    }

    @Override
    protected String getUserId(HttpServletRequest request) {
        return request.getSession(true).getId();
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() {
        return googleConnector.getFlow();
    }
}
