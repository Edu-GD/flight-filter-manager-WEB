# ✈️ Flight Filter Manager WEB

Esta es una API REST desarrollada en **Java con Spring Boot** para la gestión y filtrado de vuelos. El sistema permite realizar operaciones CRUD completas sobre un repositorio de vuelos en memoria, aplicando filtros avanzados y lógica de ordenación.

## 🚀 Características Principales

* **CRUD Completo**: Creación, lectura, actualización y eliminación de vuelos.
* **Filtrado Avanzado**: Búsqueda por origen, destino, empresa y fechas.
* **Ordenación Inteligente**: Por defecto, los vuelos se entregan ordenados por fecha de salida. Permite ordenación personalizada por empresa o destino.
* **Cálculo Automático**: Determina la duración del vuelo en días entre la fecha de salida y llegada.
* **Gestión de Errores**: Respuestas estandarizadas mediante un `GlobalExceptionHandler`.

## 🛠️ Tecnologías Utilizadas

* **Java 21**
* **Spring Boot 4.0.0**
* **Maven**
* **Postman**

## 📥 Instalación y Ejecución

1.  **Clonar** el repositorio.
2.  Abrir en su IDE de preferencia (recomendado **IntelliJ IDEA**).
3.  Ejecutar la clase `DemoApplication.java`.
4.  La API estará disponible en: `http://localhost:8080/vuelos`

## 🧪 Pruebas con Postman

He incluido una colección de Postman para facilitar la revisión de los endpoints:
1.  Localiza el archivo en: `/.01-postman/flight-filter-manager-v2.postman_collection.json`.
2.  Impórtalo en Postman (File > Import).
3.  La colección contiene pruebas para **GET, POST, PUT y DELETE**.

## 📡 Endpoints Principales

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/vuelos` | Listado completo ordenado por fecha. |
| **GET** | `/vuelos/{id}` | Buscar un vuelo por ID. |
| **POST** | `/vuelos` | Crear nuevo vuelo (ID autoincremental). |
| **PUT** | `/vuelos/{id}` | Actualizar un vuelo existente. |
| **DELETE** | `/vuelos/{id}` | Eliminar un vuelo. |

## ✒️ Autor
**Edu Garcia Devesa** - *Desarrollo de API REST con Spring Boot* - [Hack a Boss]
