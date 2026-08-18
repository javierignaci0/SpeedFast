package model;

public class Repartidor {

    private String nombreRepartidor;
    private int distancia;
    private boolean mochilaTermica;

    public Repartidor(String nombreRepartidor, int distancia,  boolean mochilaTermica){
        this.nombreRepartidor = nombreRepartidor;
        this.distancia = distancia;
        this.mochilaTermica = mochilaTermica;

    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public boolean isMochilaTermica() {
        return mochilaTermica;
    }

    public void setMochilaTermica(boolean mochilaTermica) {
        this.mochilaTermica = mochilaTermica;
    }

    public String verificarMochila(){
        if (!isMochilaTermica()){
            return "No tiene Mochila";
        }
        else {
            return "OK";
        }
    }

    @Override
    public String toString(){
        return "Repartidor: " + nombreRepartidor + ", Distancia: " + distancia + ", Mochila Termica: " + mochilaTermica;
    }
}

