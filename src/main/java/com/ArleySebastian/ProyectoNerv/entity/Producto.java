package com.ArleySebastian.ProyectoNerv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name="producto")
public class Producto{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long refproducto;
	
	@Column
	private String color;
	
	@Column
	private String talla_producto;
	
	@Column
	private String marca;
	
	@Column
	private String descripcion;
	

	
	@Column
	private String foto;

	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	

	public Long getRefproducto() {
		return refproducto;
	}

	public void setRefproducto(Long Refproducto) {
		this.refproducto = Refproducto;
	}

	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

	public String getTalla_producto() {
		return talla_producto;
	}

	public void setTalla_producto(String talla_producto) {
		this.talla_producto = talla_producto;
	}



	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((refproducto == null) ? 0 : refproducto.hashCode());
		result = prime * result + ((talla_producto == null) ? 0 : talla_producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (refproducto == null) {
			if (other.refproducto != null)
				return false;
		} else if (!refproducto.equals(other.refproducto))
			return false;
		if (talla_producto == null) {
			if (other.talla_producto != null)
				return false;
		} else if (!talla_producto.equals(other.talla_producto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [refproducto=" + refproducto + ", color=" + color + ", talla_producto=" + talla_producto
				+ ", marca=" + marca + ", descripcion=" + descripcion + ", foto=" + foto + "]";
	}



	
	

}
