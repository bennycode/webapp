package com.welovecoding.gauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.model.Person;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleConnector {
    
    private static final Logger LOG = Logger.getLogger(GoogleConnector.class.getName());
    
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Arrays.asList(
            PlusScopes.USERINFO_EMAIL,
            PlusScopes.USERINFO_PROFILE,
            YouTubeScopes.YOUTUBE_READONLY
    );
    
    private final String GOOGLE_PROJECT_ID;
    private final String GOOGLE_CLIENT_ID;
    private final String GOOGLE_CLIENT_SECRET;
    private final String GOOGLE_REDIRECT_URI;

    static {
        LOG.setLevel(Level.INFO);
    }

    public GoogleConnector(String googleProjectId, String googleClientId, String googleClientSecret, String googleRedirectUri) {
        this.GOOGLE_PROJECT_ID = Objects.requireNonNull(googleProjectId, "Google Project ID is not set");
        this.GOOGLE_CLIENT_ID = Objects.requireNonNull(googleClientId, "Google Client ID is not set");
        this.GOOGLE_CLIENT_SECRET = Objects.requireNonNull(googleClientSecret, "Google Client Secret is not set");
        this.GOOGLE_REDIRECT_URI = Objects.requireNonNull(googleRedirectUri, "Google Redirect URI is not set");
    }

    public GoogleTokenResponse codeToToken(String code)
            throws IOException {
        return new GoogleAuthorizationCodeTokenRequest(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                GOOGLE_CLIENT_ID,
                GOOGLE_CLIENT_SECRET,
                code,
                GOOGLE_REDIRECT_URI
        ).execute();
    }

    public AuthorizationCodeFlow getFlow() {
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                GOOGLE_CLIENT_ID,
                GOOGLE_CLIENT_SECRET,
                SCOPES
        ).build();
    }

    public Plus getPlusClient(String accessToken) {
        return new Plus.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GoogleCredential().setAccessToken(accessToken)
        ).setApplicationName(GOOGLE_PROJECT_ID).build();
    }

    public YouTube getYouTubeClient(String accessToken) {
        return new YouTube.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GoogleCredential().setAccessToken(accessToken)
        ).setApplicationName(GOOGLE_PROJECT_ID).build();
    }
    
    /**
     * @see http://www.programcreek.com/java-api-examples/index.php?api=com.google.api.services.plus.Plus
     * @param person
     * @return
     */
    public String getEmailAddress(Person person) {
        String emailAddress = null;

        List<Person.Emails> emails = person.getEmails();
        for (Person.Emails email : emails) {
            if (email.getType().equals("account")) {
                emailAddress = email.getValue();
            }
        }

        if (emailAddress == null) {
            throw new RuntimeException("Account email not in email list");
        }

        return emailAddress;
    }
    // @todo Outsource functionality to "PlusService" EJB
    public Person getSelfUser(Plus client) throws IOException {
        return client.people().get("me").execute();
    }

}
