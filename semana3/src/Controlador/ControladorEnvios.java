package Controlador;

import model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class ControladorEnvios {
    private List<Pedido> pedidos;
    private int siguienteId;

    public ControladorEnvios() {
        this.pedidos = new ArrayList<>();
        this.siguienteId = 1;
    }

    // "Reservar" = agregar pedido al sistema
    public void reservarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido reservado (ID: " + pedido.getId() + ")");
    }

    public void cancelarPedido(int id) {
        Pedido p = buscarPedido(id);
        if (p != null) {
            p.cancelar();
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    public void despacharPedido(int id) {
        Pedido p = buscarPedido(id);
        if (p != null) {
            p.despachar();
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    public void verHistorialEntregas() {
        System.out.println("\n=== Historial de entregas ===");
        for (Pedido p : pedidos) {
            p.verHistorial();
            System.out.println();
        }
    }

    public void mostrarResumenTodos() {
        System.out.println("\n=== Resumen de todos los pedidos ===");
        for (Pedido p : pedidos) {
            p.mostrarResumen();
            System.out.println();
        }
    }

    private Pedido buscarPedido(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}