package com.ArleySebastian.ProyectoNerv.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ArleySebastian.ProyectoNerv.entity.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	public Optional<Cliente> findById(Long id);
}
