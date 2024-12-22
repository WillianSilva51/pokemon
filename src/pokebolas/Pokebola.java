package pokebolas;

import pokemon.Pokemon;

public class Pokebola {
  protected String nome;
  protected float taxaBase;

  public Pokebola() {
    nome = "Pokebola";
    taxaBase = 0.4f;
  }

  public float getTaxaBase(Pokemon p) {
    return taxaBase - (taxaBase * (p.getNivel() / 100f) + 0.05f);
  }

  public boolean capturar(Pokemon p) {
    System.out.println("Você tem " + getTaxaBase(p) * 100 + "% de chance de capturar");

    return Math.random() < getTaxaBase(p);
  }

  @Override
  public String toString() {
    return nome;
  }
}
