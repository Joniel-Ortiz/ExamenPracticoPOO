package modelos;

public class Jugador {
  private String nombre;
  private static int contador = 0;
  private int id;

  public Jugador(String nombre) {
    this.nombre = nombre;
    this.id = contador++;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return id + ". " + nombre;
  }

}
