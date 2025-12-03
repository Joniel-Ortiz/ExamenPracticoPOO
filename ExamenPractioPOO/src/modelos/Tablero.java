package modelos;

import java.util.Random;

public class Tablero {

  private Casilla[][] casillas; // Matriz del tablero real (minas y casillas vacías)
  private Mina mina;
  // Constructor que crea un tablero de 10x10 + fila/columna de encabezado)
  public Tablero() {
    this.casillas = new Casilla[11][11];

    // Lleno TODO el tablero con objetos Casilla para evitar nul

    for (int i = 0; i < casillas.length; i++) {
      for (int j = 0; j < casillas[i].length; j++) { // ← CORREGIDO AQUÍ
        casillas[i][j] = new Casilla(); // Casilla vacía por defecto
      }
    }
//chato:iniciodejuego

this.contarMinasAlrededor();

}
  // MétrMinas(10)odo que coloca una cantidad de minas aleatorias en el tablero)
  public void colocarMinas(int cantidadMinas) {
    Random random = new Random();
    int minasColocadas = 0;

    while (minasColocadas < cantidadMinas) {

      int filaAleatoria = random.nextInt(10) + 1; // filas 1-10
      int columnaAleatoria = random.nextInt(10) + 1; // columnas 1-10

      Mina nuevaMina = new Mina(filaAleatoria,columnaAleatoria);
      // valido para no repetir minas)
        // Coloco la mina)
        casillas[filaAleatoria][columnaAleatoria].setMina(nuevaMina);
        minasColocadas++;
      }
    }
  

  // By:LASTRA DYLLAN: Devuelve toda la matriz de casillas
  public Casilla[][] getCasillasCompletas() {
    return casillas;
  }

  public Casilla getCasilla(int x, int y) {
    return casillas[x][y];
  }

  public void setCasilla(Casilla[][] casilla) {
    this.casillas = casilla;
  }

  public boolean revelar(int fila, int columna) {
    // Validación básica
    if (fila < 1 || fila > 10 || columna < 1 || columna > 10) {
      return false;
    }

    Casilla c = casillas[fila][columna];
    c.setRevelada(true);

    return true;
  }

  public boolean verificarVictoria() {

    // Si existe una casilla NO mina y NO revelada aún, el juego no se gana.
    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= 10; j++) {
        Casilla c = casillas[i][j];
        if (!c.isMina() && !c.isRevelada()) {
          return false;
        }
      }
    }

    return true; // No hay casillas pendientes
  }

  // CHATO: Metodo contarMinasAlrededor

  private void contarMinasAlrededor() {

    int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    for (int f = 1; f <= 10; f++) {
      for (int c = 1; c <= 10; c++) {

        if (casillas[f][c].isMina())
          continue;

        int total = 0;

        for (int i = 0; i < 8; i++) {
          int nf = f + dx[i];
          int nc = c + dy[i];

          if (estaDentro(nf, nc) && casillas[nf][nc].isMina()) {
            total++;
          }
        }

        casillas[f][c].setNumeroAlrededor(total);
      }
    }
  }

  // Verifica si una posición está dentro del tablero (1 a 10)
  private boolean estaDentro(int f, int c) {
    return (f >= 1 && f <= 10 && c >= 1 && c <= 10);
  }

  

}
