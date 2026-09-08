package model;


import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidos;

    public Repartidor(){

    }

    public Repartidor(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {

        Random random =  new Random();

        for (Pedido pedido : pedidos) {

            System.out.println("[Repartidor: " + getNombre() + "] esta entregando pedido #" + pedido.getId());

            try {

                int tiempoEntrega = 1000 + random.nextInt(4000);
                Thread.sleep(tiempoEntrega);

                System.out.println("    [Repartidor: " + getNombre() + "] ha entregado a " + pedido.getCliente() + " con exito el pedido #" + pedido.getId());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Repartidor: + " + getNombre() + "] tuvo un inconveniente. Pedido interrumpido(Hilo)");
                return;
            }
        }

        System.out.println("[Repartidor: " + getNombre() + "] terminó de entregar todos los pedidos.");
    }
}
