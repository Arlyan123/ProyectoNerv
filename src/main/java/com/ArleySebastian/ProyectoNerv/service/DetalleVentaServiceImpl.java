package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArleySebastian.ProyectoNerv.contracts.DetalleVentaService;
import com.ArleySebastian.ProyectoNerv.entity.VentaPlato;
import com.ArleySebastian.ProyectoNerv.repository.DetalleVentaRepository;



@Service
public class DetalleVentaServiceImpl implements DetalleVentaService{
	@Autowired
	private DetalleVentaRepository _detalleVentaRepository;
	
	@Override
	public List<VentaPlato> findAll() {
		return (List<VentaPlato>)_detalleVentaRepository.findAll();
	}

	@Override
	public VentaPlato findById(Long id) {
		// TODO Auto-generated method stub
		return  _detalleVentaRepository.findById(id).orElse(null);
	}

	@Override
	public VentaPlato save(VentaPlato detalleVenta) {
		// TODO Auto-generated method stub
		return _detalleVentaRepository.save(detalleVenta);
	}

	@Override
	public void delete(Long id) {
		_detalleVentaRepository.deleteById(id);
		
	}

	/*@Override
	@Transactional(readOnly=true)
	public VentaPlato listarPorVenta(Long id) {
		return _detalleVentaRepository.listarPorVenta(id);
	}*/
}

