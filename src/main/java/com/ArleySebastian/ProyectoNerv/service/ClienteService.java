package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import com.ArleySebastian.ProyectoNerv.entity.Cliente;


public interface ClienteService {
	
public Iterable<Cliente> getAllclientes();
	
	int guardar(Cliente c);

	public Cliente buscarPorId(Long id);

	public void eliminar(Long id);

 
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public Cliente save(Cliente cliente);
	public void delete(Long id);
}
