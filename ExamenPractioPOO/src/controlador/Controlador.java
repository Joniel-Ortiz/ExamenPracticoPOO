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

  public void esVisible(Tablero tablero) {

  }

  // CRISTIAN: Método para inicializar el tablero sin mostrarlo
  // Este método crea el tablero una sola vez y evita que se muestre
  // automáticamente.
  public void inicializarTablero(int minas) {
    this.tablero = new Tablero();
    tablero.colocarMinas(minas);
  }

  // CRISTIAN: Método para revelar una casilla del tablero
  // Uso: la vista llama a este método cuando el jugador marca una casilla.
  public boolean revelarCasilla(int fila, int columna) {
    if (tablero == null)
      return false;
    return tablero.revelar(fila, columna);
  }

  // CRISTIAN: Método para pedir a la vista que muestre SOLO lo revelado)
  // Evita mostrar el tablero completo desde el inicio.
  public void actualizarVista() {
    if (vista != null && tablero != null) {
      vista.mostrarTablero(tablero.getCasillasCompletas());
    }
  }

  // (CRISTIAN: Método que verifica si el jugador ganó)
  public boolean juegoGanado() {
    if (tablero == null)
      return false;
    return tablero.verificarVictoria();
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