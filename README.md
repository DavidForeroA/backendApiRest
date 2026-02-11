# Backend API Rest - Spring Boot

Proyecto desarrollado como parte de una **prueba técnica para el Banco de Bogotá**.  
Implementa un servicio backend con **Spring Boot** y **API REST**, siguiendo buenas prácticas de arquitectura y desarrollo.

---

## 🚀 Tecnologías utilizadas
- **Java 17**  
- **Spring Boot**  
- **Spring Data JPA / Hibernate**  
- **MySQL** (base de datos relacional)  
- **Maven** (gestión de dependencias y build)

---

## 📂 Estructura del proyecto
- `src/main/java` → Código fuente principal (controladores, servicios, repositorios, entidades).  
- `src/main/resources` → Configuración de la aplicación (application.properties).  
- `pom.xml` → Archivo de configuración de Maven.  
- `.gitignore` → Exclusiones de archivos y carpetas (ej. `target/`).

---

## ⚙️ Configuración inicial
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/DavidForeroA/backendApiRest.git
2. Entrar en la carpeta del proyecto:
cd backendApiRest

3.Configurar la base de datos en  src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/nombre_bd
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update

4. Compilar y ejecutar:
mvn spring-boot:run

📡 Endpoints principales
- GET /api/clientes → Lista todos los clientes.
- GET /api/clientes/{id} → Obtiene un cliente por ID.
- POST /api/clientes → Crea un nuevo cliente.
- PUT /api/clientes/{id} → Actualiza un cliente existente.
- DELETE /api/clientes/{id} → Elimina un cliente.
  Cuentas
- GET /api/cuentas → Lista todas las cuentas.
- GET /api/cuentas/{id} → Obtiene una cuenta por ID.
- POST /api/cuentas → Crea una nueva cuenta.
- PUT /api/cuentas/{id} → Actualiza una cuenta existente.
- DELETE /api/cuentas/{id} → Elimina una cuenta.

 Notas
- Este proyecto fue creado como parte de una prueba técnica.
- Se recomienda usar un entorno local con MySQL para ejecutar correctamente los servicios.
- La rama principal es main.

👨‍💻 Autor
David Forero A.
GitHub (github.com in Bing)

