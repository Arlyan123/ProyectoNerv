package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Pago;
import com.ArleySebastian.ProyectoNerv.repository.PagoRepository;


@Service
public class PagoServiceImpl implements PagoService{
	@Autowired
	private PagoRepository pagoRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>)pagoRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Pago findById(Long id) {
		// TODO Auto-generated method stub
		return pagoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pago save(Pago Pago) {
		// TODO Auto-generated method stub
		return pagoRepository.save(Pago);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pagoRepository.deleteById(id);
		
	}
}
