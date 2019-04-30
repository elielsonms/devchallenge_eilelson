package com.wexinc.interview.challenge1.filters;

import com.google.inject.Inject;
import com.wexinc.interview.challenge1.models.AuthorizationToken;
import com.wexinc.interview.challenge1.services.AuthManager;
import com.wexinc.interview.challenge1.util.Path;

import spark.Filter;
import spark.Request;
import spark.Response;

public class AuthFilter implements Filter{
	private AuthManager authManager;
	@Inject
	public AuthFilter(final AuthManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		switch (request.requestMethod()) {
			case "GET": 
				if(Path.getEnpointsAuthorized.contains(request.uri()))
					handleValidateAndRotateToken(request, response);
				break;
			case "POST": 
				if(Path.postEnpointsAuthorized.contains(request.uri()))
					handleValidateAndRotateToken(request, response);
				break;
		}
		
	}
	
	public void handleValidateAndRotateToken(Request request, Response response) throws Exception {
		final String authToken = request.headers("X-WEX-AuthToken");
		final AuthorizationToken token = authManager.verifyAuthToken(authToken);
		final AuthorizationToken newToken =  authManager.rotateAuthToken(token);
		response.header("X-WEX-AuthToken",newToken.getAuthToken());
		
		request.attribute("userId", newToken.getUserId());
	}

}
