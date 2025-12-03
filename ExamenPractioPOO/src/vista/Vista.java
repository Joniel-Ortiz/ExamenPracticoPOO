package vista;

import java.util.Scanner;
import controlador.*;
import modelos.*;

import java.util.List;
import java.util.Random;

public class Vista {

  Scanner sc = new Scanner(System.in);
  Controlador controlador;

  // By: Joniel Ortiz
  // Metodo que mostrara un menú interactivo al usuario
  public void menuPrincipal() {

    boolean finalizar = false;

    while (!finalizar) {
      System.out.println("");
      System.out.println("Menu:");
      System.out.println("1. Iniciar Juego");
      System.out.println("2. Cargar Partida");
      System.out.println("3. Mostrar Lista de Jugadores");
      System.out.println("4. Finalizar");
      System.out.print("Opcion: ");
      int opcion = sc.nextInt();
      switch (opcion) {
        case 1:
          String nombreJugador = pedirDatosJugador();
          controlador.agregarJugador(nombreJugador);
          controlador.tableroAVista();
          break;
        case 2:
          System.out.println("");
          System.out.println("Cargando Partida...");
          System.out.println("");
          break;
        case 3:
          mostrarListaJugadores(controlador.retornarListaJugadores());
          break;
        case 4:
          finalizar = true;
          System.out.println("");
          System.out.println("Finalizando Programa...");
          System.out.println("");
          break;
      }
    }
  }

  // By: Joniel Ortiz
  // Metodo que pide el nombre del jugador que jugará
  public String pedirDatosJugador() {
    System.out.print("Introduce tu nombre: ");
    String nombre = sc.next();
    System.out.println("Jugador creado con exito!");
    return nombre;
  }

  public void mostrarListaJugadores(List<Jugador> listaJugadores) {
    System.out.println("");
    System.out.println("------------------Lista de jugadores------------------");
    listaJugadores.forEach(jugador -> {
      System.out.println(jugador);
    });
    System.out.println("------------------------------------------------------");
  }

  // By: Joniel Ortiz
  // Metodo que muestra el tablero creado en el controlador
  public void mostrarTablero(Casilla[][] casillas) {
    System.out.println("");
    for (int i = 0; i < casillas.length; i++) { // Filas del Tablero
      for (int j = 0; j < casillas.length; j++) { // Columnas del Tablero
        char letras = (char) ('A' + i - 1); // Empieza con la letra A y termina en la letra J
        if (i > 0 && j == 0) {
          System.out.print("[" + letras + "]"); // Imprime las letras en la columna 0
        } else if (i == 0 && j > 0) {
          System.out.print("[" + j + "]"); // Imprime los numeros de la fila 0
        } else if (i == 0 && j == 0) {
          System.out.print("[ ]"); // Imprime la fila 0 y columna 0 vacia
        } else {
          System.out.print("[?]"); // Imprime los espacios vacios
        }
      }
      System.out.println(""); // Salto de linea
    }
    String coordenadaCasilla = pedirCasilla(); // Almacena el dato que introdujo el usuario en la variable
                                               // "coodenadaCasilla"

  }

  // By: Joniel Ortiz
  // Metodo que pide la coordenada de la casilla que desea revelar
  public String pedirCasilla() {
    System.out.println("");
    System.out.print("Escoge la casilla que deseas, por ejemplo (A5): ");
    String casilla = sc.next();
    return casilla;
  }

  // Cuenta las casillas que aún no han sido descubiertas
  public int contarMinasRestantes(String[][] tableroVisible) {
    int minasOcultas = 0;

    for (int i = 1; i < tableroVisible.length; i++) {
      for (int j = 1; j < tableroVisible.length; j++) {
        if (tableroVisible[i][j].equals("[?]")) {
          minasOcultas++;
        }
      }
    }
    return minasOcultas;
  }

  // Coloca minas en posiciones aleatorias del tablero real

  // Transformo las letras (A-J) en valores numéricos (1-10)
  public int transformarCoordenada(char letra) {
    return (letra - 'A') + 1;
  }

  // Contar cuántas minas hay alrededor de una casilla
  public int contarMinasVecinas(String[][] tablero, int fila, int columna) {
    int minas = 0;
    for (int i = fila - 1; i <= fila + 1; i++) {
      for (int j = columna - 1; j <= columna + 1; j++) {
        if (i > 0 && i < tablero.length && j > 0 && j < tablero.length) {
          if (tablero[i][j].equals("[X]")) {
            minas++;
          }
        }
      }
    }
    return minas;
  }

  // Destapa recursivamente casillas seguras
  public void destaparCasillas(String[][] tablero, String[][] tableroVisible, int fila, int columna) {

    if (!tableroVisible[fila][columna].equals("[?]")) {
      return;
    }

    int minasCerca = contarMinasVecinas(tablero, fila, columna);

    // Si no hay minas cerca, pongo [V] (vacío)
    if (minasCerca == 0) {
      tableroVisible[fila][columna] = "[V]";
    } else {
      tableroVisible[fila][columna] = "[" + minasCerca + "]";
    }

    if (minasCerca > 0) {
      return;
    }

    for (int i = fila - 1; i <= fila + 1; i++) {
      for (int j = columna - 1; j <= columna + 1; j++) {
        if (i > 0 && i < tablero.length && j > 0 && j < tablero.length) {
          destaparCasillas(tablero, tableroVisible, i, j);
        }
      }
    }
  }

  // Revisa que ya no queden casillas ocultas
  public boolean verificarVictoria(String[][] tableroVisible) {
    for (int i = 1; i < tableroVisible.length; i++) {
      for (int j = 1; j < tableroVisible.length; j++) {
        if (tableroVisible[i][j].equals("[?]")) {
          return false;
        }
      }
    }
    return true;
  }

  // Cuando pierdo o gano, revelo todas las minas
  public void mostrarMinas(String[][] tablero, String[][] tableroVisible) {
    for (int i = 1; i < tablero.length; i++) {
      for (int j = 1; j < tablero.length; j++) {
        if (tablero[i][j].equals("[X]")) {
          tableroVisible[i][j] = "[X]";
        }
      }
    }
  }

  public void setControlador(Controlador controlador) {
    this.controlador = controlador;
  }
}