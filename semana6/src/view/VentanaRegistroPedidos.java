package view;

import model.ControladorPedidos;
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

    private ControladorPedidos controlador;

    public VentanaRegistroPedidos(ControladorPedidos controlador) {
        this.controlador = controlador;

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
            String textoIdPedido = campoId.getText().trim();
            String direccionPedido = campoDireccion.getText().trim();
            String tipoPedido = tipoPedidoBox.getSelectedItem().toString();

            if ( direccionPedido.isEmpty() || textoIdPedido.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            int idPedido = Integer.parseInt(textoIdPedido);

            if (idPedido < 1){
                throw new IllegalArgumentException("El id no puede ser negativo o cero.");
            }

            if (tipoPedido.isEmpty() || tipoPedido.isBlank()) {
                throw new IllegalArgumentException("El tipo de pedido no puede ser nulo.");
            }

            Pedido pedidoNuevo = new Pedido(idPedido, direccionPedido, tipoPedido);

            controlador.agregarPedido(pedidoNuevo);
            JOptionPane.showMessageDialog(null, "Pedido agregado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
            // Para confirmar consola
            System.out.println("Pedido #" + idPedido + " agregado correctamente.");
            System.out.println(controlador.obtenerPedidos());
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
