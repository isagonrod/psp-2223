# Actividad 2: Primer servicio REST con Spring Boot

El ejemplo que se utiliza en esta lección está accesible desde la web de Spring en la siguiente url: https://spring.io/guides/gs/rest-service/.
<br>Aunque también se puede descargar directamente desde el propio IDE de la siguiente manera:
- Desde Spring Tool Suite: _New > Import Spring Getting Started Content_ y seleccionando **Rest Service**.
- Desde IntelliJ: _File > New > Project from Version Control_, se introduce la URL del repositorio y la carpeta donde se quiere guardar.

## ¿Qué se hará en esta actividad?
Se va a implementar una aplicación de servidor que va a aceptar peticiones GET en una determinada ruta /greeting y que nos va a devolver un JSON, es decir, un mensaje de respuesta que va a tener un id de tipo numérico (long) y un mensaje en una cadena de caracteres (String).