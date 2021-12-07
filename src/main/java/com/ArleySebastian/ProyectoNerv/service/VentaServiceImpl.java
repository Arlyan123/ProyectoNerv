package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Venta;
import com.ArleySebastian.ProyectoNerv.repository.VentaRepository;


@Service
public class VentaServiceImpl implements VentaService{
	@Autowired
	private VentaRepository ventaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Venta> findAll() {
		
		return (List<Venta>)ventaRepository.findAll(); 
	}

	@Override
	@Transactional(readOnly=true)
	public Venta findById(Long id) {
		return ventaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Venta save(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ventaRepository.deleteById(id);
	
	}

	@Override
	@Transactional
	public void cambiarEstado(Long id) {
		// TODO Auto-generated method stub
		ventaRepository.cambiarEstado(id);
	}

	@Override
	@Transactional(readOnly=true)
	public int contarVenta() {
		int contar=ventaRepository.contarVenta();
		return contar;
	}

	@Override
	@Transactional(readOnly=true)
	public int contarVentaPagada() {
		// TODO Auto-generated method stub
		int noPagado=ventaRepository.contarVentaPagada();
		return noPagado;
	}

	@Override
	@Transactional(readOnly=true)
	public int contarVentaNoPagada() {
		int pagado=ventaRepository.contarVentaNoPagada();
		return pagado;
	}
}
