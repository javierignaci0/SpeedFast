package view;

import controller.ControladorEntregas;
import controller.ControladorPedidos;
import controller.ControladorRepartidores;
import dao.PedidoDAO;
import dao.impl.PedidoDAOImpl;
import dao.impl.RepartidorDAOImpl;
import model.Entrega;
import model.Pedido;
import model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VentanaAlternativaForm extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JTextField campoDireccion;
    private JTextField campoNombre;
    private JComboBox comboIdPedido;
    private JComboBox comboTipo;
    private JComboBox comboIdRepartidor;
    private JButton crearPedidoButton;
    private JButton verPedidosButton;
    private JButton editarPedidoButton;
    private JButton eliminarPedidoButton;
    private JButton crearRepartidorButton;
    private JButton verRepartidoresButton;
    private JButton editarRepartidorButton;
    private JButton eliminarRepartidorButton;
    private JButton crearEntregaButton;
    private JButton verEntregasButton;
    private JButton editarEntregaButton;
    private JButton eliminarEntregaButton;
    private JTable tablaEntregas;
    private JTable tablaPedidos;
    private JTable tablaRepartidores;
    private JButton limpiarCamposButton;
    private JButton salirButton;

    private ControladorPedidos controladorPedidos;
    private ControladorRepartidores controladorRepartidores;
    private ControladorEntregas controladorEntregas;

    private DefaultTableModel modeloTablaE;
    private DefaultTableModel modeloTablaP;
    private DefaultTableModel modeloTablaR;

    public VentanaAlternativaForm() {
        // CONFIGURACION VENTANA
        setTitle("SpeedFast v8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setSize(1050, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // CONFIGURACION COMBOBOX
        String[] opcionesComboBox = {"Seleccione","Comida", "Encomienda", "Express"};
        comboTipo.setSelectedIndex(0);
        comboTipo.setModel(new DefaultComboBoxModel(opcionesComboBox));


        // CONFIGURACION TABLAS
        // TABLA ENTREGA
        String[] columnasE = {"Id", "Id_pedido", "Id_repartidor", "Fecha", "Hora"};
        modeloTablaE = new DefaultTableModel(columnasE, 0);
        tablaEntregas.setModel(modeloTablaE);
        // TABLA PEDIDO
        String[] columnasP = {"Id", "Direccion", "Tipo", "Estado"};
        modeloTablaP = new DefaultTableModel(columnasP, 0);
        tablaPedidos.setModel(modeloTablaP);
        // TABLA REPARTIDOR
        String[] columnasR = {"Id", "Nombre"};
        modeloTablaR = new DefaultTableModel(columnasR, 0);
        tablaRepartidores.setModel(modeloTablaR);

        // INSTANCIA DE CONTROLADORES
        this.controladorPedidos = new ControladorPedidos();
        this.controladorRepartidores = new ControladorRepartidores();
        this.controladorEntregas = new ControladorEntregas();

        // BOTONES PANEL 1
        // BOTONES PARA PEDIDO
        crearPedidoButton.addActionListener(e -> crearPedido()); //FUNCIONA // TODO VALIDAR DIRECCION NO VACIA
        verPedidosButton.addActionListener(e -> actualizarTablaP()); //FUNCIONA
        verPedidosButton.addActionListener(e -> actualizarComboIdPedido(comboIdPedido));//FUNCIONA
        editarPedidoButton.addActionListener(null);
        eliminarPedidoButton.addActionListener(null);
        // BOTONES PARA REPARTIDOR
        crearRepartidorButton.addActionListener(e -> crearRepartidor());
        verRepartidoresButton.addActionListener(e -> actualizarTablaR()); //FUNCIONA
        verRepartidoresButton.addActionListener(e -> actualizarComboIdRepartidor(comboIdRepartidor));
        editarRepartidorButton.addActionListener(null);
        eliminarRepartidorButton.addActionListener(null);
        // BOTONES PARA ENTREGA
        crearEntregaButton.addActionListener(e -> crearEntrega());//FUNCIONA //TODO 1:N
        verEntregasButton.addActionListener(e -> actualizarTablaE());//FUNCIONA
        editarEntregaButton.addActionListener(null);
        eliminarEntregaButton.addActionListener(null);
        // BOTONES EXTRA
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        salirButton.addActionListener(e -> dispose());

        //TODO
        //iniciarEntregaButton.addActionListener(e -> iniciarEntrega()); // usa JoptionPane, metodos definidos abajo

        // ACTUALIZA LAS TABLAS Y LOS COMBOBOX SEGUN LA BBDD
        actualizarTablaE();
        actualizarTablaP();
        actualizarTablaR();
        actualizarComboIdPedido(comboIdPedido);
        actualizarComboIdRepartidor(comboIdRepartidor);


    }

    //FUNCIONA
    public void crearPedido() {
        try {
            //validacion tipo pedido
            if (comboTipo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecciona un tipo de pedido valido.");
            }

            String direccionPedido = campoDireccion.getText().trim();
            String tipoPedido = comboTipo.getSelectedItem().toString();
            // validacion direccion
            if (direccionPedido.isEmpty() || direccionPedido.length() < 4) {
                JOptionPane.showMessageDialog(null, "Direccion de pedido demasiado corta o no puede estar vacia.");
                return;
            }
            // validacion tipo
            if (tipoPedido.isBlank()) {
                throw new IllegalArgumentException("El tipo de pedido no puede ser nulo.");
            }

            Pedido pedidoNuevo = new Pedido(direccionPedido, tipoPedido);

            // V -> C
            boolean exito = controladorPedidos.agregarPedido(pedidoNuevo);
            if (exito){
                // se usa el ps.getGeneratedKeys para obtener id
                JOptionPane.showMessageDialog(null, "Pedido #" + pedidoNuevo.getId() + " creado con exito.", "Sistema registro", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Pedido #" + pedidoNuevo.getId() + " agregado correctamente a la base de datos.");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
        }

        actualizarTablaP();
    }

    public void crearEntrega() {
        try {


            int idPedido = comboIdPedido.getSelectedIndex();
            int idRepartidor = comboIdRepartidor.getSelectedIndex();

            //validacion ui
            if (idPedido == 0 || idRepartidor == 0) {
                throw new IllegalArgumentException("El pedido no puede ser cero ni nulo");
            }

            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();

            int repuesta = JOptionPane.showConfirmDialog(this,
                    "Esta seguro de crear una Entrega con ID pedido: " + idPedido + "| ID Repartidor: " + idRepartidor,
                    "Confirmar creacion Entrega",
                    JOptionPane.YES_NO_OPTION);
            if (repuesta != JOptionPane.YES_OPTION) {
                return;
            }

            Entrega nuevaEntrega = new Entrega(idPedido, idRepartidor, fecha, hora);
            // V -> C
            boolean exito = controladorEntregas.agregarEntrega(nuevaEntrega);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Entrega creada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear la entrega");
            }
        } catch (Exception ex) {
            System.out.println("Error inesperado: " + ex.getMessage());
        }
    }

    //FUNCIONA
    public void actualizarComboIdPedido(JComboBox<String> comboIdPedido) {
        comboIdPedido.removeAllItems();
        comboIdPedido.addItem("Seleccione");

        PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
        List<Pedido> ids = pedidoDAO.listarTodos();

        for (Pedido pedido : ids) {
            comboIdPedido.addItem(String.valueOf(pedido.getId()));
        }
    }

    //FUNCIONA
    public void actualizarComboIdRepartidor(JComboBox<String> comboIdRepartidor) {
        comboIdRepartidor.removeAllItems();
        comboIdRepartidor.addItem("Seleccione");

        RepartidorDAOImpl repartidorDAO = new RepartidorDAOImpl();
        List<Repartidor> ids = repartidorDAO.listarTodos();

        for (Repartidor repartidor : ids) {
            comboIdRepartidor.addItem(String.valueOf(repartidor.getId()));
        }
    }

    //FUNCIONA
    public void crearRepartidor() {
        try {
            //formato
            String nombreRepartidor = campoNombre.getText().trim();
            //validacion ui
            if (nombreRepartidor.isBlank() || nombreRepartidor.length() < 2 || nombreRepartidor.length() > 31) {
                JOptionPane.showMessageDialog(null, "El nombre del repartidor es obligatorio y debe tener entre 3 y 30 caracteres");
                return;
            }

            Repartidor repartidor = new Repartidor(nombreRepartidor);
            // V -> C
            controladorRepartidores.agregarRepartidor(repartidor);
            JOptionPane.showMessageDialog(null, "Repartidor creado con exito.");
            actualizarTablaR();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }
    }

    //FUNCIONA
    private void actualizarTablaE() {
        modeloTablaE.setRowCount(0);

        List<Entrega> listaEntregas = controladorEntregas.listarEntregas();
        for (Entrega entrega : listaEntregas) {
            Object[] fila = {
                    entrega.getId(),
                    entrega.getId_pedido(),
                    entrega.getId_repartidor(),
                    entrega.getFecha(),
                    entrega.getHora()
            };

            modeloTablaE.addRow(fila);
        }
    }

    //FUNCIONA
    private void actualizarTablaP() {
        modeloTablaP.setRowCount(0);

        List<Pedido> listaPedidos = controladorPedidos.listarPedidos();
        for (Pedido pedido : listaPedidos) {
            Object[] fila = {
                    pedido.getId(),
                    pedido.getDireccion(),
                    pedido.getTipo(),
                    pedido.getEstado()
            };

            modeloTablaP.addRow(fila);
        }
    }

    //FUNCIONA
    private void actualizarTablaR() {
        modeloTablaR.setRowCount(0);

        List<Repartidor> listaRepartidores = controladorRepartidores.listarRepartidores();
        for (Repartidor repartidor : listaRepartidores) {
            Object[] fila = {
                    repartidor.getId(),
                    repartidor.getNombre()
            };

            modeloTablaR.addRow(fila);
        }
    }

    //FUNCIONA
    public void limpiarCampos() {
        campoDireccion.setText("");
        campoNombre.setText("");
        comboTipo.setSelectedIndex(0);
        comboIdPedido.setSelectedIndex(0);
        comboIdRepartidor.setSelectedIndex(0);
        campoDireccion.requestFocus();
    }


}
