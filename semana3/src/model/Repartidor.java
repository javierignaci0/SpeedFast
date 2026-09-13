package model;

public class Repartidor {

    private String nombreRepartidor;
    private int proximidad;
    private boolean mochilaTermica;

    public Repartidor(String nombreRepartidor, int proximidad,  boolean mochilaTermica){
        this.nombreRepartidor = nombreRepartidor;
        this.proximidad = proximidad;
        this.mochilaTermica = mochilaTermica;

    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public int getProximidad() {
        return proximidad;
    }
    public void setProximidad(int proximidad) {
        this.proximidad = proximidad;
    }

    public boolean isMochilaTermica() {
        return mochilaTermica;
    }

    public void setMochilaTermica(boolean mochilaTermica) {
        this.mochilaTermica = mochilaTermica;
    }

    public String verificarMochila(){
        if (!isMochilaTermica()){
            return "No tiene Mochila Termica";
        }
        else {
            return "OK";
        }
    }

    @Override
    public String toString(){
        return "Repartidor: " + getNombreRepartidor() + ", Proximidad: " + getProximidad() + ", Mochila Termica: " + verificarMochila();
    }
}

