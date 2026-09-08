package app;


import model.*;


import java.util.ArrayList;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        PedidoComida pedido = new PedidoComida(1,"Maria Rosas","Pajaritos 1111", 4, "WcDonalds");
        PedidoEncomienda pedido2 = new PedidoEncomienda(2,"Maria de las mercedes","Pajaritos 2222",2);
        PedidoExpress pedido3 = new PedidoExpress(3, "Maria antonieta", "Pajaritos 33333", 5);
        PedidoEncomienda pedido4 = new PedidoEncomienda(4, "Maria de las nieves", "Pollitos 444", 2);
        PedidoExpress pedido5 = new PedidoExpress(5, "Maria Fernanda", "Patitos 777", 11);
        PedidoComida pedido6 = new PedidoComida(6, "Maria Cristina", "Flamengos 999", 6, "BurgerQueen");
        PedidoEncomienda pedido7 = new PedidoEncomienda(7, "Maria Jose", "Jerusalem 1", 5);
        PedidoExpress pedido8 = new PedidoExpress(8, "Maria Jesus", "Infierno 2", 10);
        PedidoComida pedido9 = new PedidoComida(9, "Maria Cristo", "Luna 4", 1,"CFK");



        List<Pedido> pedidosFry = new ArrayList<>();
        pedidosFry.add(pedido);
        pedidosFry.add(pedido2);
        pedidosFry.add(pedido3);
        List<Pedido> pedidosLeela = new ArrayList<>();
        pedidosLeela.add(pedido4);
        pedidosLeela.add(pedido5);
        pedidosLeela.add(pedido6);
        List<Pedido> pedidosBender = new ArrayList<>();
        pedidosBender.add(pedido7);
        pedidosBender.add(pedido8);
        pedidosBender.add(pedido9);

        Repartidor repartidor1 = new Repartidor("Fry", pedidosFry);
        Repartidor repartidor2 = new Repartidor("Leela", pedidosLeela);
        Repartidor repartidor3 = new Repartidor("Bender", pedidosBender);

        Thread thread1 = new Thread(repartidor1);
        Thread thread2 = new Thread(repartidor2);
        Thread thread3 = new Thread(repartidor3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {

            thread1.join();
            thread2.join();
            thread3.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Simulacion finalizada");

    }
}