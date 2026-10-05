package view;

import controller.ControladorEntregas;
import controller.ControladorPedidos;
import controller.ControladorRepartidores;
import model.Entrega;
import model.Pedido;
import model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

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
    private JLabel lblSeleccion;

    private final ControladorPedidos controladorPedidos = new ControladorPedidos();
    private final ControladorRepartidores controladorRepartidores = new ControladorRepartidores();
    private final ControladorEntregas controladorEntregas = new ControladorEntregas();

    private final DefaultTableModel modeloTablaE;
    private final DefaultTableModel modeloTablaP;
    private final DefaultTableModel modeloTablaR;

    private int idPedidoSeleccionado = -1;
    private int idRepartidorSeleccionado = -1;
    private int idEntregaSeleccionada = -1;
    private String direccionInicial;
    private String tipoInicial;


    public VentanaAlternativaForm() {
        // CONFIGURACION VENTANA
        setTitle("SpeedFast v8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setSize(1050, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // CONFIGURACION COMBOBOX
        String[] opcionesComboBox = {"Seleccione","COMIDA", "ENCOMIENDA", "EXPRESS"};
        comboTipo.setSelectedIndex(0);
        comboTipo.setModel(new DefaultComboBoxModel(opcionesComboBox));



        // CONFIGURACION TABLAS
        // TABLA ENTREGA
        String[] columnasE = {"Id", "Id_pedido", "Id_repartidor", "Fecha", "Hora"};
        modeloTablaE = new DefaultTableModel(columnasE, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEntregas.setModel(modeloTablaE);
        // TABLA PEDIDO
        String[] columnasP = {"Id", "Direccion", "Tipo", "Estado"};
        modeloTablaP = new DefaultTableModel(columnasP, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPedidos.setModel(modeloTablaP);
        // TABLA REPARTIDOR
        String[] columnasR = {"Id", "Nombre"};
        modeloTablaR = new DefaultTableModel(columnasR, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaRepartidores.setModel(modeloTablaR);

        // INSTANCIA DE CONTROLADORES


        // BOTONES PANEL 1
        // BOTONES PARA PEDIDO
        crearPedidoButton.addActionListener(e -> crearPedido()); //FUNCIONA
        verPedidosButton.addActionListener(e -> actualizarTablaP()); //FUNCIONA
        verPedidosButton.addActionListener(e -> actualizarComboIdPedido(comboIdPedido));//FUNCIONA
        editarPedidoButton.addActionListener(e -> editarPedido()); //FUNCIONA
        eliminarPedidoButton.addActionListener(e -> eliminarPedido()); //FUNCIONA
        // BOTONES PARA REPARTIDOR
        crearRepartidorButton.addActionListener(e -> crearRepartidor()); //FUNCIONA
        verRepartidoresButton.addActionListener(e -> actualizarTablaR()); //FUNCIONA
        verRepartidoresButton.addActionListener(e -> actualizarComboIdRepartidor(comboIdRepartidor)); //FUNCIONA
        editarRepartidorButton.addActionListener(e -> editarRepartidor()); //FUNCIONA
        eliminarRepartidorButton.addActionListener(e -> eliminarRepartidor());
        // BOTONES PARA ENTREGA
        crearEntregaButton.addActionListener(e -> crearEntrega());//FUNCIONA
        verEntregasButton.addActionListener(e -> actualizarTablaE());//FUNCIONA
        editarEntregaButton.addActionListener(e -> editarEntrega());
        eliminarEntregaButton.addActionListener(e -> eliminarEntrega());
        // BOTONES EXTRA
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        salirButton.addActionListener(e -> dispose());

        // ACTUALIZA LAS TABLAS Y LOS COMBOBOX SEGUN LA BBDD
        actualizarTablaE();
        actualizarTablaP();
        actualizarTablaR();
        actualizarComboIdPedido(comboIdPedido);
        actualizarComboIdRepartidor(comboIdRepartidor);


        // Listener Seleccion fila tabla pedidos
        tablaPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaPedidos.getSelectedRow();

                if (fila >= 0) {

                    idPedidoSeleccionado = Integer.parseInt(tablaPedidos.getValueAt(fila, 0).toString());
                    direccionInicial = tablaPedidos.getValueAt(fila, 1).toString();
                    tipoInicial = tablaPedidos.getValueAt(fila, 2).toString();

                    campoDireccion.setText(direccionInicial);
                    comboTipo.setSelectedItem(tipoInicial);
                    campoNombre.setText("");
                    lblSeleccion.setText("Pedido seleccionado: "  + idPedidoSeleccionado + ", Direccion: " + direccionInicial + ", Tipo: " + tipoInicial);

                    comboIdPedido.setSelectedItem(idPedidoSeleccionado);
                }
            }
        });

        // Listener seleccion tabla repartidores
        tablaRepartidores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaRepartidores.getSelectedRow();

                if (fila >= 0) {
                    idRepartidorSeleccionado = Integer.parseInt(tablaRepartidores.getValueAt(fila, 0).toString());
                    String nombre = tablaRepartidores.getValueAt(fila, 1).toString();

                    campoDireccion.setText("");
                    campoNombre.setText(nombre);
                    lblSeleccion.setText("Repartidor seleccionado: "  + idRepartidorSeleccionado + ", Nombre: " + nombre);
                }
            }
        });

        // Listener seleccion tabla entregas
        tablaEntregas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaEntregas.getSelectedRow();

                if (fila >= 0) {

                    idEntregaSeleccionada = Integer.parseInt(tablaEntregas.getValueAt(fila, 0).toString());
                    idPedidoSeleccionado = Integer.parseInt(tablaEntregas.getValueAt(fila, 1).toString());
                    idRepartidorSeleccionado = Integer.parseInt(tablaEntregas.getValueAt(fila, 2).toString());

                    lblSeleccion.setText("Entrega seleccionada | ID:" + idEntregaSeleccionada + " | ID Pedido:"+ idPedidoSeleccionado + " | ID Repartidor:"+idRepartidorSeleccionado);

                    comboIdPedido.setSelectedItem(idPedidoSeleccionado);
                    comboIdRepartidor.setSelectedItem(idRepartidorSeleccionado);
                }
            }
        });
    }

    //FUNCIONA CREATE PEDIDO
    public void crearPedido() {
        try {
            //validacion tipo pedido
            if (comboTipo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecciona un tipo de pedido valido.");
            }

            String direccionPedido = campoDireccion.getText().trim();
            String tipoPedido = Objects.requireNonNull(comboTipo.getSelectedItem()).toString();
            // validacion direccion
            if (direccionPedido.length() < 4) {
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

                actualizarComboIdPedido(comboIdPedido);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
        }

        actualizarTablaP();
    }

    //FUNCIONA READ PEDIDO
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

    //FUNCIONA UPDATE PEDIDO
    public void editarPedido() {
        //validacion id
        if (idPedidoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un pedido de la tabla para editar.");
            return;
        }

        String nuevaDireccion = campoDireccion.getText();
        String nuevoTipo = Objects.requireNonNull(comboTipo.getSelectedItem()).toString();

        //validacion
        if (nuevaDireccion.equals(direccionInicial) && nuevoTipo.equals(tipoInicial)) {
            JOptionPane.showMessageDialog(null, "No se ha modificado ningun campo");
            return;
        }

        if (nuevaDireccion.trim().isEmpty() || nuevoTipo.equals("Seleccione")) {
            JOptionPane.showMessageDialog(this, "La dirección y el tipo no pueden quedar vacíos.");
            return;
        }

        Pedido pedidoModificado = new Pedido(idPedidoSeleccionado, nuevaDireccion, nuevoTipo);
        //
        boolean exito = controladorPedidos.actualizarPedido(pedidoModificado);

        if (exito) {
            JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente.");

            idPedidoSeleccionado = -1;
            limpiarCampos();
            actualizarTablaP();

        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el pedido en la base de datos.");
        }
    }

    //FUNCIONA DELETE PEDIDO
    public void eliminarPedido() {
        if (idPedidoSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un pedido para eliminarlo.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                null,
                "Seguro que deseas borrar el Pedido #" + idPedidoSeleccionado + " ?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        boolean exito = controladorPedidos.eliminarPedido(idPedidoSeleccionado);
        if (exito) {
            JOptionPane.showMessageDialog(null,"Pedido #" + idPedidoSeleccionado + " eliminado correctamente.");

            idPedidoSeleccionado = -1;
            actualizarTablaP();
            limpiarCampos();
        }else{
            JOptionPane.showMessageDialog(null,"Error al eliminar el pedido.");
        }
    }

    //FUNCIONA CREATE REPARTIDOR
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
            limpiarCampos();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }
    }

    //FUNCIONA READ REPARTIDOR
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

    //FUNCIONA UPDATE REPARTIDOR
    public void editarRepartidor(){
        if (idRepartidorSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un repartidor de la tabla para editarlo.");
            return;
        }

        String nuevoNombre = campoNombre.getText();

        if (nuevoNombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del repartidor no puede quedar vacio.");
            return;
        }

        Repartidor repartidorModificado = new Repartidor(idRepartidorSeleccionado, nuevoNombre);

        boolean exito = controladorRepartidores.actualizarRepartidor(repartidorModificado);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Repartidor actualizado correctamente.");

            idRepartidorSeleccionado = -1;
            limpiarCampos();
            actualizarTablaR();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el repartidor.");
        }
    }

    // DELETE REPARTIDOR
    public void eliminarRepartidor(){
        if (idRepartidorSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un repartidor para eliminar.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                null,
                "Esta seguro que desea eliminar el Repartidor #" + idRepartidorSeleccionado + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (confirmar == JOptionPane.YES_OPTION) {

            boolean exito = controladorRepartidores.eliminarRepartidor(idRepartidorSeleccionado);

            if (exito){
                JOptionPane.showMessageDialog(null, "Repartidor #" + idRepartidorSeleccionado + " eliminado correctamente.");

                idRepartidorSeleccionado = -1;
                actualizarTablaR();
                limpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar el repartidor.");
            }
        }
    }

    //FUNCIONA CREATE ENTREGA
    public void crearEntrega() {
        try {
            String idPedidoTxt = Objects.requireNonNull(comboIdPedido.getSelectedItem()).toString();
            String idRepartidorTxt = Objects.requireNonNull(comboIdRepartidor.getSelectedItem()).toString();

            int idPedido = Integer.parseInt(idPedidoTxt);
            int idRepartidor = Integer.parseInt(idRepartidorTxt);

            //validacion ui
            if (idPedido == 0 || idRepartidor == 0) {
                throw new IllegalArgumentException("El pedido no puede ser cero ni nulo");
            }
            //validacion para estado
            if (controladorPedidos.verificarPedidoEntregado(idPedido)) {
                JOptionPane.showMessageDialog(this,
                        "El pedido #" + idPedido + " ya fue ENTREGADO y no puede asignarse.",
                        "Pedido ya entregado",
                        JOptionPane.WARNING_MESSAGE);
                return;
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

                controladorPedidos.cambiarEstadoPedido(idPedido);

                actualizarTablas();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(null, "Error al crear la entrega");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error inesperado: " + ex.getMessage());
        }
    }

    //FUNCIONA READ ENTREGA
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

    //FUNCIONA UPDATE ENTREGA
    public void editarEntrega(){
        if (idEntregaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una entrega de la lista para poder editarla");
            return;
        }
        String seleccionPedido = comboIdPedido.getSelectedItem().toString();
        String seleccionRepartidor = comboIdRepartidor.getSelectedItem().toString();

        if (seleccionPedido.equals("Seleccione") || seleccionRepartidor.equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Selecciona un id pedido y un id repartidor.");
            return;
        }

        int idPedido = Integer.parseInt(seleccionPedido);
        int idRepartidor = Integer.parseInt(seleccionRepartidor);

        Entrega entregaModificada = new Entrega(idEntregaSeleccionada,idPedido, idRepartidor);

        boolean exito = controladorEntregas.actualizarEntrega(entregaModificada);

        if (exito){
            JOptionPane.showMessageDialog(null,"Entrega actualizada correctanmente");

            idEntregaSeleccionada = -1;
            actualizarTablas();
            limpiarCampos();
        }else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el entrega.");
        }
    }

    // DELETE ENTREGA
    public void eliminarEntrega(){
        if (idEntregaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null,"Selecciona una entrega de la lista para eliminar.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                null,
                "Esta seguro que desea eliminar la entrega #" + idEntregaSeleccionada + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmar == JOptionPane.YES_OPTION) {
            boolean exito = controladorEntregas.eliminarEntrega(idEntregaSeleccionada);
            if (exito) {
                JOptionPane.showMessageDialog(null, "Entrega eliminada correctamente");

                idEntregaSeleccionada = -1;
                actualizarTablaE();
                limpiarCampos();

            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar el entrega.");
            }
        }
    }


    //FUNCIONA
    public void actualizarComboIdPedido(JComboBox<String> comboIdPedido) {
        comboIdPedido.removeAllItems();
        comboIdPedido.addItem("Seleccione");

        List<Pedido> ids = controladorPedidos.listarPedidos();

        for (Pedido pedido : ids) {
            comboIdPedido.addItem(String.valueOf(pedido.getId()));
        }
    }

    //FUNCIONA
    public void actualizarComboIdRepartidor(JComboBox<String> comboIdRepartidor) {
        comboIdRepartidor.removeAllItems();
        comboIdRepartidor.addItem("Seleccione");

        List<Repartidor> ids = controladorRepartidores.listarRepartidores();

        for (Repartidor repartidor : ids) {
            comboIdRepartidor.addItem(String.valueOf(repartidor.getId()));
        }
    }

    //FUNCIONA
    public void limpiarCampos() {
        campoDireccion.setText("");
        campoNombre.setText("");
        comboTipo.setSelectedIndex(0);
        comboIdPedido.setSelectedIndex(0);
        comboIdRepartidor.setSelectedIndex(0);

        idPedidoSeleccionado = -1;
        idRepartidorSeleccionado = -1;
        idEntregaSeleccionada = -1;
        lblSeleccion.setText("Seleccione un elemento de las tablas:");
    }

    public void actualizarTablas(){
        actualizarTablaE();
        actualizarTablaP();
        actualizarTablaR();
    }

}
