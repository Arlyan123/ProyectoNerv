package com.ArleySebastian.ProyectoNerv.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.*;

import com.ArleySebastian.ProyectoNerv.entity.Role;
import com.ArleySebastian.ProyectoNerv.entity.Usuarios;
import com.ArleySebastian.ProyectoNerv.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios u = userRepository.findByUsername(username);
		
		if(u == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		List<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
		
		for(Role rol: u.getRoles()) {
			role.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		return new User(u.getUsername(), u.getPassword(), role);
	}
	
	

	}



