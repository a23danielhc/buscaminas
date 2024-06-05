package buscaminas;

public class BuscaMinas {

    String[][] tableroJugador = new String[10][10];
    String[][] tableroDescubierto = new String[10][10];

    public BuscaMinas(){
        generarTableroDescubierto();
    }

    private void generarTableroDescubierto(){
        for (int i = 0; i < tableroDescubierto.length; i++){
            for (int j = 0; j < tableroDescubierto[i].length; j++){
                tableroDescubierto[i][j] = "T";
            }
        }
        for (int i = 0; i < tableroJugador.length; i++){
            for (int j = 0; j < tableroJugador[i].length; j++){
                tableroJugador[i][j] = "M";
            }
        }
        colocarMinas();
    }
    
    private void colocarMinas(){
        int fila;
        int columna;
        for (int x = 0; x < 1; x++){
            fila = (int)(Math.random()*9);
            columna = (int)(Math.random()*9);
            tableroDescubierto[fila][columna] = "M";
        }
        contadorMinas();
    }

    private void contadorMinas(){
        for (int fila = 0; fila < tableroDescubierto.length; fila ++){
            for (int columna = 0; columna < tableroDescubierto[fila].length; columna++){
                // A partir de aquí se hace el conteo de minas alrededor de la casilla y se van sumando para poner el valor en la casilla
                int minasAlrededor = 0;
                for (int x = Math.max(0, fila-1); x < Math.min(tableroDescubierto.length, fila +2); x ++){
                    for (int y = Math.max(0, columna -1); y < Math.min(tableroDescubierto[fila].length, columna +2); y++){
                        if (tableroDescubierto[x][y] == "M"){
                            minasAlrededor += 1;
                        }
                    }
                }
                if (tableroDescubierto[fila][columna] != "M"){
                    tableroDescubierto[fila][columna] = Integer.toString(minasAlrededor);
                }
            }
        }
    }

    public String[][] getTableroJugador(){
        return tableroJugador;
    }

    public String[][] getTableroDescubierto(){
        return tableroDescubierto;
    }

    public boolean descubrirCasillas(int fila, int columna){
        if (tableroDescubierto[fila][columna] != "M"){
            tableroJugador[fila][columna] = tableroDescubierto[fila][columna];
            descubrirAledanas(fila, columna);
            return true;
        }
        else {
            tableroJugador[fila][columna] = tableroDescubierto[fila][columna];
            return false;
        }
    }

    private void descubrirAledanas(int fila, int columna){
            for (int x = Math.max(0, fila -1); x < Math.min(fila +2, tableroDescubierto.length); x++){
                for (int y = Math.max(0, columna -1); y < Math.min(columna +2, tableroDescubierto[x].length); y++){
                    if (x == fila && y == columna){
                        continue;
                    }
                    if (!tableroDescubierto[x][y].equals("M") && tableroJugador[x][y].equals("M")){
                        tableroJugador[x][y] = tableroDescubierto[x][y];
                        descubrirAledanas(x, y);
                    }
                }
            }
    }

    public boolean comprobacionGanador(){
        for (int i = 0; i < tableroJugador.length; i++){
            for (int j = 0; j < tableroJugador[i].length; j++){
                if (tableroJugador[i][j] != tableroDescubierto[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
