# Actividad 2: Primer servicio REST con Spring Boot

El ejemplo que se utiliza en esta lección está accesible desde la web de Spring en la siguiente url: https://spring.io/guides/gs/rest-service/.
<br>Aunque también se puede descargar directamente desde el propio IDE de la siguiente manera:
- Desde Spring Tool Suite: _New > Import Spring Getting Started Content_ y seleccionando **Rest Service**.
- Desde IntelliJ: _File > New > Project from Version Control_, se introduce la URL del repositorio y la carpeta donde se quiere guardar.

## ¿Qué se hará en esta actividad?
Se va a implementar una aplicación de servidor que va a aceptar peticiones GET en una determinada ruta /greeting y que nos va a devolver un JSON, es decir, un mensaje de respuesta que va a tener un id de tipo numérico (long) y un mensaje en una cadena de caracteres (String). Si le proporcionamos como parámetro opcional 'name' a este greeting, en lugar de hacer un "Hello World!" haremos un "Hello User!" (siendo _User_ el nombre introducido como parámetro opcional).

## Interación
La interacción será hacer peticiones GET a /greeting, obteniendo como respuesta un código 200 OK, incluyendo en el cuerpo de la respuesta un objeto JSON, que deberá incluir un id único y un texto con el mensaje de bienvenida o saludo.

## Gestión del modelo de datos
### Clase Modelo
Se creará una clase modelo _Greeting_, que incluye los datos del id y del contenido.

### Clase Controlador
También se hará otra clase controlador, _GreetingController_, con @RestController, y dentro de esta, un método con @GetMapping (para aceptar las peticiones GET en una URL). Este método recibe como parámetro un 'name' (inyectado con @RequestParam), este name lo recogerá de la url, si no lo encuetra, aparecerá por defecto el valor que se ha introducido como _defaultValue_, que en este caso es la palabra "World".

- **@RestController** es la convinanción de @Controller y @ResponseBody (ésta lo que devuelve en el método es el cuerpo de la respuesta, pasando previamente por el filtro del _HttpMessageConverter_ que lo transforma de un objeto Java a un objeto JSON).
- **@GetMapping** se utiliza en lugar de @RequestMapping, para no tener que indicarle el método GET entre paréntesis como parámetro.

### Clase Principal
Y además habrá que tener una clase _RestServiceApplication_, donde se ejecutará el método `main`, que servirá para arrancar nuestra API. Esta clase tendrá la anotación @SpringBootApplication, que lo que hace es desplegar la aplicación en un servidor web (Tomcat), busca los beans de forma automática sin tener que declararlos, etc.

## Puesta en marcha de la aplicación
Hay diferentes alternativas:
1. Desde el propio IDE, como cualquier otro proyecto de Spring Boot. Es decir, pulsando el botón derecho del ratón en el proyecto, haciendo click sobre 'Run as... > Spring Boot App'.
2. Desde un terminal cualquiera escribiendo los siguientes comandos:
	<br>`$ mvn clean`
	<br>`$ mvn install`
	<br>`$ mvn spring-boot:run`
3. Desde Postman se puede hacer la petición y ver tanto las cabeceras como el cuerpo de la respuesta en diferentes formatos, e incluso guardarla en un fichero.
img1 y img2

## Desarrollo de un CRUD sobre una entidad. Versión 1 (estructura_rutas)
En el siguiente ejemplo se hará una API para la gestión de un conjunto de productos simple. Como campos van a tener un id, el nombre de producto y el precio. También vamos a crear un repositorio en el que insertaremos unos datos de ejemplo.
Para hacer el mapeo de las rutas de nuestro controlador, con las diferentes operaciones CRUD, necesitamos hacer una determinada ruta para cada una de las operaciones dentro del controlador.

- **Read**: La operación de obtención de datos se hará a través de la petición GET, es decir, métodos del controlador que irán anotados con @GetMapping.
- **Create**: Para crear un producto lo que haremos será una petición de tipo POST (@PostMapping).
- **Update**: Para actualizar o editar un producto ya existente, utilizaremos el verbo PUT (@PutMapping).
- **Delete**: Para eliminar un producto, haremos uso de DELETE (@DeleteMapping).

### Algunas anotaciones
- **@RequestBody**: Permite inyectar el cuerpo de la petición en un objeto.
- **@PathVariable**: Nos permite inyectar un fragmento de la URL en una variable.

### Clases
Application
img3
Producto
img4
ProductoRepository
img5
ProductoController
img6

## Desarrollo de un CRUD sobre una entidad. Versión 2