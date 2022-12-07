package com.corvanta.jwt.producer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * User detail is hard-coded for JWT Security POC
 * However, it can be retrieved form database
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		System.out.println("-->>MyUserDetailsService userName:"+userName);
		
		
			List<SimpleGrantedAuthority> authorizationList = new ArrayList<>();
			authorizationList.add(new SimpleGrantedAuthority("ADMIN"));
			authorizationList.add(new SimpleGrantedAuthority("USER"));
			
		return new User(userName, "demo", authorizationList);
	}


}
