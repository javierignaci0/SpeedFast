package view;

import controller.ControladorEntregas;

import model.Entrega;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaEntregas extends JFrame {
    private JButton actualizarButton;
    private JButton salirButton;
    private JTable tablaEntregas;
    private JPanel panelListaEntregas;

    private DefaultTableModel modeloTabla;
    private ControladorEntregas controladorEntregas;

    public VentanaListaEntregas(ControladorEntregas controladorEntregas) {
        this.controladorEntregas = controladorEntregas;


        setTitle("Lista de Entregas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panelListaEntregas);


        String[] columnas = {"id", "id_pedido", "id_repartidor", "Fecha", "Hora"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEntregas.setModel(modeloTabla);




        actualizarTabla();


        actualizarButton.addActionListener( e -> actualizarTabla());
        salirButton.addActionListener(e -> dispose());
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        List<Entrega> listaEntregas = controladorEntregas.listarEntregas();
        for (Entrega entrega : listaEntregas) {
            Object[] fila = {
                    entrega.getId(),
                    entrega.getId_pedido(),
                    entrega.getId_repartidor(),
                    entrega.getFecha(),
                    entrega.getHora()
            };

            modeloTabla.addRow(fila);
        }
    }




}
