

//Variables para el platillo
var cantidadTotal =0;
var total = 0;
var subtotal = 0;
var precio = 0;
var platilloId = 0;
var venta = [];
var Plato = {};

function bereken() {
	
}

var btnAgregarDatosPlatillo=document.getElementById('btnAgregarDatosPlatillo');
btnAgregarDatosPlatillo.addEventListener("click",function(){
	$("#tablaPlatillo tbody tr").each(function () {
		  if (this.querySelector(".id").checked || this.querySelector(".nombre").checked ||
				  this.querySelector(".cantidad").checked ||this.querySelector(".precio").checked ) {
			  
			  input1 = document.getElementById('cantidadEnviar').value;

			  platilloId = $(this).find(".id").val().toLowerCase();
			  let nombre = $(this).find(".nombre").val();
			  var cantidad = $(this).find(".cantidad").val();
			  precio=$(this).find(".precio").val();  
			  var cantidadEnviar = $("#cantidadEnviar").val();
			  cantidadTotal=cantidadEnviar;
			  subtotal=(precio*cantidadTotal);
			  console.log(nombre); 
			  			  
			  if(parseInt(cantidadEnviar) > parseInt(cantidad)){
				  swal("NERV", "Superó al stock");     
		          return;
			  }

			  if ($("#cantidadEnviar").val() <= 0 || !Number.isInteger(+input1)){
				  swal("NERV", "Ingrese un valor válido mayor a 0");     
		          return;
			  }
			  else{		  
				  if (checkId(platilloId)) {
					  swal("NERV", "El zapato ya ha sido seleccionado");
				  }
				  else{
					  var tabla = "<tr>";
					  tabla = tabla + "<td for='id'>" + platilloId + "</td>";   
					  tabla = tabla + "<td>" + nombre + "</td>";          
					  tabla = tabla + "<td>" + precio + "</td>";
					  tabla = tabla + "<td>" + cantidadTotal + "</td>";
			          tabla = tabla + "<td>" + subtotal + "</td>";
			          tabla = tabla + "<td><a class='eliminar'>Eliminar</a></td><tr>";
			    	  $("#detalle").append(tabla);  	  // asigna todos los valores a la tabla
			    	  total = total + subtotal; // calcula el total 
			    	  $("#totalVenta").val(total+" pesos"); // imprime el total de la venta   	
			    	  
			    	  $(document).on("click","a.eliminar",function(){
			    		  swal({
				    		   title: "Realmente desea eliminar?",
				    		   text: "Al eliminar, ya no podrá recuperar el item eliminado!",
				    		   icon: "warning",
				    		   buttons: true,
				    		   dangerMode: true,
					    	   }).then((willDelete) => {
					    	   if (willDelete) {
					    		      swal("Good! ¡Zapato eliminado con éxito!", {
					    		        icon: "success",
					    		       
					    		      });		    		    
					    		      var idPlato = $(this).parents("tr").find("td").eq(0).html();
					  	    		  var valor = $(this).parents("tr").find("td").eq(4).html(); // valor para restar el total al momento de eliminar un item.
					  	    		  $(this).parents("tr").fadeOut("normal",
					                        function () {
					                            $(this).remove();
					                            total = total - valor;
					                        	$("#totalVenta").val(total);		                                                 
					  	    		  });		    		 				  	    	 
					    	   } 
					    	   else {
					    		    swal("¡El Plato no ha sido eliminado!");
					    	   }
				    	   });	 
			    	  });
				  }
				  
			  }                 
		  }  
	  });
	  if ($("input[name=cantidad]:checked").val() == null ) {
		  swal("NERV", "Seleccione un Zapato");
	      return ;
	  }
});

  
function fadeOut(id, speed) {
    var s = document.getElementById(id).style;
    s.opacity = 1;
    (function fade() {(s.opacity-=.1)<.1?s.display="none":setTimeout(fade,speed)})();
}

var valor = 0;
function checkId (id) {
	let ids = document.querySelectorAll('#tablaDetallePlatoVenta td[for="id"]');
    return [].filter.call(ids, td => td.textContent === id).length === 1;
}

;