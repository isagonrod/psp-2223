# Actividad 1. Introducción

## Entorno de desarrollo
Spring es un Framework de Java, que para utilizarlo, dependiendo del IDE que queramos utilizar, será necesario instalarlo o no. Por ejemplo, en el curso de OpenWebinars se ha utilizado el Spring Tool Suite de Eclipse, el cual es necesario instalarlo en el IDE.<br>
Si queremos utilizar IntelliJ IDE nos encontraremos con dos opciones, o bien tenemos la versión de pago, por lo cual no necesitaremos intalarle nada porque ya lo trae por defecto; o bien, si solo disponemos de la versión gratuita, tendríamos que ponerle un plugin.<br>
Otra opción es crearnos el proyecto desde el servicio Starter e importarlo directamente a nuestro IDE como Maven y funcionaría sin problema.

### Spring Tool Suite 4 de Eclipse
Está disponible para Linux, MacOs y Windows. Solo es necesario ir a la web spring.io/tools y descargar el fichero para el sistema operativo en el que vayamos a trabajar. Después hay que descomprimirlo y vincularlo con el IDE.<br>
La versión mínima de Java necesaria es la 8, que tendrá que estar instalada para poder ejecutar Eclipse con Tool Suite.

### Spring Boot
Facilita la creación de aplicaciones basadas en Spring independientes y listas para usar, con un mínimo esfuerzo. Lo caracteriza la fácil gestión del pom.xml, el servidor embebido (es decir, que no hay que depender de un servidor externo, sino que lo tendremos accesible desde el propio IDE), las dependencias iniciales que facilitan la configuración de componentes, la configuración de librerías de 3ºs y la no generación de código o configuración XML.

### Spring Initilizr
Spring nos ofrece un servicio para la generación rápida del esqueleto de una aplicación. Se puede realizar desde la web https://start.spring.io o desde Spring Tool Suite directamente si ya disponemos de él en nuestro IDE.<br>
Por ejemplo, esta herramienta se tendrá que utilizar si se utiliza el IntelliJ IDE en su versión gratuita.

### Postman
Es un cliente que nos ayuda al desarrollo de APIs REST y a interactuar con ellas. Existen versiones tanto para Linux como para MacOs o Windows.

## Project Lombok
Ayuda con las clases del modelo, autogenerando los getters, los setters, el constructor, y otros métodos como toString, hashCode, equals, etc.<br>
Ahora nuestras clases estarán compuestas de sus propiedades (también llamados atributos) y un conjunto de anotaciones (@Data, @AllArgsConstructor, @NoArgsConstructor...).

### Anotaciones
Lombok tiene algunas anotaciones clave, como:
- **@Getter**: Nos permite generar los métodos getter de nuestras propiedades.
- **#Setter**: Nos permite generar los métodos setter de nuestras propiedades.
- **@RequiredArgsConstructor**: Nos generará un constructor con los argumentos especificados como "not null" o "final", es decir, con los que son obligatorios.
- **@AllArgsConstructor**: Nos permite generar un constructor con todos los argumentos.
- **@NoArgsConstructor**: Nos permite generar un constructor sin argumentos.
- **@EqualsAndHashCode**: Nos permite generar los métodos equals() y hashCode().
- **@ToString**: Nos permite generar el método toString().
También tiene algunas anotaciones derivadas, que agregan la funcionalidad de más de una anotación de las anteriores, como por ejemplo **@Data** que equivale a @Getter, @Setter, @RequiredArgsConstructor, @EqualsAndHashCode y @ToString.

### Descarga e instalación
En la web https://projectlombok.org/download, nos descargamos Lombok. Lo que hay que hacer es instalar un pequeño complemento en Spring Tool Suite o en Eclipse a través de la librería descargada. Desde el terminal solo tendremos que ejecutarla con el siguiente comando (si estamos en Linux):
<br>`java -jar lombok.jar`<br>
Intentará encontrar el IDE instalado, si no lo encuentra por sí mismo, se pulsa en "Specify location" y se busca manualmente dónde se encuentra el Spring Tool Suite. Y ya quedaría instalado a falta de incluir Lombok en nuestros proyectos.

