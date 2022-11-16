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

![img1](https://user-images.githubusercontent.com/98974760/201521174-3242df62-ac03-4889-84ca-dbfcf1ab96da.PNG)
![img2](https://user-images.githubusercontent.com/98974760/201521182-21e42c6c-6db6-4386-bfd4-1b575ef5ef61.PNG)

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

![img3](https://user-images.githubusercontent.com/98974760/201521192-179c4af5-5387-4e6b-b846-8443402edc47.PNG)

Producto

![img4](https://user-images.githubusercontent.com/98974760/201521203-d6509e9e-34f8-4ff5-a0c8-690fbebb2658.PNG)

ProductoRepository

![img5](https://user-images.githubusercontent.com/98974760/201521209-0dceb72f-8f3d-410c-af57-e7753ea64004.PNG)

ProductoController

![img6](https://user-images.githubusercontent.com/98974760/201521211-a72af622-cee8-4739-8d8e-ffcacd16117d.PNG)



## Desarrollo de un CRUD sobre una entidad. Versión 2
Tomando el ejemplo anterior (estructura_rutas), y las clases ya implementadas con sus anotaciones correspondientes, hay otra serie de clases que necesitamos añadirle. Estas son:
- **ResponseEntity<T>**: Nos permite manejar de una forma más conveniente la respuesta que enviamos al cliente. Hereda de _HttpEntity<T>_. Nos permite indicar el código de respuesta, qué se envía en el cuerpo, responder peticiones sin el mismo, etc.

Por tanto, el ejemplo anterior lo modificaremos añadiendo algunas funcionalidades al controlador, para que aparezcan los códigos de retorno adecuados:
- **@GetMapping**: Devolveremos 200 OK si localizamos el recurso; si no, devolveremos 404 Not Found.
- **@PostMapping**: Devolveremos 201 Created.
- **@PutMapping**: Devolveremos 200 OK si localizamos y modificamos el recurso; si no, devolveremos 404 Not Found.
- **@DeleteMapping**: Devolveremos 204 No Content.



## Desarrollo de un CRUD sobre una entidad. Versión 3
Las **Entidades** son clases Java con algunas anotaciones más, cumplen una serie de requisitos y se encargan de modelar nuestros objetos en la capa lógica de negocio. Según los expertos, es buena práctica no usar estas entidades en el resto de capas. Estas se dejarán para la lógica de negocio, y para otras capas se utilizarán otras clases, como por ejemplo las ***Data Transfer Object (DTO)***.

Estas DTO son objectos POJO que agrupan datos de la capa de negocio. Pueden tener parte de los datos de una sola entidad o de más de una, o bien aglutinar todos los datos de varias entidades. A veces se les conoce como VO (_Value Object_). Están pensadas para aligerar las transacciones entre cliente/servidor.

Las características de un DTO es que va a ser una clase muy sencilla, va a ser un objeto plano que no va a tener nada de lógica de negocio (puede tener getter, setter y los constructores necesarios para facilitar el trabajo), tiene que ser serializable para que pueda viajar a través de la red (aunque esto no es extricamente obligatorio).

Esta clase DTO se utilizará cuando no queramos obtener toda la información de alguna clase, sino solo algunos datos, o incluso datos convinados de varias clases. Se hará, por ejemplo, en los métodos GET, para obtener así los datos deseados, pudiendo haber más de un DTO distinto para una sola entidad, dependiendo del tipo de consulta que se vaya a hacer.

Se puede implementar DTO de varias formas:
- Manualmente: Instanciamos el nuevo objeto y, mediante los getter y los setter, vamos asignando los datos. Con la anotación @Builder de Lombok se nos genera un _builder_ en el que, a través de métodos encadenados, podemos construir un objeto.
- ModelMapper: Librería que hace el trabajo de transformar una clase en otra.
- JsonViews: A través de anotaciones, un mismo objeto puede devolver más o menos datos.

### DTO con ModelMapper
Nos evita código muy repetitivo, ya que facilita la creación de DTO mediante asignación dinámica. Para usar _ModelMapper_ solo hay que añadir al pom.xml la dependencia correspondiente con la versión más actualizada que se quisiera tener. Como configuración básica, se puede crear un Bean que nos devuelva el ModelMapper para poder utilizarlo donde lo necesitemos.

Para hacer la transformación nos creamos un componente independiente, que inyectaremos para usar donde haga falta. Y esto lo utilizaremos dentro de un controlador.

Para dicha transformación, tan solo basta con utilizar el método _map_ cuyos parámetros de entrada son dos: el producto y la clase a la que se quiere transformar (ProductoDTO.class).