package model;

public class PedidoEncomienda extends Pedido {
    private double pesoKg;
    private String origen;

    public PedidoEncomienda(int id, String cliente, String direccion, String origen, double pesoKg) {
        super(id, cliente, direccion);
        this.origen = origen;
        this.pesoKg = pesoKg;
    }

    @Override
    public void asignarRepartidor() {
            System.out.println("Repartidor de encomienda asignado automatico");
        }


    @Override
    public int calcularTiempoEntrega() {
        return 20 + (int)(pesoKg * 2);
    }

    @Override
    protected String obtenerTipo() {
        return "Encomienda";
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Origen: " + origen);
        System.out.println("Peso: " + pesoKg + " kg");
    }
}