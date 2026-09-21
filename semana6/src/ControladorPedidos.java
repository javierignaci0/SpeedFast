import java.util.ArrayList;
import java.util.List;

public class ControladorPedidos {
    private final List<Pedido> listaPedidos;

    public ControladorPedidos() {
        listaPedidos = new ArrayList<>();
    }


    public void agregarPedido(Pedido Pedido) {
        listaPedidos.add(Pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return listaPedidos;
    }
}
