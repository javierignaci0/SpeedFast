package model;

import interfaces.Despachable;
import interfaces.Cancelable;
import interfaces.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    protected int id;
    protected String cliente;
    protected String direccion;
    protected String repartidor;
    protected boolean despachado;
    protected boolean cancelado;

    public Pedido(int id, String cliente, String direccion) {
        this.id = id;
        this.cliente = cliente;
        this.direccion = direccion;
        this.repartidor = "Sin asignar";
        this.despachado = false;
        this.cancelado = false;
    }


    public void mostrarResumen() {
        System.out.println("=== Resumen del Pedido ===");
        System.out.println("ID: " + id);
        System.out.println("Cliente: " + cliente);
        System.out.println("Direccion: " + direccion);
        System.out.println("Repartidor: " + repartidor);
        System.out.println("Tipo: " + obtenerTipo());
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " min");
        String estado;
        if (cancelado) {
            estado = "Cancelado";
        } else if (despachado) {
            estado = "En despacho";
        } else {
            estado = "Pendiente";
        }
        System.out.println("Estado: " + estado);

    }

    public abstract void asignarRepartidor();

    public abstract int calcularTiempoEntrega();


    protected abstract String obtenerTipo();

    // Sobrecarga: asignar repartidor manual
    public void asignarRepartidor(String nombre) {
        if (!cancelado && !despachado) {
            this.repartidor = nombre;
            System.out.println("Repartidor asignado manualmente: " + nombre);
        } else {
            System.out.println("No se puede asignar repartidor: pedido cancelado o despachado.");
        }
    }

    // Implementación de interfaces (puede ser genérica o sobrescrita)
    @Override
    public void despachar() {
        if (cancelado) {
            System.out.println("No se puede despachar: pedido cancelado.");
            return;
        }
        if ("Sin asignar".equals(repartidor)) {
            asignarRepartidor(); // asignación automática antes de despachar
        }
        despachado = true;
        System.out.println("Pedido " + id + " despachado con repartidor: " + repartidor);
    }

    @Override
    public void cancelar() {
        if (despachado) {
            System.out.println("No se puede cancelar: pedido ya despachado.");
            return;
        }
        cancelado = true;
        System.out.println("Pedido " + id + " cancelado.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido " + id + ":");
        System.out.println("- Creado para cliente: " + cliente);
        System.out.println("- Direccion: " + direccion);
        System.out.println("- Repartidor: " + repartidor);
        System.out.println("- Tiempo estimado: " + calcularTiempoEntrega() + " min");

        String estado;
        if (cancelado == true) {
            estado = "Cancelado";
        }
        else if (despachado == true) {
            estado = "En despacho";
        }
        else {
            estado = "Finalizado";
        }
        System.out.println("Estado: " + estado);
    }

    public int getId() { return id; }
    public boolean isDespachado() { return despachado; }
    public boolean isCancelado() { return cancelado; }
}