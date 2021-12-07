package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Role;
import com.ArleySebastian.ProyectoNerv.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> listarRoles() {
		
		return (List<Role>) roleRepository.findAll();
	}

	 @Override
		public int guardar(Role r) {
	    	int res=0;
	    	Role rr=roleRepository.save(r);
	    	if(!rr.equals(null)) {
	    		res=1;
	    	}
	    			return res;
	    }

	 @Override
		@Transactional
		public Role save(Role role) {
			return roleRepository.save(role);
		}
	
	

}
