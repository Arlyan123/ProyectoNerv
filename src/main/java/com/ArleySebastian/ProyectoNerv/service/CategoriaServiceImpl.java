package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Categoria;
import com.ArleySebastian.ProyectoNerv.repository.CategoriaRepository;


@Service
public class CategoriaServiceImpl implements CategoriaService{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return (List<Categoria>)categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Categoria findById(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Categoria save(Categoria Categoria) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(Categoria);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		categoriaRepository.deleteById(id);
		
	}
}
