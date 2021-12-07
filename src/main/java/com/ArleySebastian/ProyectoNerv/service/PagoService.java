package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import com.ArleySebastian.ProyectoNerv.entity.Pago;



public interface PagoService {
	public List<Pago> findAll();
	public Pago findById(Long id);
	public Pago save(Pago Pago);
	public void delete(Long id);

}
