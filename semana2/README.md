
# Sistema de Asignación de Pedidos

Proyecto desarrollado en Java para practicar programación orientada a objetos, herencia, polimorfismo y sobrecarga de métodos.

## Descripción

El programa permite representar distintos tipos de pedidos y asignarlos a repartidores según las condiciones necesarias para cada pedido.

Los tipos de pedido considerados son:

- Encomienda.
- Comida.
- Express.

Cada tipo de pedido tiene sus propias reglas de asignación:

- La encomienda verifica las medidas y el peso del pedido.
- La comida verifica si el repartidor tiene mochila térmica.
- El pedido express verifica la distancia del repartidor.

## Estructura del proyecto

```text
src/
├── app/
│   └── Main.java
├── data/
└── model/
    ├── Pedido.java
    ├── PedidoEncomienda.java
    ├── PedidoComida.java
    ├── PedidoExpress.java
    └── Repartidor.java
```

## Clases principales

### `Pedido`

Clase base para representar la información común de los pedidos, como:

- ID del pedido.
- Dirección de entrega.
- Tipo de pedido.

Contiene un metodo llamado `mostrarResumen()` que entrega informacion del pedido por consola.

Ademas, tambien incluye metodos abstractos como:

- `asignarRepartidor(Repartidor repartidor)`
- `calcularTiempoEntrega()`

### `PedidoEncomienda`

Representa una encomienda y valida sus medidas y peso antes de asignarla a un repartidor. Tiene una formula de tiempo
de entrega de base 20 minutos + (1.5 minutos por cada 1 kilometro de distancia).

### `PedidoComida`

Representa un pedido de comida y verifica que el repartidor tenga mochila térmica. Tiene una formula de tiempo
de entrega de base 15 minutos + (2 minutos por cada 1 kilometro de distancia)

### `PedidoExpress`

Representa un pedido express, tiene una formula de tiempo de entrega de base 10 minutos + 5 minutos si la distancia es 
mayor a 5 kilometros.

### `Repartidor`

Representa a un repartidor con los siguientes datos:

- Nombre.
- Distancia.
- Disponibilidad de mochila térmica.

También contiene el método `verificarMochila()`, que indica si el repartidor tiene o no una mochila térmica.

## Conceptos utilizados

- Clases y objetos.
- Constructores.
- Encapsulamiento.
- Herencia.
- Polimorfismo.
- Abstraccion.
- Sobrescritura de métodos con `@Override`.
- Sobrecarga de métodos y constructores.
- Condicionales `if-else`.
- Uso de valores booleanos.

## Ejecución

1. Abrir el proyecto en IntelliJ IDEA, NetBeans o Eclipse.
2. Verificar que el proyecto utilice un JDK compatible.
3. Ejecutar la clase:

```text
src/app/Main.java
```

4. Revisar los resultados en la consola.

## Ejemplo de uso

```java
PedidoComida pedido1 = 
        new PedidoComida("1111", "Vespucio #435", 10, "WcDonald's");

PedidoEncomienda pedido2 = 
        new PedidoEncomienda("12345", "Las Vizcachas #121", 9, "20x20x15", 3);

Repartidor repartidor1 =
        new Repartidor("Juan perez", 5, true);

Repartidor repartidor2 =
        new Repartidor("Ana maria", 2, false);

System.out.println(pedido1.asignarRepartidor(repartidor1));
System.out.println(pedido1.mostrarResumen());
pedido1.calcularTiempoEntrega();

System.out.println(pedido2.asignarRepartidor(repartidor2));
System.out.println(pedido2.mostrarResumen());
pedido2.calcularTiempoEntrega();

```

Un repartidor con mochila térmica puede recibir un pedido de comida:

```text
Verificando mochila térmica...OK
```

Un repartidor sin mochila térmica no puede recibirlo:

```text
Verificando mochila térmica...No tiene Mochila
```

## Autor
Javier R.

Proyecto académico desarrollado como actividad formativa de desarrollo orientado a objetos II en Java.
