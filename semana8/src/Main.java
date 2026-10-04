import util.ConexionDB;
import view.VentanaAlternativaForm;
import view.VentanaPrincipal;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        if (!ConexionDB.existeDB()){
            System.err.println("Error de conexion, la base de datos no esta creada o no existe");
            System.err.println("Debes crear la base de datos antes de iniciar la aplicacion");
            System.exit(1);
        }
        System.out.println("Iniciando la conexion con la base de datos...");

        SwingUtilities.invokeLater(() -> {
            VentanaAlternativaForm ventana = new VentanaAlternativaForm();
            ventana.setVisible(true);
        });



    }
}