package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class PedidoComida extends Pedido implements Despachable, Cancelable, Rastreable {

    String restaurante;

    public PedidoComida(int id, String cliente, String direccion, int distanciaKm, String restaurante) {
        super(id, cliente, direccion, distanciaKm);
        this.restaurante = restaurante;
    }

    @Override
    public void asignarRepartidor() {

    }

    @Override
    public int calcularTiempoEntrega() {

        int tiempo = distanciaKm * 15;

        return tiempo;
    }

    @Override
    public void cancelar() {
        System.out.println("Imposible cancelar. El pedido ya esta siendo preparado por el restaurante");
    }

    @Override
    public void despachar(){
        System.out.println("Despachando pedido a destino.");
    }

    @Override
    public void verHistorial() {

    }

    @Override
    public void mostrarResumen(){
        super.mostrarResumen();
        System.out.println("Restaurante: " + restaurante);

    }
}
