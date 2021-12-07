package com.ArleySebastian.ProyectoNerv.contracts;

import java.util.List;

import com.ArleySebastian.ProyectoNerv.entity.VentaPlato;


public interface DetalleVentaService {
	public List<VentaPlato> findAll();
	public VentaPlato findById(Long id);
	public VentaPlato save(VentaPlato detalleVenta);
	public void delete(Long id);
	//public VentaPlato listarPorVenta(Long id);
}
