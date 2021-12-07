package com.ArleySebastian.ProyectoNerv.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ArleySebastian.ProyectoNerv.entity.Categoria;




@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	
	

}
