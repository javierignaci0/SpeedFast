import java.util.Random;

public class Repartidor implements Runnable {
    private final String nombre;
    private final ZonaDeCarga zonaCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaCarga) {
        this.nombre = nombre;
        this.zonaCarga = zonaCarga;
    }

    @Override
    public void run(){
        Random random = new Random();
        while (true){

            Pedido pedido = zonaCarga.retirarPedido();

            if (pedido == null){
                System.out.println("No quedan pedidos por tomar.");
                return;
            }

            pedido.setEstado(estadoPedido.EN_REPARTO);
            System.out.println("[Repartidor: " + nombre + "] inicia el reparto del pedido #" + pedido.getId() +
                    " | Estado: "+ pedido.getEstado());
            zonaCarga.historialPedidos.add(pedido);
            try{
                int tiempo = random.nextInt(2000,6000);
                Thread.sleep(tiempo);
            }catch (InterruptedException e){
                System.out.println("        [Repartidor: " + nombre + "] ha sufrido un imprevisto con el pedido #" + pedido.getId() +
                        ". Estamos trabajando para llegue lo antes posible.");

                return;
            }

            pedido.setEstado(estadoPedido.ENTREGADO);
            System.out.println("    [Repartidor " + nombre + "] ha entregado el pedido #" + pedido.getId() +
                    " | Estado: " + pedido.getEstado());


        }
    }
}
