package app;

import model.*;
import Controlador.*;

public class Main {
    public static void main(String[] args) {
        ControladorEnvios controlador = new ControladorEnvios();

        PedidoComida pedido1 = new PedidoComida(1, "Levi Ackermann", "Muralla Maria 111", "Cocina China", 4);
        PedidoEncomienda pedido2 = new PedidoEncomienda(2, "Erwin Smith", "Cuartel Legion", "Cuartel Policial", 10);
        PedidoExpress pedido3 = new PedidoExpress(3, "Eren Jaeger", "Muralla maria 1203", "Oxxo", true);


        controlador.reservarPedido(pedido1);
        controlador.reservarPedido(pedido2);
        controlador.reservarPedido(pedido3);

        System.out.println("--- Asignación automatica de repartidores ---");
        pedido1.asignarRepartidor();
        pedido2.asignarRepartidor();
        pedido3.asignarRepartidor();

        System.out.println();

        System.out.println("--- Calculo de tiempos de repartoestimados ---");
        System.out.println("Pedido 1 (Comida): " + pedido1.calcularTiempoEntrega() + " min");
        System.out.println("Pedido 2 (Encomienda): " + pedido2.calcularTiempoEntrega() + " min");
        System.out.println("Pedido 3 (Express): " + pedido3.calcularTiempoEntrega() + " min");

        System.out.println();

        System.out.println("--- Asignacion manual de repartidor ---");
        pedido1.asignarRepartidor("Mikasa");

        System.out.println();
        System.out.println("--- Despacho de pedidos ---");
        controlador.despacharPedido(1);
        controlador.despacharPedido(2);
        controlador.despacharPedido(3);

        System.out.println();
        System.out.println("--- Cancelacion de un pedido (intento en ya despachado) ---");
        controlador.cancelarPedido(1); // no deberia poder cancelarse


        PedidoComida pedido4 = new PedidoComida(4, "Jean", "Muralla Rose 232", "Pescados del Mercado", 2);
        controlador.reservarPedido(pedido4);
        System.out.println("--- Cancelacion de pedido no despachado ---");
        controlador.cancelarPedido(4);

        System.out.println("=== Historial de entregas ===");
        controlador.verHistorialEntregas();

        System.out.println("=== Resumen final de todos los pedidos ===");
        controlador.mostrarResumenTodos();
    }
}