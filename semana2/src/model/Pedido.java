package model;

public abstract class Pedido {

    private String idPedido;
    private String direccionEntrega;
    private int distancia;


    public Pedido(String idPedido, String direccionEntrega, int distancia) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distancia = distancia;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public int getDistancia() {
        return distancia;
    }
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }


    public String mostrarResumen(){
        return "Resumen del pedido: " +
                "\nid del pedido: " + idPedido +
                "\nDireccion de entrega: " + direccionEntrega +
                "\nDistancia: " + distancia + "km";
    }
    public abstract String asignarRepartidor(Repartidor repartidor);

    public abstract void calcularTiempoEntrega();


}
