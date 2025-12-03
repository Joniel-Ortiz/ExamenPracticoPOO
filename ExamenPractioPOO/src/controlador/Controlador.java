package controlador;

import vista.*;

import java.util.List;

import modelos.*;

public class Controlador {

  private Vista vista;
  private Tablero tablero;
  private List<Jugador> listaJugadores;

  public Controlador() {

  }

  public Controlador(Vista vista, List<Jugador> listaJugadores) {
    this.vista = vista;
    this.listaJugadores = listaJugadores;

  }

  //By: Joniel Ortiz
  //Permite agregar un nuevo jugador a la lista de jugadores
  public void agregarJugador (String nombre) {
    listaJugadores.add(new Jugador(nombre));

  }

  //By: Joniel Ortiz
  //Retorna la lista de jugadores
  public List<Jugador> retornarListaJugadores () {
    return listaJugadores;
  }

  // By: Joniel Ortiz
  // Metodo que transforma el codigo ASCII a numero entero
  public int[][] codigoAsciiANumero(String coordenada) {
    char posicionXString = coordenada.charAt(0);
    posicionXString = Character.toUpperCase(posicionXString);
    int posicionX = (int) posicionXString;
    int posicionY = coordenada.charAt(1);
    posicionX = (posicionX - 65) + 1;
    int[][] posiciones = new int[posicionX][posicionY];
    return posiciones;
  }

  // By: Joniel Ortiz
  // Metodo que crea el tablero y lo muestra en la vista
  public void tableroAVista() {
    Tablero tablero = new Tablero();
    tablero.colocarMinas(10);
    vista.mostrarTablero(tablero.getCasillasCompletas());
  }

  public Vista getVista() {
    return vista;
  }

  public void setVista(Vista vista) {
    this.vista = vista;
  }

  public Tablero getTablero() {
    return tablero;
  }

  public void setTablero(Tablero tablero) {
    this.tablero = tablero;
  }
}