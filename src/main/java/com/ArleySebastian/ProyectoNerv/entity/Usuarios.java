package com.ArleySebastian.ProyectoNerv.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.persistence.JoinColumn;

@Entity
@Table(name="usuarios")
	public class Usuarios implements Serializable{
		private static final long serialVersionUID = -6833167247955613395L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column
		private String tipo_id;
		
		@Column
		private Long numero;
		
		@Column 
		private String nombre;
		
		@Column 
		private String apellido;
		
		@Column 
		private String correo;
		
		@Column 
		private String genero;
		
		@Column 
		private String direccion;
		
		@Column 
		private String localidad;
		
		@Column 
		private String telefono;
		
		@Column 
		private Long edad;
		
		@Column 
		private String estado;
		
		@Column
		private String password;
		
		@Column
		private String foto;
		
		@Column
		private String username;
		
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
		@OneToMany
		@JoinColumn(name="idUsuario")
		private List<Role> roles;
		
		public Usuarios() {
			super();
		}

		public Long getNumero() {
			return numero;
		}

		public void setNumero(Long numero) {
			this.numero = numero;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public Usuarios(Long id) {
			super();
			this.id = id;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTipo_id() {
			return tipo_id;
		}

		public void setTipo_id(String tipo_id) {
			this.tipo_id = tipo_id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getLocalidad() {
			return localidad;
		}

		public void setLocalidad(String localidad) {
			this.localidad = localidad;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public Long getEdad() {
			return edad;
		}

		public void setEdad(Long edad) {
			this.edad = edad;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
			result = prime * result + ((correo == null) ? 0 : correo.hashCode());
			result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
			result = prime * result + ((edad == null) ? 0 : edad.hashCode());
			result = prime * result + ((estado == null) ? 0 : estado.hashCode());
			result = prime * result + ((foto == null) ? 0 : foto.hashCode());
			result = prime * result + ((genero == null) ? 0 : genero.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
			result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
			result = prime * result + ((numero == null) ? 0 : numero.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((roles == null) ? 0 : roles.hashCode());
			result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
			result = prime * result + ((tipo_id == null) ? 0 : tipo_id.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			Usuarios other = (Usuarios) obj;
			if (apellido == null) {
				if (other.apellido != null)
					return false;
			} else if (!apellido.equals(other.apellido))
				return false;
			if (correo == null) {
				if (other.correo != null)
					return false;
			} else if (!correo.equals(other.correo))
				return false;
			if (direccion == null) {
				if (other.direccion != null)
					return false;
			} else if (!direccion.equals(other.direccion))
				return false;
			if (edad == null) {
				if (other.edad != null)
					return false;
			} else if (!edad.equals(other.edad))
				return false;
			if (estado == null) {
				if (other.estado != null)
					return false;
			} else if (!estado.equals(other.estado))
				return false;
			if (foto == null) {
				if (other.foto != null)
					return false;
			} else if (!foto.equals(other.foto))
				return false;
			if (genero == null) {
				if (other.genero != null)
					return false;
			} else if (!genero.equals(other.genero))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (localidad == null) {
				if (other.localidad != null)
					return false;
			} else if (!localidad.equals(other.localidad))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			if (numero == null) {
				if (other.numero != null)
					return false;
			} else if (!numero.equals(other.numero))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (roles == null) {
				if (other.roles != null)
					return false;
			} else if (!roles.equals(other.roles))
				return false;
			if (telefono == null) {
				if (other.telefono != null)
					return false;
			} else if (!telefono.equals(other.telefono))
				return false;
			if (tipo_id == null) {
				if (other.tipo_id != null)
					return false;
			} else if (!tipo_id.equals(other.tipo_id))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Usuarios [id=" + id + ", tipo_id=" + tipo_id + ", numero=" + numero + ", nombre=" + nombre
					+ ", apellido=" + apellido + ", correo=" + correo + ", genero=" + genero + ", direccion="
					+ direccion + ", localidad=" + localidad + ", telefono=" + telefono + ", edad=" + edad + ", estado="
					+ estado + ", password=" + password + ", foto=" + foto + ", username=" + username + ", roles="
					+ roles + "]";
		}
		
		
		
	}
