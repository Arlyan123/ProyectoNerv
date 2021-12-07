package com.ArleySebastian.ProyectoNerv.repository;


import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ArleySebastian.ProyectoNerv.entity.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{
	
	public Set<Producto> findByRefproducto(Long refproducto);

	@Transactional
    @Modifying
	@Query("update Producto set cantidad = cantidad - ?1 where refproducto= ?2")
	public void actualizarStock(int cantidad, Long refproducto);
	
	@Transactional
    @Modifying
	@Query("update Producto set estado = 'sin stock'  where refproducto= ?1")
	public void actualizarEstado(Long refproducto);

}
