package com.ArleySebastian.ProyectoNerv.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ArleySebastian.ProyectoNerv.contracts.DetalleVentaService;
import com.ArleySebastian.ProyectoNerv.entity.Cliente;
import com.ArleySebastian.ProyectoNerv.entity.Pago;
import com.ArleySebastian.ProyectoNerv.entity.Plato;
import com.ArleySebastian.ProyectoNerv.entity.Producto;
import com.ArleySebastian.ProyectoNerv.entity.Role;

import com.ArleySebastian.ProyectoNerv.entity.Usuarios;
import com.ArleySebastian.ProyectoNerv.entity.Venta;
import com.ArleySebastian.ProyectoNerv.entity.VentaPlato;
import com.ArleySebastian.ProyectoNerv.repository.CategoriaRepository;


import com.ArleySebastian.ProyectoNerv.repository.PlatoRepository;
import com.ArleySebastian.ProyectoNerv.repository.ProductoRepository;
import com.ArleySebastian.ProyectoNerv.repository.RoleRepository;
import com.ArleySebastian.ProyectoNerv.repository.UserRepository;
import com.ArleySebastian.ProyectoNerv.service.CategoriaService;
import com.ArleySebastian.ProyectoNerv.service.ClienteService;
import com.ArleySebastian.ProyectoNerv.service.PagoService;

import com.ArleySebastian.ProyectoNerv.service.PlatoService;
import com.ArleySebastian.ProyectoNerv.service.ProductoService;
import com.ArleySebastian.ProyectoNerv.service.RoleService;
import com.ArleySebastian.ProyectoNerv.service.SendMailServices;
import com.ArleySebastian.ProyectoNerv.service.UserService;
import com.ArleySebastian.ProyectoNerv.service.VentaService;


@Controller

public class UserController {
	
	@Autowired
	private SendMailServices sendMailService;
	
	@Autowired
	private UserService _usuarioContract;
	//Navbar
	
	@GetMapping("/inicio")
	public String index() {
		
	return "index";
	}

	@RequestMapping(value="/guardar")
	public String crear(Map<String,Object> model) {
		model.put("titulo","Formulario del usuario");
		Usuarios usuario=new Usuarios();
		model.put("usuario",usuario);
		return "/usuario/guardar";
	}
	
