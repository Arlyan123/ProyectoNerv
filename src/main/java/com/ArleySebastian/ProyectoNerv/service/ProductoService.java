package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;
import java.util.Optional;



import com.ArleySebastian.ProyectoNerv.entity.Producto;


public interface ProductoService {
	public Iterable<Producto> getAllproductos();
	
	int save(Producto p);

	Optional<Producto> listarId(Long refproducto);
	

	public Producto buscarPorId(Long refproducto);

	public void eliminar(Long refproducto);


	public List<Producto> findAll();
	public Producto findById(Long id);
	public void delete(Long id);

	public void actualizarStock(int cantidad, Long id);
	public void actualizarEstado(Long id);

}
