package app;

import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import model.Repartidor;

public class Main {
    public static void main(String[] args) {
        PedidoEncomienda pedido1 = new PedidoEncomienda("12345", "Las Vizcachas #121", "Encomienda", "20x20x15", 3);
        Repartidor repartidor1 = new Repartidor("Juan perez", 5, true);
        Repartidor repartidor2 = new Repartidor("Ana maria", 2, false
        );
        Repartidor repartidor3 = new Repartidor("John Rockefeller", 6, false);
        PedidoComida pedido2 = new PedidoComida("1111", "Vespucio #435", "Comida", "WcDonald's");

        PedidoExpress pedido3 = new PedidoExpress("0091", "Esquina Blanca #665", "Express", 3);

        System.out.println(pedido1.asignarRepartidor(repartidor1));

        System.out.println(pedido2.asignarRepartidor(repartidor2));
        System.out.println(pedido3.asignarRepartidor(repartidor3));

    }
}