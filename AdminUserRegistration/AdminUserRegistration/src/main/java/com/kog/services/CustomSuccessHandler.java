package com.kog.services;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		var authororities=authentication.getAuthorities();
		var roles=authororities.stream().map(role->role.getAuthority()).findFirst();
		if(roles.orElse("").equals("ADMIN"))
		{
			response.sendRedirect("/adminPage");
		}
		else if (roles.orElse("").equals("USER")) {
			response.sendRedirect("/userPage");
		}
		else
		{
			response.sendRedirect("/error");
		}
		
	}
	
}
