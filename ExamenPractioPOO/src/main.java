import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import modelos.*;
import vista.*;

public class main {
  public static void main(String[] args) {
    List<Jugador> listaJugadores = new ArrayList<>();
    Vista vista = new Vista();
    Controlador controlador = new Controlador(vista, listaJugadores);
    vista.setControlador(controlador);
    vista.menuPrincipal();
  }
}
