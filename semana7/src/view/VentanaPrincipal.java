package view;

import controller.ControladorEntregas;
import controller.ControladorPedidos;
import controller.ControladorRepartidores;
import model.Entrega;
import model.EstadoPedido;
import model.Pedido;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaPrincipal extends JFrame {
    private JButton registrarPedidoButton;
    private JButton verPedidosButton;
    private JButton iniciarEntregaButton;
    private JPanel panelPrincipal;
    private JButton agregarRepartidorButton;
    private JButton verEntregasButton;
    private JButton verRepartidoresButton;


    private ControladorPedidos controladorPedidos;
    private ControladorRepartidores controladorRepartidores;
    private ControladorEntregas controladorEntregas;

    public VentanaPrincipal() {

        setTitle("SpeedFast v6 con GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        this.controladorPedidos = new ControladorPedidos();
        this.controladorRepartidores = new  ControladorRepartidores();
        this.controladorEntregas = new ControladorEntregas();

        //botones ventana principal
        registrarPedidoButton.addActionListener(e -> new VentanaRegistroPedidos(controladorPedidos).setVisible(true));

        verPedidosButton.addActionListener(e -> new VentanaListaPedidos(controladorPedidos).setVisible(true));

        verEntregasButton.addActionListener(e -> {new VentanaListaEntregas(controladorEntregas).setVisible(true);});

        iniciarEntregaButton.addActionListener(e -> iniciarEntrega()); // usa JoptionPane, metodos definidos abajo

        agregarRepartidorButton.addActionListener(e -> agregarRepartidor()); // usa JoptionPane, metodos definidos abajo

        verRepartidoresButton.addActionListener(e -> new VentanaListaRepartidores(controladorRepartidores).setVisible(true));

    }

    private void agregarRepartidor() {

        String inputNombreRepartidor = JOptionPane.showInputDialog("Ingrese el nombre del Repartidor");
        if (inputNombreRepartidor == null || inputNombreRepartidor.trim().isEmpty()) {
            return;
        }
        try{
            boolean exito = controladorRepartidores.agregarRepartidor(inputNombreRepartidor);
            if (exito) {
                JOptionPane.showMessageDialog(null, "Repartidor agregado correctamente");
                System.out.println("Repartidor agregado correctamente");
            }else  {
                JOptionPane.showMessageDialog(null, "Error al agregar repartidor");
                System.out.println("Error al agregar Repartidor");
            }
        }catch(Exception e){
            System.out.println("Error en la operacion: " + e.getMessage());
        }
    }

    private void iniciarEntrega(){
        try{
            String idPedidoInput = JOptionPane.showInputDialog("Ingrese el id del pedido: ");
            if (idPedidoInput == null || idPedidoInput.trim().isEmpty()) {
                return;
            }
            String idRepartidorInput = JOptionPane.showInputDialog("Ingrese el id del repartidor: ");
            if (idRepartidorInput == null || idRepartidorInput.trim().isEmpty()) {
                return;
            }

            int idPedido = Integer.parseInt(idPedidoInput);
            int idRepartidor = Integer.parseInt(idRepartidorInput);


            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();

            Entrega nuevaEntrega = new Entrega(idPedido, idRepartidor, fecha, hora);

            boolean exito = controladorEntregas.agregarEntrega(nuevaEntrega);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Entrega agregado correctamente");
            }
            else  {
                JOptionPane.showMessageDialog(null, "Error al iniciar la entrega");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    // obsoleto para SpeedFastv7
    private void asignarRepartidor(){
        String inputId = JOptionPane.showInputDialog(this,"Ingrese ID del pedido para asignarle un repartodir.");

        if (inputId == null || inputId.trim().isEmpty()) {
            return;
        }
        try{
            int id = Integer.parseInt(inputId.trim());

            Pedido pedidoEncontrado = null;
            for (Pedido pedido : controladorPedidos.listarPedidos()){
                if (pedido.getId() == id){
                    pedidoEncontrado = pedido;
                    break;
                }

            }

            if (pedidoEncontrado == null){
                JOptionPane.showMessageDialog(this,"El pedido #" + id + " no existe");
                return;
            }

            if (pedidoEncontrado.getEstado() == EstadoPedido.ENTREGADO){
                JOptionPane.showMessageDialog(this, "El pedido #" + id + " ya fue entregado.");
                return;
            }

            if (pedidoEncontrado.getEstado() == EstadoPedido.EN_REPARTO){
                JOptionPane.showMessageDialog(this,"El pedido #" + id + " ya se encuentra en reparto");
                return;
            }

            pedidoEncontrado.setEstado(EstadoPedido.EN_REPARTO);
            JOptionPane.showMessageDialog(this, "El pedido #" + id + " ha iniciado su despacho.");
            Pedido pedidoParaEntregar = pedidoEncontrado;

            Timer timer = new Timer(10000, e -> {
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

