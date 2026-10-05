package model;

public class Repartidor {

    private int id;
    private String nombre;

    /**
     *
     * @param id es el id del repartidor
     * @param nombre es el nombre del repartidor
     */
    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor para bbdd
    public Repartidor(String nombre){
        this.nombre = nombre;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
}

