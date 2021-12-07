package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;
import java.util.Optional;


import com.ArleySebastian.ProyectoNerv.entity.Usuarios;


public interface UserService {
	public Iterable<Usuarios> getAllUsers();

	int save(Usuarios u);

	Optional<Usuarios> listarId(Long id);
	
	public void eliminar(Long id);

	//public Usuarios registrar(Usuarios u);

	public Usuarios buscarPorId(Long id);
	
	public List<Usuarios> findAll();
	public Usuarios findByNombreEndsWith(String usuario, String password);
	public Usuarios listarPorNombre(String nombre);
	public Usuarios findById(Long id);
	public void delete(Long id);




}