## Spring Starter Project
1. Creamos un nuevo proyecto de Spring.

![img1](https://user-images.githubusercontent.com/98974760/201468921-bb7d70a8-5eea-4ee5-9230-f87973f44c8f.PNG)

2. Añadimos las dependencias Lombok y Spring Web.

![img2](https://user-images.githubusercontent.com/98974760/201468937-5faf989a-7f60-4fc6-8e92-817a95e6f4e6.PNG)

3. Creamos una clase Persona de ejemplo y observamos cómo Lombok inyecta los métodos getters, setters, etc. gracias a las anotaciones @Data, @AllArgsConstructor y @NoArgsConstructor.

![img3](https://user-images.githubusercontent.com/98974760/201468944-20d131af-b5bc-4a7a-93b9-f1b301adb8f7.PNG)

4. Creamos una clase Application, que será la principal (donde estará el main), con las siguientes anotciones:
	- **@SpringBootApplication**: Ésta engloba otras anotaciones, como @Target(value={TYPE}), @SpringBootConfiguration, @EnableAutoConfiguration (que habilita el contexto para acceder a los beans), @ComponentScan (que busca los beans escaneando), @Retention(value=RUNTIME), @Documented y @Inherited.
	- **@Bean**: Anotación utilizada en un método concreto para crear una instancia del bean devuelto por el mismo, la cual va a ser gestionada por el contenedor de IoC de Spring. 

![img4](https://user-images.githubusercontent.com/98974760/201468948-ddc32f5a-c9d9-478a-b3d5-2d6cb4a67a29.PNG)
	
Si se comenta esta anotación, Spring no gestionará el objeto devuelto por el método, por lo que se ejecutaría el código del método, y la ejecución de la aplicación mostraría que no se ejecuta el método.

![img5](https://user-images.githubusercontent.com/98974760/201468956-269192c1-676f-4eae-af56-127351226186.PNG)
	
Si no se comenta la anotación @Bean, Spring gestiona el bean y, al tratarse de una implementación de CommandLineRunner, se ejecutaría sin problema.

5. Ejecutamos el proyecto, con la anotación @Bean descomentada, y observamos que, por un lado, se ha ejecutado el método lombokTest y por otro lado, están operativos los métodos constructores (sin argumentos y con todos los argumentos) de nuestra clase Persona, y también los métodos toString() y equals().

![img6](https://user-images.githubusercontent.com/98974760/201468959-ce5885d6-b31a-4d42-8abe-6e946e41eb94.PNG)

## Soporte para REST
Spring Boot nos ofrece un soporte específico para REST con anotaciones como **@RestController**, que nos permite tener una combinación de @Controller y @ResponseBody, es decir, se modifica el mecanismo de renderización de la vista y, en lugar de redirigirnos a una plantilla Thymeleaf, JSP..., se devuelve directamente el contenido que se envía al cliente.<br>
El **HttpMessageConverter** es un tipo de Beans especial que se encarga de formatear el contenido que se le entregará al cliente. Al incluir la dependecia _starter web_, se añadirán algunas librerías (entre ellas Jackson2) y algunos conversores, como puede ser **MappingJackson[2]HttpMessageConverter** que lo que hace es convertir una clase Java en una cadena Json.

### Clases que se utilizarán en el curso
- **HttpEntity<T>**: representa una petición o respuesta HTTP. Tiene dos concreciones: **RequestEntity<T>** y **ResponseEntity<T>** (esta última añade el código de estado (_HttpStatus_)).
- **MediaType**: subclase de MIME. Tiene un listado de constantes.
- **HttpHeaders**: representa los encabezados de una petición o de una respuesta.
	
### APIs externas
Spring también te permite construir una API REST que llama a otra API externa, mediante la clase **RestTemplate**, que nos permitirá hacer peticiones de todo tipo y aprovecha los diferentes _converters_ que se han explicado antes.
