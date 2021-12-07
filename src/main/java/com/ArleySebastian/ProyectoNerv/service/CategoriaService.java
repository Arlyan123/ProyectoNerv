package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import com.ArleySebastian.ProyectoNerv.entity.Categoria;



public interface CategoriaService {
	public List<Categoria> findAll();
	public Categoria findById(Long id);
	public Categoria save(Categoria Categoria);
	public void delete(Long id);
}
