package model;

public abstract class Pedido {
    protected int id;
    protected String cliente;
    protected String direccion;
    protected int distanciaKm;

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCliente() {
        return cliente;
    }

    public int getId() {
        return id;
    }

    protected Repartidor repartidor;


    public Pedido(int id, String cliente, String direccion, int distanciaKm) {
        this.id = id;
        this.cliente = cliente;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
        this.repartidor = new Repartidor();
    }


    public void mostrarResumen() {
        System.out.println("=== Resumen del Pedido ===");
        System.out.println("ID: " + id);
        System.out.println("Cliente: " + cliente);
        System.out.println("Direccion: " + direccion);
        System.out.println("Distancia Km: " + distanciaKm);
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " min");
    }

    public abstract void asignarRepartidor();

    public abstract int calcularTiempoEntrega();

}