function validarUsuario() {
var email, clave;
email = document.getElementById("email").value;
clave = document.getElementById("password").value;
//El rol admin ingresa al dashboard con las funcionalidades de su rol
if (email === "admin@gmail.com" && clave === "admin") {
window.location.href = "Admin/dashboard_admin.html";
return false;
}
//El rol otro usuario ingresa al dashboard con las funcionalidades de su rol
if (email === "user1@gmail" && clave === "user1") {
window.location.href = "Cliente/dashboard_cliente.html";
return false;
}
if (email === "empleado@gmail.com" && clave == "empleado1")
    window.location.href ="Empleado/dashboard_empleado.html";
return false;

else {

swal({
title: "Credenciales",
text: "Acceso incorrecto, intenta nuevamente!",
icon: "success",
});

return false;

}
