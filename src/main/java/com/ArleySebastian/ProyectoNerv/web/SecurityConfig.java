package com.ArleySebastian.ProyectoNerv.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().and()
		.anonymous().and().authorizeRequests()
		.antMatchers("/auth/**","/index","/","/inicio","/carrito","/colegial","/Botas","/Formal","/zapatillas","/child",
				"/Hombre","/Mujer","/deportivo","/css/**","/cssPry/**","/js/**","/icons/**","/img/**",
				"/layer/**","/Cliente/**","/Empleado/**","/grafica/**","/Graficas/**","/Sweetalert/**","/webfonts/**",
				"/gestionarNuevoPedido/**","/carCliente","/GestionarProductos","/pago/**","/noPagado/**","/pagado/**","/pagar/**"
				,"/menu/**","/Solicitudpedido/**","/detalleVenta/**","/imprimirVenta/**","/guardarPago/**","/imprimirCompra/**"
				,"/contarVentas/**","/detalleCompra/**","/compra/**","/registrarSolicitud/**","/bootstrap/**","/bootstrapHome/**"
				,"/venta/**","/listarCompras/**","/guardarPago/**","/register/**", "/process_register", "/500", "/404","/registrarCliente","/asignarRol").permitAll()
		.antMatchers("/principalCliente").hasAnyRole("USER")
		.antMatchers("/ConsultarCuentaCliente").hasAnyRole("USER")
		.antMatchers("/clienteInventario").hasAnyRole("USER")
		.antMatchers("clienteGestion").hasAnyRole("USER")
		.antMatchers("/ClientePye").hasAnyRole("USER")
		.antMatchers("/Admin").hasAnyRole("ADMIN")
		.antMatchers("/Client").hasAnyRole("ADMIN")
		.antMatchers("/principal").hasAnyRole("ADMIN")
		.antMatchers("/registrarZapato/**").hasAnyRole("ADMIN")
		.antMatchers("/administrador").hasAnyRole("ADMIN")
		.antMatchers("/consultarCuenta").hasAnyRole("ADMIN")
		.antMatchers("/GestionarProductos/").hasAnyRole("ADMIN")
		.antMatchers("/adminPye").hasAnyRole("ADMIN")
		.antMatchers("/grafica/ejemplo3").hasAnyRole("ADMIN")
		.antMatchers("/grafica/ejemplo2").hasAnyRole("ADMIN")
		.antMatchers("/grafica/ejemplo1").hasAnyRole("ADMIN")
		.antMatchers("/consultarenvio").hasAnyRole("ADMIN")
		.antMatchers("/consultarPedidos").hasAnyRole("ADMIN")
		.antMatchers("/GestionarProductos/agregar").hasAnyRole("ADMIN")
		.antMatchers("/GestionarProductos/pro/**").hasAnyRole("ADMIN")
		.antMatchers("/GestionarProductos/editarp/**").hasAnyRole("ADMIN")
		.antMatchers("/GestionarProductos/detalle/**").hasAnyRole("ADMIN")
		.antMatchers("/GestionarProductos/eliminarp").hasAnyRole("ADMIN")
		.antMatchers("/consultarCliente/save").hasAnyRole("ADMIN")
		.antMatchers("/consultarCliente/editar/**").hasAnyRole("ADMIN")
		.antMatchers("/consultarCliente/detalles/**").hasAnyRole("ADMIN")
		.antMatchers("/consultarCliente/delete/**").hasAnyRole("ADMIN")
		.antMatchers("/consultarCliente").hasAnyRole("ADMIN")
		.and()
		.formLogin().loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
		http.csrf().disable();
		 http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
		 
		}
}
