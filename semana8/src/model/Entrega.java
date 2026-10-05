package model;

import java.time.LocalDate;
import java.time.LocalTime;


public class Entrega {

    private int id;
    private int id_pedido;
    private int id_repartidor;
    private LocalDate fecha;
    private LocalTime hora;

    /**
     *
     * @param id_pedido es el id del pedido asociada a la entrega
     * @param id_repartidor es el id del repartidor asociada a la entrega
     * @param fecha es la fecha de la entrega
     * @param hora es la hora de la entrega
     */
    public Entrega(int id_pedido, int id_repartidor, LocalDate fecha, LocalTime hora) {
        this.id_pedido = id_pedido;
        this.id_repartidor = id_repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Entrega(int id, int id_pedido, int id_repartidor,  LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.id_repartidor = id_repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Entrega(int id, int id_pedido, int id_repartidor) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.id_repartidor = id_repartidor;
    }

    public int getId() {
        return id;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public int getId_repartidor() {
        return id_repartidor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
}
