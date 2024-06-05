package principal;

import java.util.Scanner;
import buscaminas.BuscaMinas;

public class Interface {
    BuscaMinas tablero;
    Scanner sc = new Scanner(System.in);

    Interface(){
        tablero = new BuscaMinas();
        turnoJugador();
    }

    private void mostrarTablero(){
        String[][] TableroJugador = tablero.getTableroJugador();
        for (int i = 0; i < TableroJugador.length; i++){
            for (int j = 0; j < TableroJugador[i].length; j++){
                System.out.print(TableroJugador[i][j]+" | ");
            }
            System.out.println();
        }
        System.out.println("TABLERO MAQUINA");
        mostrarTableroMaquina();

    }

    private void mostrarTableroMaquina(){
        String[][] TableroDescubierto = tablero.getTableroJugador();
        for (int i = 0; i < TableroDescubierto.length; i++){
            for (int j = 0; j < TableroDescubierto[i].length; j++){
                System.out.print(TableroDescubierto[i][j]+" | ");
            }
            System.out.println();
        }
    }

    private void turnoJugador(){
        mostrarTablero();
        System.out.println("Inserte la fila (0-9)");
        int fila = sc.nextInt();
        System.out.println("Inserte la columna (0-9)");
        int columna = sc.nextInt();
        if (tablero.descubrirCasillas(fila, columna)){
            if (tablero.comprobacionGanador()){
                mostrarTablero();
                System.out.println("Enhorabuena, has ganado el reto del buscaminas :)");
            } else {
                turnoJugador();
            }
        } else {
            mostrarTablero();
            System.out.println("Has fallado, había una mina :(");
        }
    }

    public static void main(String[] args) {
        new Interface();
    }
}