	@RequestMapping(value="/guardar", method=RequestMethod.POST)
	public String guardar(@Validated Usuarios usuario,BindingResult result,Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de Usuarios");
			return "/usuario/guardar";
		}
		_usuarioContract.save(usuario);
		status.setComplete(); // Elimina los objetos session del cliente	
		return "/usuario/guardar";
	}

	 @PostMapping("/inicio") 
	    public String sendMail(
	    		@RequestParam("name") String name,
	    		@RequestParam("apellido") String apellido, 
	    		@RequestParam("id") String id,
	    		@RequestParam("tel") String tel, 
	    		@RequestParam("mail") String mail,
	    		@RequestParam("body") String body
	    		){ 
		 String message = body +"\n\n Datos de contacto: " + "\nNombre: " + name + "\nE-mail: " + mail;
	        sendMailService.sendMail("spring.gaes2@gmail.com","spring.gaes2@gmail.com",id,message);
	        
	        return "index";
	    }
	 
	 @GetMapping("/register")
		public String showRegistrationForm(Model model) {
			model.addAttribute("user", new Usuarios());
			
			List<Role> listRoles = roleservice.listarRoles();
			model.addAttribute("Roles", listRoles);
			return "signup_form";
		}
		
		@PostMapping("/process_register")
		public String processRegister(Usuarios user) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			
			usuarioRepository.save(user);
			
			return "register_success";
		}
		@GetMapping("/consultarCliente")
		public String consultarCliente(Model model) {
			model.addAttribute("userList", userService.getAllUsers());
			model.addAttribute("roles", roleRepository.findAll());
			model.addAttribute("listTab","active");	
		return "consultarCliente";
		}
		
		@GetMapping("/Client")
		public String client(Model model, RedirectAttributes attribute) {
			model.addAttribute("Client",new Usuarios());
			Usuarios Client = new Usuarios();
			List<Role> listRoles = roleservice.listarRoles();
			model.addAttribute("Usuario", Client);
			model.addAttribute("Roles", listRoles);
			
			attribute.addFlashAttribute("success", "ATENCIÓN: Tu correo se ha enviado correctamente!");
			
		return "Client/cliente";
		}
		
		@PostMapping("/save")
		public String save(@Validated @ModelAttribute Usuarios u, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes attribute) {
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario: Nuevo Usuaurio");
				model.addAttribute("Usuario", u);
				
				attribute.addFlashAttribute("warning", "Exiten errores en el formulario");
				return "Client/cliente";
			}
			if(!foto.isEmpty()) {

				String rutaAbsoluta = "C://Usuario//recurso";
				
				try {

					byte[] bytesImg = foto.getBytes();
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
					Files.write(rutaCompleta, bytesImg);
					u.setFoto(foto.getOriginalFilename());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
			
			//model.addAttribute("usuario", userService.registrar(u));
			  model.addAttribute("role", roleRepository.findAll());
			  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(u.getPassword());
				u.setPassword(encodedPassword);
			   usuarioRepository.save(u);

				return "redirect:/consultarCliente";
		}
		

		@GetMapping("/detalles/{id}")
		public String detalleUsuaurio(@PathVariable Long id, Model model, RedirectAttributes attribute) {
			
			Usuarios Client = null;
			
			if(id>0) {
				Client = userService.buscarPorId(id);
			   if(Client == null) {
				   attribute.addFlashAttribute("error", "Atencion: el id no existe");
				   return "redirect:/consultarCliente/";
			   }
			}else {
				attribute.addFlashAttribute("error", "Atencion: error con el id del usuario");
				return "redirect:/consultarCliente/";
			}
			model.addAttribute("cc",new Cliente());
			model.addAttribute("titulo","Detalle del usuario: "+ Client.getNombre());
			model.addAttribute("Client",Client);
			
			
			return "detalleusuario";
		}
		//registrar cliente
		@GetMapping("/registrarCliente")
		public String registrarCliente(Model model) {
			
			model.addAttribute("cc",new Cliente());
			model.addAttribute("userList", userService.getAllUsers());
		return "registrarCliente";
		}
		
		
		
		@PostMapping("/cli")
		public String save(@Validated @ModelAttribute Cliente c, BindingResult result, Model model, RedirectAttributes attribute) {
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario: Nuevo Cliente");
				model.addAttribute("cliente", c);
				
				attribute.addFlashAttribute("warning", "Exiten errores en el formulario");
				return "registrarCliente";
			}
		    clienteRepository.save(c);
			attribute.addAttribute("success", "cliente guardado con exito");
			
			return "redirect:/consultarCliente";
			
		}
		//rol
		@GetMapping("/asignarRol")
		public String asignarRol(Model model) {
			
			model.addAttribute("rr",new Role());
			model.addAttribute("userList", userService.getAllUsers());
		return "asignarRol";
		}
		
		
		
		@PostMapping("/rol")
		public String sav(@Validated @ModelAttribute Role r, BindingResult result, Model model, RedirectAttributes attribute) {
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario: Nuevo rol");
				model.addAttribute("rol", r);
				
				attribute.addFlashAttribute("warning", "Exiten errores en el formulario");
				return "asignarRol";
			}
		    roleRepository.save(r);
			attribute.addAttribute("success", "rol guardado con exito");
			return "redirect:/asignarRol/";
			
		}
		
		@GetMapping("/editar/{id}")
		public String editar(@PathVariable Long id, Model model) {
			Optional<Usuarios>Client=userService.listarId(id);
			model.addAttribute("rr",new Role());
			model.addAttribute("Client", Client);
			model.addAttribute("Cliente", Client);
			return "Client/cliente";
		}
		
		@GetMapping("/delete/{id}")
		public String eliminar(@PathVariable Long id,Model model) {
			userService.eliminar(id);
			System.out.println("Registro eliminado");
			model.addAttribute("userList", userService.getAllUsers());
			model.addAttribute("roles", roleRepository.findAll());
			model.addAttribute("listTab","active");	
			
			return "redirect:/consultarCliente";
		}
		
	//Catalogos
	
	@GetMapping("/Mujer")
	public String Mujer() {
	return "Mujer";
	}
	
	@GetMapping("/Hombre")
	public String Hombre() {
	return "Hombre";
	}
	
	@GetMapping("/child")
	public String child() {
	return "child";
	}
	
	@GetMapping("/zapatillas")
	public String zapatillas() {
	return "zapatillas";
	}
	
	@GetMapping("/deportivo")
	public String deportivo() {
	return "deportivo";
	}
	
	@GetMapping("/Formal")
	public String Formal() {
	return "Formal";
	}
	
	@GetMapping("/Botas")
	public String Botas() {
	return "Botas";
	}
	
	@GetMapping("/colegial")
	public String colegial() {
	return "colegial";
	}
	
	//Administrador
	
	
	@GetMapping("/Admin")
	public String Admin() {
	return "Admi/dashboard_admin";
	}
	
	@GetMapping("/principal")
	public String principal() {
	return "principal";
	}
	
	@GetMapping("/administrador")
	public String administrador() {
	return "Admi/administrador";
	}
	
	@GetMapping("/cambiarContraseña")
	public String cambiarContraseña() {
	return "cambiarContraseña";
	}
	
	@GetMapping("/consultarCuenta")
	public String consultarCuenta() {
	return "consultarCuenta";
	}
	
	
	//Inventario Admin
	@Autowired
	ProductoService productoService;
	
	@Autowired
	PlatoService platoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	PlatoRepository platoRepository;
	
	@Autowired
	com.ArleySebastian.ProyectoNerv.repository.ClienteRepository clienteRepository;
	
	@Autowired
	CategoriaRepository catalogoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/GestionarProductos")
	public String GestionarProductos(Model model) {
		model.addAttribute("productoList", productoService.getAllproductos());
		model.addAttribute("categoria", categoriaRepository.findAll());
		model.addAttribute("listTab","active");	
	return "GestionarProductos";
	}
	
	@GetMapping("/catalogo")
	public String catalogo(Model model) {
		model.addAttribute("productoList", productoService.getAllproductos());
		model.addAttribute("categoria", categoriaRepository.findAll());
		model.addAttribute("listTab","active");	
	return "catalogo";
	}
	
	@GetMapping("/Solicitudpedido")
	public String Solicitudpedido(Model model) {

		model.addAttribute("categoria", categoriaRepository.findAll());
		return "Solicitudpedido";
	}
	
	@GetMapping("/agregar/{refproducto}")
	public String agregar(@PathVariable Long refproducto, Model model) {
		Optional<Producto>pp=productoService.listarId(refproducto);
		model.addAttribute("pp",pp);
		model.addAttribute("categoria", categoriaRepository.findAll());
		return "Solicitudpedido";
	}
	
	@GetMapping("/registrarProducto")
	public String registrarProducto(Model model) {
		model.addAttribute("pp",new Producto());
		model.addAttribute("cata", categoriaRepository.findAll());
		model.addAttribute("categoria",categoriaService.findAll());
		model.addAttribute("userList", userService.getAllUsers());
	return "registrarProducto";
	}
	
	@PostMapping("/pro")
	public String save(@Validated @ModelAttribute Producto p, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Producto");
			model.addAttribute("producto", p);
			
			attribute.addFlashAttribute("warning", "Exiten errores en el formulario");
			return "registrarProducto";
		}
		if(!foto.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/img");
			String rutaAbsoluta = "C://Producto//recursos";
			
			try {
				byte[] bytesImg = foto.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				p.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		productoRepository.save(p);
		attribute.addAttribute("success", "producto guardado con exito");
		
		return "redirect:/GestionarProductos/";
	}
	
	@GetMapping("/registrarZapato")
	public String registrarZapato(Model model) {
		
		model.addAttribute("zz",new Plato());
		model.addAttribute("productoList", productoService.getAllproductos());
		model.addAttribute("cata", categoriaRepository.findAll());
		model.addAttribute("categoria",categoriaService.findAll());
		model.addAttribute("userList", userService.getAllUsers());
	return "registrarZapato";
	}
	
	@PostMapping("/zap")
	public String saves(@Validated @ModelAttribute Plato z, BindingResult result, Model model, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Zapato");
			model.addAttribute("zapato", z);
			
			attribute.addFlashAttribute("warning", "Exiten errores en el formulario");
			return "registrarZapato";
		}
	platoRepository.save(z);
		attribute.addAttribute("success", "producto guardado con exito");
		return "redirect:/GestionarProductos/";
		
	}
	
	
	@GetMapping("/editarp/{refproducto}")
	public String editarp(@PathVariable Long refproducto, Model model) {
		Optional<Producto>pp=productoService.listarId(refproducto);
		
		model.addAttribute("categoria", catalogoRepository.findAll());
		model.addAttribute("pp",pp);

		return "registrarProducto";
	}
	
	@GetMapping("/detalle/{refproducto}")
	public String detalleProducto(@PathVariable Long refproducto, Model model, RedirectAttributes attribute) {
		
		Producto pp = null;
		
		if(refproducto>0) {
			pp = productoService.buscarPorId(refproducto);
		   if(pp == null) {
			   attribute.addFlashAttribute("error", "Atencion: el id no existe");
			   return "redirect:/GestionarProductos/";
		   }
		}else {
			attribute.addFlashAttribute("error", "Atencion: error con el id del producto");
			return "redirect:/GestionarProductos/";
		}
			
		model.addAttribute("titulo","Detalle del producto: "+ pp.getMarca());
		model.addAttribute("pp",pp);
		model.addAttribute("zz",new Plato());
		model.addAttribute("productoList", productoService.getAllproductos());
		model.addAttribute("cata", categoriaRepository.findAll());
		model.addAttribute("categoria",categoriaService.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		return "detalleproducto";
	}
	
	
	@GetMapping("/editarProducto")
	public String editarProducto() {

	return "editarProducto";
	}
	
	@GetMapping("/eliminarp/{refproducto}")
	public String delete(Model model, @PathVariable Long refproducto) {
		productoService.eliminar(refproducto);
		System.out.println("Registro eliminado");
		model.addAttribute("productoList", productoService.getAllproductos());
		model.addAttribute("categoria", catalogoRepository.findAll());
		model.addAttribute("listTab","active");	
		
		return "redirect:/GestionarProductos";
	}

	
	@GetMapping("/adminInventario.htlm")
	public String adminInventario() {
	return "adminInventario.htlm";
	}
	
	//Clientes Admin
	
	@GetMapping("/adminGestion")
	public String adminGestion() {
	return "adminGestion";
	}
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleservice;
	
	@Autowired
	UserRepository ClienteRepository;
	
	
	@GetMapping("/adminClientes")
	public String adminClientes() {
	return "adminClientes";
	}
	
	@GetMapping("/ConsultarCuentaCliente")
	public String ConsultarCuentaCliente() {
	return "Client/ConsultarCuentaCliente";
	}
	
	@GetMapping("/ConsultarEmpleado")
	public String ConsultarEmpleado() {
	return "Empleado/ConsultarEmpleado";
	}
	
	//PyE
	
	@GetMapping("/adminPye")
	public String adminPye() {
	return "adminPye";
	}
	
	
	
	//Cliente
	
	@GetMapping("/carCliente")
	public String carCliente() {
	return "carCliente";
	}
	 @PostMapping("/carCliente") 
	    public String sendeMail(
	    		@RequestParam("nombre") String nombre,
	    		@RequestParam("apellido") String apellido, 
	    		@RequestParam("direccion") String direccion,
	    		@RequestParam("descripcion") String descripcion, 
	    		@RequestParam("localidad") String localidad,
	    		@RequestParam("barrio") String barrio,
	    		@RequestParam("telefono") String telefono,
	    		@RequestParam("mail") String mail,
	    		@RequestParam("tipo") String tipo,
	    		@RequestParam("id") String id
	    		){ 
		 String message = localidad + "\n barrio: "+ barrio +"\n\n Datos de contacto: " + "\nNombre: " + nombre + "\n Apellido:"+ "\nE-mail: " + mail
				 +"\n Direccion:"+ direccion + "\n Télefono:" +telefono+ "\n tipo de id:"+tipo +"\n Id:"+id ;
	        sendMailService.sendeMail("spring.gaes2@gmail.com","spring.gaes2@gmail.com","Solicitud de pedido",message);
	        return "carCliente";
	    }
	
	@GetMapping("/clienteInventario")
	public String clienteInventario() {
	return "clienteInventario";
	}
	
	@GetMapping("/Consultarenviopersona")
	public String Consultarenviopersona() {
	return "Consultarenviopersona";
	}
	
	@GetMapping("/ConsultarEnvioCliente")
	public String ConsultarEnvioCliente() {
	return "ConsultarEnvioCliente";
	}
	
	@GetMapping("/gestionarNuevoPedido")
	public String gestionarNuevoPedido() {
	return "gestionarNuevoPedido";
	}
	
	

	@GetMapping("/Cliente")
	public String dashcliente() {
	return "Client/dashboard_cliente";
	}
	
	@GetMapping("/consultarpedido")
	public String consultarpedido() {
	return "consultarpedido";
	}
	
	@GetMapping("/principalCliente")
	public String principalCliente() {
	return "principalCliente";
	}
	
	@GetMapping("/clienteGestion")
	public String clienteGestion() {
	return "clienteGestion";
	}
	
	
	@GetMapping("/ClientePye")
	public String ClientePye() {
	return "ClientePye";
	}
	
	
	//Empleado
	
	@GetMapping("/Empleado")
	public String Empleado() {
	return "Empleado/dashboard_empleado";
	}
	@GetMapping("/Emplead")
	public String Emplead() {
	return "Empleado/empleado";
	}
	
	@GetMapping("/principalEmpleado")
	public String principalEmpleado() {
	return "principalEmpleado";
	}
	
	@GetMapping("/empleadoInventario")
	public String empleadoInventario() {
	return "empleadoInventario";
	}
	
	@GetMapping("/empleadoPys")
	public String empleadoPys() {
	return "empleadoPys";
	}
	
	@GetMapping("/empleadoGestion")
	public String empleadoGestion() {
	return "empleadoGestion";
	}
	//Graficas
	
	@GetMapping("/grafica/ejemplo3")
	public String ejemplo3() {
	return "grafica/ejemplo3";
	}
	
	@GetMapping("/grafica/ejemplo2")
	public String ejemplo2() {
	return "grafica/ejemplo2";
	}
	
	@GetMapping("/grafica/ejemplo1")
	public String ejemplo1() {
	return "grafica/ejemplo1";
	}
	
	
	

	
	
//MENU
	
	@Autowired
	private VentaService _ventaContract;

	@GetMapping("/menu")
	public String index(Model model) {
		int sumaClientes = 0;
		//int sumaVentas = 0;

		for(Cliente cliente:_clienteContract.findAll()) {

			System.out.println(cliente.getId());
		}
		for(Venta venta:_ventaContract.findAll()) {

			System.out.println(venta.getId());
			
		}

		model.addAttribute("titulo", "NERV'");
		model.addAttribute("cantidadClientes", sumaClientes);
		model.addAttribute("cantidadVentas", _ventaContract.contarVenta());
		model.addAttribute("ventaPagada",_ventaContract.contarVentaPagada());
		model.addAttribute("ventaNoPagada",_ventaContract.contarVentaNoPagada());
		return "menu";
	}
	//PAGO

	
	@Autowired
	private PagoService _pagoContract;
	
	@Autowired
	private DetalleVentaService _detalleVentaContract;

	@GetMapping("/pago")
	public String crear(Model model) {	
		model.addAttribute("titulo","Módulo de pago");
		model.addAttribute("ventas",_ventaContract.findAll());
		return "/pago/pago";
	}

	@GetMapping("/noPagado")
	public String noPagado(Model model) {	
		model.addAttribute("titulo","Módulo de pago");
		model.addAttribute("ventas",_ventaContract.findAll());

		return "/pago/noPagado";
	}

	@GetMapping("/pagado")
	public String pagado(Model model) {	
		model.addAttribute("titulo","Módulo de pago");
		model.addAttribute("ventas",_ventaContract.findAll());
		return "/pago/pagado";
	}

	@GetMapping("/pagar/{id}")
	public String pagarVenta(@PathVariable(value="id") Long id, Map<String,Object> model, Model model2, RedirectAttributes f) {	
		Venta venta=new Venta();
		Pago pago=new Pago();
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<VentaPlato> ventaPlato=new ArrayList<>();
		if(id>0) {
			venta=_ventaContract.findById(id);
			for(VentaPlato vp:_detalleVentaContract.findAll()) {
				if(vp.getVenta().getId()==id) {
					ventaPlato.add(vp);
				}
				total=venta.calcularTotal();
				calcularIgv= venta.calcularIgv();
				subTotal = venta.calcularSubTotal();
			}
			if(venta==null ) {
				f.addFlashAttribute("error","El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		}
		else {
			f.addAttribute("error","El ID no puede ser 0");
			return "redirect:/noPagado";
		}

		model.put("pago",pago);
		model.put("venta",venta);
		model2.addAttribute("ventaPlatos",ventaPlato);
		model.put("titulo","Módulo de pago");
		model2.addAttribute("ventaTotal",+total);
		model2.addAttribute("igv",+calcularIgv);
		model2.addAttribute("subTotal",+subTotal);
		
		return "/pago/pagar";
	}
	//VENTA

	@GetMapping(value="/detalleVenta/{id}")
	public String detalleVenta(@PathVariable(value="id") Long id, Map<String,Object> model, Model model2, RedirectAttributes f) {	
		Venta venta=null;
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<VentaPlato> ventaPlato=new ArrayList<>();
		if(id>0) {
			venta=_ventaContract.findById(id);
			for(VentaPlato vp:_detalleVentaContract.findAll()) {
				if(vp.getVenta().getId()==id) {
					ventaPlato.add(vp);
				}
				total=venta.calcularTotal();
				calcularIgv= venta.calcularIgv();
				subTotal = venta.calcularSubTotal();
				
			}
			if(venta==null ) {
				f.addFlashAttribute("error","El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		}
		else {
			f.addAttribute("error","El ID no puede ser 0");
			return "redirect:/noPagado";
		}
		model.put("venta",venta);
		model2.addAttribute("ventaPlatos",ventaPlato);
		model.put("titulo","Módulo de pago");
		model2.addAttribute("ventaTotal",+total);
		model2.addAttribute("igv",+calcularIgv);
		model2.addAttribute("subTotal",+subTotal);
		return "/pago/detalleVenta";
	}

	@GetMapping(value="/imprimirVenta/{id}")
	public String imprimirVenta(@PathVariable(value="id") Long id, Map<String,Object> model, Model model2, RedirectAttributes f) {	
		Venta venta=null;
		double total = 0;
		double subTotal = 0;
		double calcularIgv = 0;
		List<VentaPlato> ventaPlato=new ArrayList<>();
		if(id>0) {
			venta=_ventaContract.findById(id);
			for(VentaPlato vp:_detalleVentaContract.findAll()) {
				if(vp.getVenta().getId()==id) {
					ventaPlato.add(vp);
				}
				total=venta.calcularTotal();
				calcularIgv= venta.calcularIgv();
				subTotal = venta.calcularSubTotal();
				
			}
			if(venta==null ) {
				f.addFlashAttribute("error","El ID no existe en la base de datos");
				return "redirect:/noPagado";
			}
		}
		else {
			f.addAttribute("error","El ID no puede ser 0");
			return "redirect:/noPagado";
		}
		model.put("venta",venta);
		model2.addAttribute("ventaPlatos",ventaPlato);
		model.put("titulo","Módulo de pago");
		model2.addAttribute("ventaTotal","$ "+total);
		model2.addAttribute("igv","$ "+calcularIgv);
		model2.addAttribute("subTotal","$ "+subTotal);
		return "/pago/imprimirPago";
	}

	@PostMapping(value="/guardarPago", produces= "application/json", consumes = "application/json")
	@ResponseBody
	public Pago guardarPago(@RequestBody Pago pago)  throws IOException{	
		Pago p=_pagoContract.save(pago);

		_ventaContract.cambiarEstado(p.getVenta().getId());

		return p;
	}

	
	@Autowired
	private PlatoService _platoContract;
	
	@Autowired
	private ClienteService _clienteContract;
	
	

	@GetMapping(value="venta")
	public String listar(Model model){
		model.addAttribute("titulo","Módulo de ventas");
		model.addAttribute("clientes",_clienteContract.findAll());
		model.addAttribute("platillos",_platoContract.findAll());
		return "/venta/venta";
	}
	@CrossOrigin(origins = "http://localhost:8085")
	@RequestMapping(value="/venta")
	public String crear() {	
		return "/venta/venta";
	}
	
	@PostMapping(value="/venta", produces= "application/json", consumes = "application/json")
	@ResponseBody
	public Venta guardar(@RequestBody Venta venta) throws IOException{
			
		Venta ve=new Venta();
		
		for(VentaPlato v: venta.getVentaPlato()) {
			v.setVenta(venta);
			ve=_ventaContract.save(venta);
			_platoContract.actualizarStock(v.getCantidad(), v.getPlato().getId());
			System.out.println(v.getCantidad()+" "+ve.getId());
			
		}
		for(Plato pl: _platoContract.findAll()) {
			if(pl.getCantidad()==0) {
				_platoContract.actualizarEstado(pl.getId());
			}
		}
		
		
		return

				ve;
	}
	
	@GetMapping(value="compra")
	public String listarcompra(Model model){
		model.addAttribute("titulo","Módulo de ventas");
		model.addAttribute("clientes",_clienteContract.findAll());
		model.addAttribute("platillos",_platoContract.findAll());
		return "/venta/compra";
	}
	@CrossOrigin(origins = "http://localhost:8085")
	@RequestMapping(value="/compra")
	public String creacompra() {	
		return "/venta/compra";
	}
	
	@PostMapping(value="/compra", produces= "application/json", consumes = "application/json")
	@ResponseBody
	public Venta comprar(@RequestBody Venta venta) throws IOException{
			
		Venta ve=new Venta();
		
		for(VentaPlato v: venta.getVentaPlato()) {
			v.setVenta(venta);
			ve=_ventaContract.save(venta);
			_platoContract.actualizarStock(v.getCantidad(), v.getPlato().getId());
			System.out.println(v.getCantidad()+" "+ve.getId());
			
		}
		for(Plato pl: _platoContract.findAll()) {
			if(pl.getCantidad()==0) {
				_platoContract.actualizarEstado(pl.getId());
			}
		}
		
		
		return

				ve;
	}


	@RequestMapping(value="/contarVentas", method=RequestMethod.GET)
	public String contarVentas(Model model) throws IOException{
	    //model.addAttribute("contarVentas",_ventaContract.contarVentas());
	//	System.out.println(_ventaContract.contarVentas());
	    return "menu";
	}
	
	@GetMapping(value="500")
	public String erroruno(Model model){
	
		return "/Error/500";
	}
	
	@GetMapping(value="404")
	public String errordos(Model model){

		return "/Error/404";
	}
	
		
	



	
}
