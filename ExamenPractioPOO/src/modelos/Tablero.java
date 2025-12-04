package modelos;

import java.util.Random;
import controlador.*;

public class Tablero {

  private Casilla[][] casillas; // Matriz del tablero real (minas y casillas vacías)
  private Mina mina;
  private Controlador controlador;
  private int tamanio;
  // CRISTIAN: Constructor del tablero, inicializo la matriz de casillas
  // coloco 10 minas aleatorias y luego calculo el número de minas alrededor
  // para cada casilla (esto se hace después de colocar las minas).
  public Tablero(int tamanio) {
    this.tamanio = tamanio;
    this.casillas = new Casilla[tamanio+1][tamanio+1];

    for (int i = 0; i < casillas.length; i++) {
      for (int j = 0; j < casillas[i].length; j++) {
        casillas[i][j] = new Casilla();
      }
    }

    // PRIMERO: colocar 10 minas
    this.colocarMinas(tamanio);

    // CRISTIAN: despues de colocar las minas calculo los numeros alrededor
    this.contarMinasAlrededor();
  }

  // MétrMinas(10)odo que coloca una cantidad de minas aleatorias en el tablero)
public void colocarMinas(int cantidadMinas) {
    Random random = new Random();
    int minasColocadas = 0;

    while (minasColocadas < cantidadMinas) {

        int filaAleatoria = random.nextInt(tamanio) + 1; 
        int columnaAleatoria = random.nextInt(tamanio) + 1;

        // Evitar colocar una mina repetida
        if (!casillas[filaAleatoria][columnaAleatoria].isMina()) {
            Mina nuevaMina = new Mina(filaAleatoria, columnaAleatoria);
            casillas[filaAleatoria][columnaAleatoria].setMina(nuevaMina);
            minasColocadas++;
        }
    }
}


  // By:LASTRA DYLLAN 
  // Devuelve toda la matriz de casillas
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

    if (!estaDentro(fila, columna))
      return false;

    Casilla casilla = casillas[fila][columna];

    // Si ya estaba revelada → no hacer nada
    if (casilla.isRevelada())
      return true;

    // Revelo la casilla
    casilla.setRevelada(true);

    // Si es mina → retornar false (pierde)
    if (casilla.isMina()) {
      return false;
    }

    // Si NO es mina y NO tiene números → expansión automática
    if (casilla.getNumeroAlrededor() == 0) {
      revelarCascada(fila, columna);
    }

    return true; // sigue jugando
  }

  // CHATO: Metodo contarMinasAlrededor

  public void contarMinasAlrededor() {

    int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    for (int f = 1; f < casillas.length; f++) {
      for (int c = 1; c < casillas.length; c++) {

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

  // Cristian agregue este metodo de expansión automática cuando la casilla es 0
  private void revelarCascada(int fila, int columna) {

    int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    for (int i = 0; i < 8; i++) {
      int nf = fila + dx[i];
      int nc = columna + dy[i];

      if (!estaDentro(nf, nc))
        continue;

      Casilla actual = casillas[nf][nc];

      // Si ya está revelada, no la volvemos a revelar
      if (actual.isRevelada())
        continue;

      // Revelamos la casilla
      actual.setRevelada(true);

      // Si esta casilla también es 0 → seguir expandiendo
      if (actual.getNumeroAlrededor() == 0 && !actual.isMina()) {
        revelarCascada(nf, nc);
      }
    }
  }

  // By:LASTRA DYLLAN
  // Verifica si una posición está dentro del tablero (1 a 10)
  private boolean estaDentro(int f, int c) {
    return (f >= 1 && f <= casillas.length - 1 && c >= 1 && c <= casillas.length - 1);
  }

  public boolean verificarDerrota(int posicionX, int posicionY) {
    if (casillas[posicionX][posicionY].isMina()) {
      return true;
    }

    return false;
  }

//CHATO: Metodo de victoria
public boolean verificarVictoria() {
    for (int i = 1; i < casillas.length; i++) {
        for (int j = 1; j < casillas.length; j++) {
            if (!casillas[i][j].isRevelada() && !casillas[i][j].isMina()) {
                return false;
            }
        }
    }
    return true;
}
}