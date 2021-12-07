package com.ArleySebastian.ProyectoNerv.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ArleySebastian.ProyectoNerv.entity.Usuarios;

@Repository
public interface UserRepository extends JpaRepository<Usuarios, Long>{
	
	public Set<Usuarios> findByNombre(String Nombre);
	public Usuarios findByUsername(String Username);
	
	@Query("SELECT u FROM Usuarios u WHERE u.correo = ?1")
	public Usuarios findByEmail(String correo);

	@Query("select u from Usuarios u where u.username = ?1 and u.password = ?2")
	Usuarios findByNombreEndsWith(String Username, String Password);
	
	@Query("select u from Usuarios u where u.username = ?1")
	Usuarios listarPorNombre(String Nombre);

}
