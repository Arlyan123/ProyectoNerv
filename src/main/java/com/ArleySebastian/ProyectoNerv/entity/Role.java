package com.ArleySebastian.ProyectoNerv.entity; 

import java.io.Serializable;
import javax.persistence.*;

	@Entity
	@Table(name="user_roles")
	public class Role implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long userId;
		
		private String nombre;

		public Long getuserId() {
			return userId;
		}

		public void setuserId(Long userId) {
			this.userId = userId;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
			Role other = (Role) obj;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}
	

		@Override
		public String toString() {
			return "Role [userId=" + userId + ", nombre=" + nombre + "]";
		}
		
	}
	
	


