package modelos;

public class Casilla {
    private boolean revelada = false;
    private int numeroAlrededor = 0; // opcional: número de minas alrededor 
    private String simbolo = "[?]";
    private Mina mina;
    
    public Casilla() {
    }

    // Mina
    public boolean isMina() {
      return this.mina != null; 
  }

    public void setMina(Mina mina) {
      this.mina = mina;
    }

    // Revelado
    public boolean isRevelada() {
        return revelada;
    }
    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    // Número de minas alrededor (opcional)
    public int getNumeroAlrededor() {
        return numeroAlrededor;
    }
    public void setNumeroAlrededor(int n) {
        this.numeroAlrededor = n;
    }

    public String getSimbolo() {
      return simbolo;
    }

    public void setSimbolo(String simbolo) {
      this.simbolo = simbolo;
    }
    
}

