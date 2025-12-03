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
  // Pide el nombre del jugador que va a jugar
  public String pedirDatosJugador() {
    System.out.print("Introduce tu nombre: ");
    String nombre = sc.next();
    System.out.println("Jugador creado con exito!");
    return nombre;
  }

  //By: Joniel Ortiz
  //Muesta la lista de jugadores que existen
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

  public void setControlador(Controlador controlador) {
    this.controlador = controlador;
  }
}