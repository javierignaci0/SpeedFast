package view;

import controller.ControladorPedidos;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaPedidos extends JFrame {
    private JPanel panelLista;
    private JTable tablaPedidos;
    private JButton actualizarRegistrosButton;
    private JButton salirButton;

    private DefaultTableModel modeloTabla;
    private ControladorPedidos controladorPedidos;

    public VentanaListaPedidos(ControladorPedidos controladorPedidos) {
        this.controladorPedidos = controladorPedidos;


        setTitle("Lista de pedidos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panelLista);


        String[] columnas = {"Id", "Direccion", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPedidos.setModel(modeloTabla);




        actualizarTabla();


        actualizarRegistrosButton.addActionListener( e -> actualizarTabla());
        salirButton.addActionListener(e -> dispose());
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        List<Pedido> listaPedidos = controladorPedidos.listarPedidos();
        for (Pedido pedido : listaPedidos) {
            Object[] fila = {
                    pedido.getId(),
                    pedido.getDireccion(),
                    pedido.getTipo(),
                    pedido.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }


}
