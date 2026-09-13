import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZonaDeCarga {

    public Queue<Pedido> listaPedidos = new LinkedList<>();

    public synchronized void agregarPedido(Pedido pedido){
        listaPedidos.offer(pedido);
    }

    public synchronized Pedido retirarPedido(){
        return listaPedidos.poll();
    }

    public synchronized int cantidadPedidos(){
        return listaPedidos.size();
    }

    public List<Pedido> historialPedidos = new ArrayList<>();



}