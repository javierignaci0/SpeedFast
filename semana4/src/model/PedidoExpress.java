package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class PedidoExpress extends Pedido implements Rastreable, Cancelable {
    public PedidoExpress(int id, String cliente, String direccion, int distanciaKm) {
        super(id, cliente, direccion, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {

    }

    @Override
    public int calcularTiempoEntrega() {

        int tiempo = distanciaKm * 4 + 5;

        return tiempo;
    }



    @Override
    public void verHistorial() {

    }

    @Override
    public void cancelar() {
        System.out.println("Error al cancelar. El pedido Express ya esta en reparto.");
    }
}
