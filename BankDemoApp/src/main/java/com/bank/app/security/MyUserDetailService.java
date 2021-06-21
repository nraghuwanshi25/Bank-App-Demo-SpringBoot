package com.bank.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		 List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//
//	        list.add(new SimpleGrantedAuthority("ROLE_USER"));
//	        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	      
	
		return new User("nilesh","nilesh",null);
	}

}
