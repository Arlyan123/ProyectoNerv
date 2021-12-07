package com.ArleySebastian.ProyectoNerv.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ArleySebastian.ProyectoNerv.entity.Plato;




public interface PlatoRepository extends CrudRepository<Plato, Long>{
	
	public Optional<Plato> findById(Long id);
	
	@Transactional
    @Modifying
	@Query("update Plato set cantidad = cantidad - ?1 where id= ?2")
	public void actualizarStock(int cantidad, Long id);
	
	@Transactional
    @Modifying
	@Query("update Plato set estado = 'sin stock'  where id= ?1")
	public void actualizarEstado(Long id);


}
