public class Pedido {
    private final int id;
    private final String direccion;
    private estadoPedido estado;

    public Pedido(int id, String direccion) {
        this.id = id;
        this.direccion = direccion;
        this.estado = estadoPedido.PENDIENTE;
    }

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
        return "Pedido #" + getId() + " | Direccion: " + getDireccion() + " | Estado: " + getEstado();
    }
}
