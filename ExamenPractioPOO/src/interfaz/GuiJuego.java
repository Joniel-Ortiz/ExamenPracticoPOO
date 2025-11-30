package interfaz;

import java.util.Scanner;

public class GuiJuego {
    public void guiTablero () {

        Scanner scanner = new Scanner(System.in);

        String [][] tablero = new String[11][11]; //Tablero del juego

        for (int i = 0; i < tablero.length; i++) {      //Filas
            for (int j = 0; j < tablero.length; j++) { //Columnas

                if (i == 0 && j > 0) {
                    tablero[i][j] = "[" + j + "]";
                }
                else {
                    tablero[i][j] = "[ ]";
                }

                if (i > 0 && j == 0) {
                    tablero[i][j] = "[" + Letras.values()[i-1].toString() + "]"; //Ingresa las letras del tablero
                }

                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }

        System.out.print("Ingresa la coordenada que deseas: ");
        String coordenada = scanner.nextLine();

        char posicionXString = coordenada.charAt(0); //Obtiene la letra
        int posicionY = Integer.parseInt(coordenada.substring(1)); //Obtiene el numero
        posicionXString = Character.toUpperCase(posicionXString); //Transforma la letra a mayuscula

        int posicionX = (int) posicionXString; //Transforma la letra a su codigo ASCII

        posicionX = transformarCoordenada(posicionX);

    }

    public int transformarCoordenada (int posicionX) { //Transforma codigo ASCII a numero entero
        int posicion = (posicionX - 65) + 1;
        return posicion;
    }

    enum Letras { //Letras del tablero
        A, B, C, D, E, F, G, H, I, J;
    } 
}
