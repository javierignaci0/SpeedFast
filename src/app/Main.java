package app;

import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import model.Repartidor;

public class Main {
    public static void main(String[] args) {
        PedidoEncomienda pedido1 = new PedidoEncomienda("12345", "Las Vizcachas #121", 9, "20x20x15", 3);
        PedidoComida pedido2 = new PedidoComida("1111", "Vespucio #435", 10, "WcDonald's");
        PedidoExpress pedido3 = new PedidoExpress("0091", "Esquina Blanca #665", 7, 3);

        Repartidor repartidor1 = new Repartidor("Juan perez", 5, true);
        Repartidor repartidor2 = new Repartidor("Ana maria", 2, true);
        Repartidor repartidor3 = new Repartidor("John Rockefeller", 6, false);

        System.out.println(pedido1.asignarRepartidor(repartidor1));
        System.out.println(pedido1.mostrarResumen());
        pedido1.calcularTiempoEntrega();
        System.out.println();
        System.out.println(pedido2.asignarRepartidor(repartidor2));
        System.out.println(pedido2.mostrarResumen());
        pedido2.calcularTiempoEntrega();
        System.out.println();
        System.out.println(pedido3.asignarRepartidor(repartidor3));
        System.out.println(pedido3.mostrarResumen());
        pedido3.calcularTiempoEntrega();


        System.out.println(repartidor1.verificarMochila());
        System.out.println(repartidor2.verificarMochila());
        System.out.println(repartidor3.verificarMochila());
    }
}