# SpeedFast - Sistema de Gestión de Entregas

SpeedFast es una aplicación de escritorio desarrollada en Java diseñada para administrar la logística básica
de un sistema de despachos. Permite gestionar de manera eficiente los pedidos, los repartidores y la asignación de
entregas mediante una interfaz gráfica intuitiva conectada a una base de datos relacional.

## Características Principales

El sistema está dividido en tres módulos fundamentales integrados en una única ventana de control:

*   **Gestión de Pedidos:** Creación, edición, eliminación y visualización de pedidos. Incluye validaciones de estado para evitar alteraciones en despachos ya completados.
*   **Gestión de Repartidores:** Registro y actualización del personal encargado de realizar los despachos.
*   **Control de Entregas:** Asignación relacional entre pedidos pendientes y repartidores disponibles. 
      Al registrar una entrega exitosa, el sistema actualiza automáticamente el estado del pedido a "ENTREGADO".

## Arquitectura y Tecnologías

El proyecto esta estructurado bajo el patrón de diseño **MVC (Modelo-Vista-Controlador)** en conjunto con el
patrón **DAO (Data Access Object)**, garantizando una separación limpia entre la interfaz de usuario, la lógica de negocio y el acceso a los datos.

*   **Lenguaje:** Java
*   **Interfaz Gráfica:** Java Swing
*   **Base de Datos:** MySQL
*   **Conectividad:** JDBC (Java Database Connectivity)
*   **Entorno de Desarrollo:** Diseñado y probado en IntelliJ IDEA

## Estructura del Proyecto

```text
src/
├── controller/        # Clases intermediarias entre la Vista y los DAO
├── dao/               # Interfaces DAO y sus implementaciones (impl/) para consultas SQL
├── model/             # Entidades del sistema (Pedido, Repartidor, Entrega)
├── util/              # Clases utilitarias (ej. ConexionDB)
└── view/              # Interfaces graficas (VentanaAlternativa.java y .form)
```

## Como ejecutar:

1. Clonar el repositorio:
2. Configurar la Base de Datos: Abre tu gestor de base de datos (ej. MySQL Workbench, DBeaver o DBeaver).
3. Crea la base de datos: Nombre por defecto: speedfast_db
4. Ejecuta el script SQL `SpeedFast_s3_script_tablas_bbdd` incluido en el proyecto para crear las tablas (pedidos, repartidores, entregas).
5. Configurar las Credenciales:
   - Dirígete a la clase `ConexionDB` (dentro del paquete util). 
   - Actualiza las variables de conexión `(URL, USER, PASSWORD)` con las credenciales de tu servidor MySQL local.
6. Ejecutar la Aplicación:
   - Localiza la clase principal (usualmente Main.java o la clase que contiene tu JFrame principal). 
   - Ejecuta el método `Main` para lanzar la interfaz gráfica.

## Conceptos aplicados

- Programación orientada a objetos.
- Operaciones CRUD.
- Persistencia de datos con conexion a base de datos
- Java Swing
- Organización modular con modelo MVC.

## Autor
Javier R.
Proyecto académico desarrollado como tarea evaluada para aplicar conceptos de Java, MVC, acceso y operaciones a base de datos y programación orientada a objetos.