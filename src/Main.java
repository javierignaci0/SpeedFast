import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        // Instancia de zona de carga
        ZonaDeCarga zonaCarga = new ZonaDeCarga();

        zonaCarga.historialPedidos = new ArrayList<>();

        zonaCarga.agregarPedido(new Pedido(1, "Los alamos 231"));
        zonaCarga.agregarPedido(new Pedido(2, "Los alamos 232"));
        zonaCarga.agregarPedido(new Pedido(3, "Los alamos 233"));
        zonaCarga.agregarPedido(new Pedido(4, "Los alamos 234"));
        zonaCarga.agregarPedido(new Pedido(5, "Los alamos 235"));
        zonaCarga.agregarPedido(new Pedido(6, "Los cipreses 236"));
        zonaCarga.agregarPedido(new Pedido(7, "Los alamos 237"));
        zonaCarga.agregarPedido(new Pedido(8, "Los alamos 238"));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Sistema de reparto SPEEDFAST v5");
        System.out.println("Cantidad de repartidores: 3");

        System.out.println("Cantidad de pedidos: " + zonaCarga.cantidadPedidos());

        Repartidor r1 = new Repartidor("Juanetes", zonaCarga);
        Repartidor r2 = new Repartidor("Juan Carlos Bodoque", zonaCarga);
        Repartidor r3 = new Repartidor("Juanes", zonaCarga);

        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);

        executor.shutdown();

        try {
            boolean termino = executor.awaitTermination(10, TimeUnit.SECONDS);
            if (!termino) {
                System.out.println(" => Se ha CERRADO la ventana horaria de entregas, cualquier pedido no entregado sera programado para maniana");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        if (zonaCarga.cantidadPedidos() == 0) {
            System.out.println("Todos los pedidos de la jornada han sido tomados por el Courier.");
        } else {
            System.out.println("No se han tomados todos los pedidos. Cantidad de pedidos sin retirar: " + zonaCarga.cantidadPedidos());

        }
        System.out.println("===RESUMEN OPERACIONES===");

        System.out.println("Pedidos tomados hoy: " + zonaCarga.historialPedidos.size());
        for (Pedido pedido : zonaCarga.historialPedidos) {

            System.out.println(pedido.toString());
//            if (pedido.getEstado() == estadoPedido.PENDIENTE) {
//                System.out.println("El pedido #" + pedido.getId() + " no salio a reparto el dia de hoy. Se acordara otra fecha.");
//            } else if (pedido.getEstado() == estadoPedido.EN_REPARTO) {
//                System.out.println("El pedido #" + pedido.getId() + " se encuentra en reparto pero no sera entregado hoy.");
//            } else if (pedido.getEstado() == estadoPedido.ENTREGADO) {
//                System.out.println("El pedido #" + pedido.getId() + " fue entregado hoy.");
//
//            }
        }


    }
}
