package view;

import controller.ControladorRepartidores;
import model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaRepartidores extends JFrame {
    private JPanel panelLista;
    private JTable tablaRepartidores;
    private JButton actualizarRegistrosButton;
    private JButton salirButton;


    private DefaultTableModel modeloTabla;
    private ControladorRepartidores controladorRepartidores;

    public VentanaListaRepartidores(ControladorRepartidores controladorRepartidores) {
        this.controladorRepartidores = controladorRepartidores;

        setTitle("Lista de pedidos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panelLista);


        String[] columnas = {"Id", "Nombre"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaRepartidores.setModel(modeloTabla);

        actualizarTabla();

        actualizarRegistrosButton.addActionListener( e -> actualizarTabla());
        salirButton.addActionListener(e -> dispose());
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        List<Repartidor> listaRepartidores = controladorRepartidores.listarRepartidores();
        for (Repartidor repartidor : listaRepartidores) {
            Object[] fila = {
                    repartidor.getId(),
                    repartidor.getNombre()
            };

            modeloTabla.addRow(fila);
        }
    }


}