import util.ConexionDB;
import view.VentanaAlternativaForm;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        /**
         * Valida si existe la base de datos, cierra el programa si no.
         */
        if (!ConexionDB.existeDB()) {
            System.err.println("Error de conexion, la base de datos no esta creada o no existe");
            System.err.println("Debes crear la base de datos antes de iniciar la aplicacion");
            System.exit(1);
        }
        System.out.println("Iniciando la base de datos");

        SwingUtilities.invokeLater(() -> {
            VentanaAlternativaForm ventana = new VentanaAlternativaForm();
            ventana.setVisible(true);
        });
    }
}