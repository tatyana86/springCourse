package ru.krivonogova.springcourse.securityapp.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ru.krivonogova.springcourse.securityapp.services.PersonDetailsService;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

	private final PersonDetailsService personDetailsService;
	
	public AuthProviderImpl(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		
		UserDetails personDetails = personDetailsService.loadUserByUsername(username);
		
		String password = authentication.getCredentials().toString();
		
		if(!password.equals(personDetails.getPassword())) {
			throw new BadCredentialsException("Incorrect password");
		}
		
		return new UsernamePasswordAuthenticationToken(personDetails, password, 
				Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	

}
