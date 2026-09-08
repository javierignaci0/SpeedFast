package model;

import interfaces.Cancelable;
import interfaces.Rastreable;

public class PedidoEncomienda extends Pedido implements Rastreable, Cancelable {



    public PedidoEncomienda(int id, String cliente, String direccion, int  distanciaKm) {
        super(id, cliente, direccion, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {

    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = distanciaKm * 3;;
        if (distanciaKm > 5) {
            tiempo += distanciaKm * 2;
        }
        return tiempo;
    }


    @Override
    public void verHistorial() {

    }

    @Override
    public void cancelar() {
        System.out.println("El pedido ha sido cancelado y devuelto a centro logistico");
    }
}
