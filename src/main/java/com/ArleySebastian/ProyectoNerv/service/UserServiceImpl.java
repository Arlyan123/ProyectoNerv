package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Usuarios;
import com.ArleySebastian.ProyectoNerv.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	public Iterable<Usuarios>getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public Optional<Usuarios>listarId(Long id){
		return userRepository.findById(id);
	}
	
	@Override
	public int save(Usuarios u) {
    	int res=0;
    	Usuarios Client=userRepository.save(u);
    	if(!Client.equals(null)) {
    		res=1;
    	}
    			return res;
    }
	@Override
	public Usuarios buscarPorId(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true) // ReadOnly si es una consulta select;
	public List<Usuarios> findAll() {	
		return (List<Usuarios>)userRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true) // ReadOnly si es una consulta select;
	public Usuarios findByNombreEndsWith(String username, String password) {
		return userRepository.findByNombreEndsWith(username, password); // trolita akj
	}

	@Override
	@Transactional
	public Usuarios findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}	

	@Override// Eliminar
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true) // ReadOnly si es una consulta select;
	public Usuarios listarPorNombre(String nombre) {
		return userRepository.listarPorNombre(nombre);
	}

	/*@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Override
	public Usuarios registrar(Usuarios u) {
    u.setPassword( passEncoder.encode(u.getPassword()));
		return userRepository.save(u);
	}
		
*/

}
