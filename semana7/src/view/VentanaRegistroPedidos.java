package view;

import controller.ControladorPedidos;
import model.Pedido;

import javax.swing.*;

public class VentanaRegistroPedidos extends JFrame {
    private JButton btnGuardar;
    private JPanel panelRegistro;
    private JTextField campoId;
    private JTextField campoDireccion;
    private JComboBox<String> tipoPedidoBox;
    private JButton limpiarCamposButton;
    private JLabel idPedidoLabel;
    private JLabel direccionPedidoLabel;
    private JLabel tipoPedidoLabel;
    private JButton cancelarButton;

    private ControladorPedidos controladorPedidos;

    public VentanaRegistroPedidos(ControladorPedidos controladorPedidos) {
        this.controladorPedidos = controladorPedidos;

        setContentPane(panelRegistro);
        // Configuracion ventana
        setTitle("Registro de Pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Configuracion componentes
        String[] opcionesComboBox = {"Comida", "Encomienda", "Express"};
        tipoPedidoBox.setModel(new DefaultComboBoxModel(opcionesComboBox));


        // Configurar Action
        btnGuardar.addActionListener(e -> registrarPedido());
        cancelarButton.addActionListener(e -> dispose());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
    }




    public void registrarPedido(){
        try {
            // String textoIdPedido = campoId.getText().trim(); Obsoleto para v7
            String direccionPedido = campoDireccion.getText().trim();
            String tipoPedido = tipoPedidoBox.getSelectedItem().toString();

            // obsoleto para v7
//            if ( direccionPedido.isEmpty() || textoIdPedido.isEmpty()) {
//                throw new IllegalArgumentException("Todos los campos son obligatorios.");
//            }
//
//            int idPedido = Integer.parseInt(textoIdPedido);

//            if (idPedido < 1){
//                throw new IllegalArgumentException("El id no puede ser negativo o cero.");
//            }

            if (tipoPedido.isEmpty() || tipoPedido.isBlank()) {
                throw new IllegalArgumentException("El tipo de pedido no puede ser nulo.");
            }

            Pedido pedidoNuevo = new Pedido(direccionPedido, tipoPedido);

            // entrega el pedido al controlador, que lo entrega a EntregaDAO (bbdd)
            controladorPedidos.agregarPedido(pedidoNuevo);

            // se usa el ps.getGeneratedKeys aca
            JOptionPane.showMessageDialog(null, "Pedido #" + pedidoNuevo.getId() + "agregado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
            // Para confirmar consola
            System.out.println("VentanaRegistro: Pedido #" + pedidoNuevo.getId() + " agregado correctamente.");

        }

        catch (NumberFormatException ex) {JOptionPane.showMessageDialog(this,"ID solo puede ser numerico.");}
        catch (IllegalArgumentException ex) {JOptionPane.showMessageDialog(this,"Los datos ingresados no son validos");}
    }


    private void limpiarCampos(){
        campoId.setText("");
        campoDireccion.setText("");
        tipoPedidoBox.setSelectedIndex(0);
    }
}
