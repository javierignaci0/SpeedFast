public class Pedido {
    private int id;
    private String direccion;
    private estadoPedido estado;

    public Pedido(int id, String direccion, estadoPedido estado) {
        this.id = id;
        this.direccion = direccion;
        this.estado = estadoPedido.PENDIENTE;
    }

    public Pedido(){}

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public estadoPedido getEstado() {
        return estado;
    }

    public void setEstado(estadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Direccion: " + direccion + " | Estado: " + estado;
    }
}
