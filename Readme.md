MyTube (Grupo5)
Este proyecto consta de una API REST, cuyos puntos clave serían:
1. Interfaz de Comunicación: Una API (Application Programming Interface) es una interfaz que permite la comunicación entre diferentes componentes de software. En este proyecto, la API actúa como un intermediario que permite a los usuarios y otros sistemas acceder y manipular datos relacionados con las canciones y la gestión de cuentas en MyTube.
2. REST: es un enfoque arquitectónico que utiliza los protocolos HTTP para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en recursos, que en este caso serían los datos relacionados con las canciones y los usuarios de MyTube.
3. Recursos: En MyTube, los recursos se relacionan con los datos que se pueden acceder o manipular a través de la API, como las canciones y los usuarios.
4. Operaciones CRUD: Los métodos HTTP (GET, POST, PUT y DELETE) se utilizan para realizar operaciones en estos recursos. 
5. Estado Representativo: La "R" en REST se refiere a "Representational." Esto significa que la información sobre los recursos se representa en el cuerpo de la solicitud o respuesta en un formato específico, como JSON o XML. En el caso de esta aplicación, los datos sobre canciones, usuarios, listas de favoritos, etc., se transmitirán en el cuerpo de las solicitudes y respuestas en un formato estructurado.




Los repositorios de los proyectos son los siguientes:
* Servidor : https://github.com/JoanaElorrieta/MyTubeServer
* App: https://github.com/JoanaElorrieta/MyTubeApp 


Descripción del proyecto.
![DiagramaE_R drawio](https://github.com/JoanaElorrieta/MyTubeServer/assets/124193410/f1313dd0-79a7-4317-b06e-12616155afb9)
En este proyecto, se desarrolla una App llamada MyTube. En ella, los usuarios podrán acceder a los vídeos musicales de nuestra app, hacer login y registrarse. 
Si el usuario decide registrarse o hacer login, tendrá acceso a otras gestiones en la app, tales como añadir nuevas canciones o modificar y eliminar canciones existentes.
Por otra parte, cada usuario podrá gestionar su lista de favoritos, pudiendo añadir, modificar y eliminar canciones incluidas en esta.
En concreto , el usuario no identificado podrá:
* Obtener todas las canciones.
* Obtener una canción por ID.
* Hacer login.
* Registrarse.
El usuario identificado podrá:
   * Obtener todas las canciones.
   *  Obtener una canción por ID.
   *  Crear canción.
   * Modificar una canción.
   * Eliminar una canción.
   * Actualizar su contraseña


Además, solo el usuario identificado tendrá acceso a sus propios favoritos, en concreto, solo él podrá:
* Añadir canción como mi favorita.
* Eliminar canción de mis favoritos.
* Obtener su lista de favoritos.

Built With (construido con)
Las tecnologías que se han utilizado para crear toda la capa del servidor:
* Spring Boot (3.1.4)
* MySQL


Getting Started
Prerrequisites (Prerrequisitos para arrancar el proyecto)
Para poder utilizar este proyecto, deberemos tener instalado MYSQL. En la carpeta src/main/resources se encuentran los scripts necesarios para la generación de las tablas de la base de datos , los inserts necesarios para tener datos de muestra y además cuenta con la creación del usuario que se usará como acceso.
Por otra parte, también será necesario tener instalado Android Studio.
Installation (Instalación)
A continuación detallamos la importación del Server y de la App Móvil.
Server
1. Instalación Git Bash
Para poder clonar el proyecto en nuestro ordenador deberemos instalar primeramente Git Bash sin ninguna configuración especial.
2. Clonación del proyecto
Accederemos a la carpeta en la que deseamos clonar el proyecto. En este caso dado que se trata del Server lo clonaremos dentro de la carpeta “\eclipse-workspace” y abriremos la terminal de Git Bash.
        A continuación haremos uso del siguiente comando:
        https://github.com/JoanaElorrieta/MyTubeServer.git
3. Modificar src/main/resources/application.properties las siguientes propiedades:
   1. URL de la BBDD:
“spring.datasource.url=jdbc:mysql://localhost:3306/retomytube” donde “/retomytube será el nombre de la BBDD”
   2. UserName:
“spring.datasource.username=retoMyTube” donde “retoMyTube” será el nombre del usuario con el que logueas.
   3. Password:
“spring.datasource.password=retoMyTube” donde “retoMyTube” será la contraseña del usuario con el que logueas.
   4. Driver de conexión MYSQL:
“spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver”
donde “com.mysql.cj.jdbc.Driver” será el nombre del driver de conexión a la BBDD.
App Móvil
1. Clonación del proyecto
Accederemos a la carpeta en la que deseamos clonar el proyecto. En este caso dado que se trata del Server lo clonaremos dentro de la carpeta “\eclipse-workspace” y abriremos la terminal de Git Bash.
        A continuación haremos uso del siguiente comando:
        https://github.com/JoanaElorrieta/MyTubeApp.git
        
________________
Usage (uso)
Para arrancar el proyecto, deberemos ir al archivo src/main/java/com.reto1.myTube/MyTubeApplication.java y pulsar “Run”.
Un ejemplo de las peticiones que se pueden solicitar a nuestro servidor sería la siguiente:
GET http://localhost:8080/songs
Esta petición te devolvería todas las canciones que se encuentran en la base de datos


Para la realización de las pruebas referentes a las peticiones al Servidor realizamos una exportación de las colecciones creadas con anterioridad en Postman en  “src/main/resources/RetoMyTube.postman_collection.json”.

API Documentation (Documentación)
En el enlace http://localhost:8080/swagger-ui/index.html#/ se pueden consultar las peticiones que se pueden realizar al servidor. Estas peticiones están generadas por swagger y no se necesita ningún tipo de login para verlas.
Estas solo están disponibles cuando el servidor está arrancado.


Contact (Contacto)
Joana Barber Montaner (joana.barbermo@elorrieta-errekamari.com)
David Comerón Alonso (david.comeronal@elorrieta-errekamari.com)


License (Licencia)
Distributed under the MIT License.
