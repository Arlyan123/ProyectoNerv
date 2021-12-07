package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;
import java.util.Optional;

import com.ArleySebastian.ProyectoNerv.entity.Plato;


public interface PlatoService {

	public Iterable<Plato> getAllzapatos();
	
	int guardar(Plato z);

	public Plato buscarPorId(Long id);

	public void eliminar(Long id);

	public List<Plato> findAll();
	public Plato findById(Long id);
	Optional<Plato> listarId(Long id);
	public Plato save(Plato PLato);
	public void delete(Long id);
	public void actualizarStock(int cantidad, Long id);
	public void actualizarEstado(Long id);
}
