package com.welovecoding.gauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = {GLoginServlet.GOOGLE_PLUS_LOGIN})
public class GLoginServlet extends AbstractAuthorizationCodeServlet {
    
    public static final String GOOGLE_PLUS_LOGIN = "/google-plus-login";

    @Inject
    private GoogleConnector googleConnector;

    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
    }

    @Override
    protected String getRedirectUri(HttpServletRequest request) {
        return getBaseUrl(request) + GLoginCallbackServlet.GOOGLE_PLUS_LOGIN_CALLBACK;
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