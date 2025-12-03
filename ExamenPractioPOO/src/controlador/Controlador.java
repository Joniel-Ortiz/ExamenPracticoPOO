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

  public Controlador(Vista vista, List<Jugador> listaJugadores, Tablero tablero) {
    this.vista = vista;
    this.listaJugadores = listaJugadores;
    this.tablero = tablero;
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
  
  //By: Joniel Ortiz
  //Verifica si la posicion es visible y retorna true o false dependiendo del caso
  public boolean esVisible (String coordenadas) {
    char posicionXChar = coordenadas.charAt(0);
    String posicionXString = String.valueOf(posicionXChar);
    int posicionX = codigoAsciiANumero(posicionXString);
    String posicionYString = coordenadas.substring(1);
    int posicionY = Integer.parseInt(posicionYString);
    
    var posiciones = tablero.getCasilla(posicionX, posicionY);
    if (posiciones.equals(posiciones)) {
      return true;
    }

    return false;
  }

  // By: Joniel Ortiz
  // Metodo que transforma el codigo ASCII a numero entero
  public int codigoAsciiANumero(String coordenada) {
    char posicionXString = coordenada.charAt(0);
    posicionXString = Character.toUpperCase(posicionXString);
    int posicionX = (int) posicionXString;
    posicionX = (posicionX - 65) + 1;
    return posicionX;
  }

  //By: Joniel Ortiz
  // Metodo que transforma String a Int
  public int stringAInt (String valorString) {
    int valorInt = Integer.parseInt(valorString);
    return valorInt;
  }

  // By: Joniel Ortiz
  // Metodo que crea el tablero y lo muestra en la vista
  public Casilla[][] tableroAVista() {
    tablero.colocarMinas(10);
    vista.mostrarTablero(tablero.getCasillasCompletas());
    return tablero.getCasillasCompletas();
  }

  // CRISTIAN: Método para inicializar el tablero sin mostrarlo
  // Este método crea el tablero una sola vez y evita que se muestre
  // automáticamente.
  public void inicializarTablero(int minas) {
    this.tablero = new Tablero();
    tablero.colocarMinas(minas);
  }

  // Método para pedir a la vista que muestre SOLO lo revelado)
  // Evita mostrar el tablero completo desde el inicio.
  public void actualizarVista() {
    if (vista != null && tablero != null) {
      vista.mostrarTablero(tablero.getCasillasCompletas());
    }
  }

  //  Método que verifica si el jugador ganó)
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