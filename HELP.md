# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '11' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

Proyecto Final Codo a Codo Spring Comisión N° 22812 - Alumna: Irina Ponzi

API para ser consumida por distribuidoras de libros. 
Permite guardar, buscar, actualizar y eliminar editoriales y los respectivos libros que pertenecen a ellas.

Bases de datos:
- Los test se corren automáticamente con el scope "integration_test" (base de datos en H2).
- La aplicación puede ser ejecutada desde el scope "prod" (base en H2), o desde el scope "local" (base en MySQL). 
Para esta última opción es necesario previamente crear en MySQL un esquema vacío con el nombre "existenciasDB".

Para Postman:

/* {
      "name": "Nombre Editorial",
      "businessName": "Razón Social",
      "cuit": "10-10000001-1",
      "city": "Buenos Aires",
      "address": "Direccion 2350",
      "telephone": "48228558",
      "email":
          {
              "description": "mail@mail.com"
          },     
      "books": [
          {
              "title": "Titulo Libro",
              "author": "Autor Libro",
              "publicationDate": "2020",
              "stock": 80
          },
          {
              "title": "Titulo Libro",
              "author": "Autor Libro",
              "publicationDate":1997,
              "stock": 60
          }
      ]
    } */

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/#build-image)

