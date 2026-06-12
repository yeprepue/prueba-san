# prueba-SAN — API REST de Gestión Empresarial

API REST construida con **Spring Boot 3** y **Java 21** para gestionar clientes, productos y contratos, siguiendo los principios de **Arquitectura Hexagonal (Clean Architecture)**.

---

## Tabla de Contenidos

- [¿Qué hace esta aplicación?](#qué-hace-esta-aplicación)
- [Arquitectura](#arquitectura)
- [Requisitos previos](#requisitos-previos)
- [Clonar el proyecto](#clonar-el-proyecto)
- [Configuración de base de datos](#configuración-de-base-de-datos)
- [Configuración de la aplicación](#configuración-de-la-aplicación)
- [Ejecutar la aplicación](#ejecutar-la-aplicación)
- [Verificar que funciona](#verificar-que-funciona)
- [Endpoints disponibles](#endpoints-disponibles)
- [Variables de entorno](#variables-de-entorno)
- [¿Por qué no Docker?](#por-qué-no-docker)
- [Roadmap / Pendientes](#roadmap--pendientes)

---

## ¿Qué hace esta aplicación?

API REST para gestionar entidades empresariales:

| Módulo | Estado | Operaciones |
|--------|--------|-------------|
| Clientes | ✅ Disponible | Crear, leer, actualizar, eliminar |
| Productos | 🔜 Pendiente | — |
| Contratos | 🔜 Pendiente | — |

---

## Arquitectura

El proyecto sigue **Arquitectura Hexagonal** con tres capas bien diferenciadas:

```
prueba-SAN/
├── domain/                  # Reglas de negocio puras (sin dependencias externas)
│   ├── Cliente.java
│   └── ClienteRepository.java   ← interfaz (puerto)
│
├── application/             # Casos de uso — orquesta el dominio
│   └── GetClienteService.java
│
└── infrastructure/          # Adaptadores — tecnología concreta
    ├── ClienteController.java        ← entrada HTTP (Spring MVC)
    └── ClienteRepositoryAdapter.java ← salida a BD (Spring Data JPA)
```

> **Beneficio clave:** Puedes cambiar de base de datos o de framework sin tocar la lógica de negocio.

---

## Requisitos previos

### Hardware mínimo

| Recurso | Mínimo | Recomendado |
|---------|--------|-------------|
| RAM | 4 GB | 8 GB |
| Disco libre | 500 MB | 1 GB |
| CPU | Cualquier procesador moderno | — |

### Software necesario

| Software | Versión | Descarga |
|----------|---------|----------|
| Java JDK | 21 | [adoptium.net](https://adoptium.net/) |
| SQL Server | 2019 o superior | [microsoft.com](https://www.microsoft.com/sql-server) |
| Git | Cualquier versión reciente | [git-scm.com](https://git-scm.com/) |
| Maven | 3.6+ (opcional — el proyecto incluye `mvnw`) | [maven.apache.org](https://maven.apache.org/) |

> Maven **no es obligatorio**: el proyecto incluye el wrapper `mvnw` / `mvnw.cmd` que lo descarga automáticamente.

---

## Clonar el proyecto

```bash
# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/prueba-SAN.git

# 2. Entrar al directorio
cd prueba-SAN
```

Verifica que Java 21 esté activo:

```bash
java -version
# Debe mostrar: openjdk version "21.x.x" ...
```

---

## Configuración de base de datos

### 1. Verificar que SQL Server esté corriendo

Abre **SQL Server Configuration Manager** (o Services de Windows) y confirma que el servicio `SQL Server (MSSQLSERVER)` está en estado **Running**.

### 2. Crear la base de datos

Ejecuta en SQL Server Management Studio o en DBeaver:

```sql
CREATE DATABASE prueba_san;
```

> Si ya tienes la base de datos `prueba_san`, omite este paso.

### 3. Instalar la DLL de autenticación Windows

La aplicación usa **autenticación integrada de Windows** (sin usuario/contraseña). Para ello necesitas la DLL:

```
sqljdbc_auth.dll  →  C:\Windows\System32\
```

Si no la tienes, descárgala del [Microsoft JDBC Driver for SQL Server](https://learn.microsoft.com/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server) y copia la versión correcta según tu arquitectura:

| Arquitectura | Ruta dentro del ZIP |
|---|---|
| 64-bit (recomendado) | `enu\auth\x64\sqljdbc_auth.dll` |
| 32-bit | `enu\auth\x86\sqljdbc_auth.dll` |

---

## Configuración de la aplicación

El archivo de configuración principal es `src/main/resources/application.properties` (o `application.yml`):

```properties
# Puerto de la API
server.port=9090

# Conexión a SQL Server con autenticación Windows
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=prueba_san;integratedSecurity=true;trustServerCertificate=true
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Para sobrescribir valores sin tocar el archivo, usa [variables de entorno](#variables-de-entorno).

---

## Ejecutar la aplicación

### Opción 1 — Desarrollo (recomendada)

```bash
# Windows
.\mvnw spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

### Opción 2 — JAR compilado (producción)

```bash
# Compilar (omitiendo tests)
.\mvnw clean package -DskipTests

# Ejecutar el JAR generado
java -jar target\prueba-san-0.0.1-SNAPSHOT.jar
```

---

## Verificar que funciona

Al arrancar correctamente verás en la consola:

```
✅ Conexión exitosa a SQL Server!
...
Started PruebaSanApplication in X.XXX seconds
```

La API queda disponible en: `http://localhost:9090`

### Prueba rápida con curl

```bash
# Listar todos los clientes
curl -X GET http://localhost:9090/api/clientes

# Crear un cliente
curl -X POST http://localhost:9090/api/clientes \
     -H "Content-Type: application/json" \
     -d '{"nombre": "Juan Pérez", "email": "juan@ejemplo.com"}'
```

---

## Endpoints disponibles

Base URL: `http://localhost:9090/api`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/clientes` | Listar todos los clientes |
| `GET` | `/clientes/{id}` | Obtener cliente por ID |
| `POST` | `/clientes` | Crear nuevo cliente |
| `PUT` | `/clientes/{id}` | Actualizar cliente existente |
| `DELETE` | `/clientes/{id}` | Eliminar cliente |

> Puedes probar estos endpoints en **Postman** importando la colección o creando las peticiones manualmente.

---

## Variables de entorno

Puedes sobrescribir la configuración por defecto sin editar archivos:

| Variable | Valor por defecto | Descripción |
|----------|-------------------|-------------|
| `PORT` | `9090` | Puerto en que escucha la API |
| `DATABASE_URL` | `jdbc:sqlserver://localhost:1433;databaseName=prueba_san;integratedSecurity=true` | Cadena de conexión a la BD |

**Ejemplo en Windows (PowerShell):**

```powershell
$env:PORT = "8080"
$env:DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=prueba_san;integratedSecurity=true"
.\mvnw spring-boot:run
```

---

## ¿Por qué no Docker?

Este proyecto **no requiere Docker** por tres razones:

1. **Autenticación Windows integrada** — SQL Server usa `integratedSecurity=true`, que depende de las credenciales del usuario de Windows. Dentro de un contenedor Linux esto no funciona directamente.

2. **La DLL de Windows no corre en Linux** — `sqljdbc_auth.dll` es un binario nativo de Windows; en un contenedor Linux habría que cambiar a autenticación por usuario/contraseña y usar SQL Server en contenedor, añadiendo complejidad innecesaria.

3. **Arquitectura simple** — no hay Redis, Kafka, Elasticsearch ni otros servicios. Un `java -jar` es suficiente.

<details>
<summary>Si en el futuro quisieras dockerizar…</summary>

Habría que:
- Cambiar a autenticación SQL Server (usuario/contraseña) en `application.properties`
- Levantar SQL Server en contenedor o en la nube
- Crear un `Dockerfile`:

```dockerfile
FROM openjdk:21-jdk-slim
COPY target/prueba-san-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

- Y un `docker-compose.yml` para orquestar la app + SQL Server.

</details>

---

## Roadmap / Pendientes

| Tarea | Estado |
|-------|--------|
| CRUD de Clientes | ✅ Completo |
| Adaptadores de Producto y Contrato | ❌ Pendiente |
| Servicios de Producto y Contrato | ❌ Pendiente |
| Controladores de Producto y Contrato | ❌ Pendiente |
| Pruebas unitarias | ❌ Pendiente |
| Documentación de API (Swagger / OpenAPI) | ❌ Pendiente |

---

## Solución de problemas comunes

| Error | Causa probable | Solución |
|-------|---------------|----------|
| `This driver is not configured for integrated authentication` | DLL no instalada o en ruta incorrecta | Copia `sqljdbc_auth.dll` a `C:\Windows\System32\` |
| `Cannot open database "prueba_san"` | BD no existe | Ejecuta `CREATE DATABASE prueba_san` en SQL Server |
| `Connection refused` en el puerto 1433 | SQL Server detenido | Inicia el servicio en SQL Server Configuration Manager |
| Puerto 9090 en uso | Otro proceso ocupa el puerto | Cambia el puerto con `$env:PORT=8081` o en `application.properties` |

---

*Generado para el proyecto **prueba-SAN** — Spring Boot 3 + Java 21 + SQL Server*
