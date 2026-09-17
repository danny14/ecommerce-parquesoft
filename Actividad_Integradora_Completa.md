# 📝 GRAN RETO INTEGRADOR: Expansión del E-Commerce (Pedidos y Categorías)

**Curso:** Java 2 - Spring Boot
**Módulo:** E-Commerce API (Consolidación Sesiones 7, 8, 9 y 10)
**Objetivo:** Consolidar todo lo aprendido sobre Arquitectura Limpia, Servicios, Controladores y Pruebas Unitarias. En este reto deberás construir **dos módulos completos** para nuestro E-Commerce y prepararlos para la documentación web.

---

## 🏗️ MÓDULO 1: Categorías (El Calentamiento)
Para mantener nuestros productos organizados, necesitamos un catálogo de Categorías. Construye el flujo CRUD completo:

**1. Entidad y Repositorio:**
*   Crea `CategoryEntity` (`id`, `name` único y obligatorio, `description`, `active`).
*   Crea `CategoryRepository` (Agrega un método para buscar por nombre: `findByName`).

**2. Servicio (Lógica) y Controlador:**
*   Crea `CategoryService`. **Regla de Negocio:** Al crear una categoría, valida que el nombre no venga vacío o nulo. Si lo está, lanza una `IllegalArgumentException`.
*   Crea `CategoryController` en la ruta `/api/categories` exponiendo los verbos GET, POST, PUT y DELETE.

---

## 🔥 MÓDULO 2: Pedidos (El Gran Reto)
Este es el corazón del E-Commerce: unir a un Cliente con una compra. Construye el flujo CRUD completo:

**1. Entidad y Repositorio:**
*   Crea `OrderEntity` (`id`, `customerId`, `orderDate`, `totalAmount`, `status` por defecto "PENDING").
*   Crea `OrderRepository` (Agrega un método para buscar los pedidos de un cliente: `findByCustomerId`).

**2. Servicio (Lógica) y Controlador:**
*   Crea `OrderService`. **Regla de Negocio:** El `totalAmount` (Total a pagar) NO puede ser menor o igual a cero. Si lo es, lanza una excepción. Además, todo pedido nuevo siempre debe guardarse con el `status` = "PENDING".
*   Crea `OrderController` en la ruta `/api/orders` exponiendo tus endpoints.
*   **Prueba Práctica:** Usa Postman para crear una Categoría y un Pedido.

---

## 🛡️ PARTE 3: Asegurando la Calidad (JUnit & Mockito - Sesión 9)

Como buen desarrollador Senior, debes garantizar que tu lógica no falle. Ve a tu carpeta de pruebas (Test) y crea `OrderServiceTest.java`.
*   Usa `@Mock` para aislar el repositorio y `@InjectMocks` para tu servicio.
*   **El Reto de Prueba:** Escribe un test llamado `testCreateOrder_ThrowsException_WhenTotalIsZeroOrNegative`.
*   Simula (Arrange) un pedido falso con `totalAmount = -50.0`. Usa `assertThrows` (Act & Assert) para confirmar que la aplicación "explota" correctamente, y usa `verify(..., never()).save(...)` para garantizar que la base de datos nunca fue llamada.

---

## 📖 PARTE 4: Hacia el Futuro (Swagger - Preparación Sesión 11)

Vamos a documentar esta API para que los equipos de Front-End puedan leerla fácilmente.

**1. Instalar Swagger:**
Agrega esta dependencia a tu `pom.xml` y recarga Maven.
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

**2. Descubrir la Magia:**
*   Ejecuta Spring Boot y entra a: `http://localhost:8080/swagger-ui.html`
*   Explora visualmente los controladores que construiste e intenta crear un Pedido directamente desde esta página.

**3. Documentación Profesional:**
Ve a tu `OrderController` y pon esta anotación sobre tu método `@PostMapping`:
```java
@Operation(summary = "Crear un nuevo pedido", description = "Valida que el total sea mayor a 0 y asigna el estado PENDING.")
```
Refresca la página de Swagger y contempla tu trabajo.
