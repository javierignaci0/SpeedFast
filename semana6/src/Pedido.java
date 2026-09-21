public class Pedido {

    private int id;
    private String direccion;
    private EstadoPedido estado;
    private String tipo;

    public Pedido(){
    }

    public Pedido(int id, String direccion, String tipo) {
        this.id = id;
        this.direccion = direccion;
        this.estado = EstadoPedido.PENDIENTE;
        this.tipo = tipo;

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
