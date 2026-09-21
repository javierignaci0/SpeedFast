import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton registrarPedidoButton;
    private JButton verPedidosButton;
    private JButton asignarRepartidorButton;
    private JPanel panelPrincipal;


    private ControladorPedidos controlador;
    public VentanaPrincipal() {

        setTitle("SpeedFast v6 con GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        this.controlador = new ControladorPedidos();

        registrarPedidoButton.addActionListener(e -> new VentanaRegistroPedidos(controlador).setVisible(true));
        verPedidosButton.addActionListener(e -> new VentanaListaPedidos(controlador).setVisible(true));

        asignarRepartidorButton.addActionListener(e -> asignarRepartidor());
    }


    private void asignarRepartidor(){
        String inputId = JOptionPane.showInputDialog(this,"Ingrese ID del pedido para asignarle un repartodir.");

        if (inputId == null || inputId.trim().isEmpty()) {
            return;
        }
        try{
            int id = Integer.parseInt(inputId.trim());

            Pedido pedidoEncontrado = null;
            for (Pedido pedido : controlador.obtenerPedidos()){
                if (pedido.getId() == id){
                    pedidoEncontrado = pedido;
                    break;
                }

            }

            if (pedidoEncontrado == null){
                JOptionPane.showMessageDialog(this,"El pedido #" + id + " no existe");
                return;
            }
            if (pedidoEncontrado != null){
                pedidoEncontrado.setEstado(EstadoPedido.EN_REPARTO);
                JOptionPane.showMessageDialog(this, "El estado del pedido #" + id + " se ha actualizado.");

            }

            Pedido pedidoParaEntregar = pedidoEncontrado;

            javax.swing.Timer timer = new javax.swing.Timer(10000, e -> {
                pedidoParaEntregar.setEstado(EstadoPedido.ENTREGADO);
                JOptionPane.showMessageDialog(this,"El pedido #" + id + " ha sido ENTREGADO.");
            });

            timer.setRepeats(false);
            timer.start();


        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Ingrese un numero valido");
        }


    }
}

