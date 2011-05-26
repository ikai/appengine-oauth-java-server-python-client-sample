package com.ikai.oauthprovider;

import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.oauth.OAuthService;
import com.google.appengine.api.oauth.OAuthServiceFactory;
import com.google.appengine.api.users.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ProtectedServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	User user = null;
	try {
	    OAuthService oauth = OAuthServiceFactory.getOAuthService();
	    user = oauth.getCurrentUser();
	    resp.getWriter().println("Authenticated: " + user.getEmail());
	} catch (OAuthRequestException e) {
	    resp.getWriter().println("Not authenticated: " + e.getMessage());
	}
    }
    
}
