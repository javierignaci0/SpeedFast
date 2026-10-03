package model;

public class Pedido {

    private int id;
    private String direccion;
    private String tipo;
    private EstadoPedido estado;

    public Pedido(int id, String direccion, String tipo, EstadoPedido estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Pedido(int id, String direccion, String tipo) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(String direccion, String tipo) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public EstadoPedido getEstado() {return estado;}
    public void setEstado(EstadoPedido estado) {this.estado = estado;}
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", estado=" + estado +
                "}\n";
    }
}
