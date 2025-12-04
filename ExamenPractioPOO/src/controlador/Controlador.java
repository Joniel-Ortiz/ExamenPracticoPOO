package controlador;

import vista.*;
import java.util.List;
import modelos.*;

public class Controlador {

  private Vista vista;
  private Tablero tablero;
  private List<Jugador> listaJugadores;

  // (CRISTIAN: Indica si el juego ya terminó, para que la vista pueda detener el loop)
  private boolean juegoTerminado = false;

  public Controlador () {

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
  
  //By: Joniel Ortiz
  //Verifica si la posicion es visible y retorna true o false dependiendo del caso
  public boolean esVisible (String coordenadas) {
    char posicionXChar = coordenadas.charAt(0);
    String posicionXString = String.valueOf(posicionXChar);
    int posicionX = codigoAsciiANumero(posicionXString);
    String posicionYString = coordenadas.substring(1);
    int posicionY = Integer.parseInt(posicionYString);
    
    Casilla casilla = tablero.getCasilla(posicionX, posicionY);
    // (CRISTIAN: aquí chequeo si la casilla existe y si ya está revelada)
    if (casilla != null && casilla.isRevelada()) {
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
    vista.mostrarTablero(tablero.getCasillasCompletas());
    return tablero.getCasillasCompletas();
  }

  // Cristian Rondal - Método para inicializar el tablero sin mostrarlo
  // Este método crea el tablero una sola vez y evita que se muestre
  // automáticamente.
  // (NOTA: usamos tamaño fijo 10 aquí para evitar usar un constructor Tablero() inexistente)
  public void inicializarTablero(int minas) {
    // (CRISTIAN: si Tablero() sin parámetros NO existe, inicializamos con tamaño 10)
    this.tablero = new Tablero(10);   // <-- si Tablero(int) existe, usamos esto
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

  // Cristian Rondal - Método para procesar cuando el jugador intenta revelar una casilla
  // Aquí verifico si hay mina. Si hay mina el juego termina y se revelan todas las minas.
  public boolean revelarCasilla(int fila, int columna) {

    // Revelo la casilla normalmente
    tablero.revelar(fila, columna);

    Casilla casilla = tablero.getCasilla(fila, columna);

    // Si esta casilla tiene una mina EL JUGADOR PIERDE
    if (casilla.isMina()) {

      // Cristian Rondal - revelo todas las minas para mostrar el tablero final
      revelarTodasLasMinas();

      // Cristian Rondal - mensaje para indicar que perdió
      vista.mostrarMensaje("¡Pisaste una mina! Perdiste el juego.");

      juegoTerminado = true;
      return false; // indica que perdió
    }

    return true; // sigue jugando
  }

  // Cristian Rondal - Método para revelar todas las minas cuando el jugador pierde
  private void revelarTodasLasMinas() {

    for (int f = 1; f <= 10; f++) {
      for (int c = 1; c <= 10; c++) {

        Casilla casilla = tablero.getCasilla(f, c);

        if (casilla.isMina()) {
          casilla.setRevelada(true);
        }
      }
    }

    // Actualizo la vista para mostrar todas las minas
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

  public boolean manejarVerificarDerrota (int posicionX, int posicionY) {
    boolean respuesta = tablero.verificarDerrota(posicionX,posicionY);
    return respuesta;
  }


  public boolean manejarVerificarVictoria () {
    boolean respuesta = tablero.verificarVictoria();
    return respuesta;
  }

  public int manejarTamanioTablero () {
    int tamanio = vista.pedirTamanioTablero();
    return tamanio;
  }

  public void iniciarJuego (String nombreJugador) {
    agregarJugador(nombreJugador);
    int tamanio = manejarTamanioTablero();
    this.tablero = new Tablero(tamanio);
    this.tablero.colocarMinas(tamanio);
    vista.mostrarTablero(tableroAVista());
    this.tablero.contarMinasAlrededor();
    this.tablero.verificarVictoria();
  }

  // (CRISTIAN: indica si el juego terminó para que la vista pueda cerrar bucles)
  public boolean isJuegoTerminado() {
    return juegoTerminado;
  }
}