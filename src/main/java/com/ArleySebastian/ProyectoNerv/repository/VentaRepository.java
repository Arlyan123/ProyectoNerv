package com.ArleySebastian.ProyectoNerv.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ArleySebastian.ProyectoNerv.entity.Venta;



public interface VentaRepository extends CrudRepository<Venta, Long>{
	@Transactional
    @Modifying
	@Query("update Venta set estado = 'pagado' where id= ?1")
	public void cambiarEstado(Long id);
	
	
	@Query("select count(*) from Venta")
	public int contarVenta();
	
	@Query("select count(*) from Venta where estado='pagado'")
	public int contarVentaPagada();
	
	@Query("select count(*) from Venta where estado='No pagado'")
	public int contarVentaNoPagada();


}
