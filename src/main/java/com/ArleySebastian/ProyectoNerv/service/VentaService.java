package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import com.ArleySebastian.ProyectoNerv.entity.Venta;


public interface VentaService {
	public List<Venta> findAll();
	public Venta findById(Long id);
	public Venta save(Venta venta);
	public void delete(Long id);
	public void cambiarEstado(Long id);
	public int contarVenta();
	public int contarVentaPagada();
	public int contarVentaNoPagada();
}
