package pokebolas;

import pokemon.Pokemon;
import random.Rand;

public class Pokebola implements Comparable<Pokebola> {
  protected String nome;
  protected float taxaBase;

  public Pokebola() {
    nome = "Pokebola";
    taxaBase = 1;
  }

  public float getTaxaBase(Pokemon p) {
    int hpAtual = p.getHpAtual();
    int hpMax = p.getHpMax();
    int taxaDeCaptura = p.getTaxaDeCaptura();

    return (((3 * hpMax) - (2 * hpAtual)) / (float) (3 * hpMax) * taxaDeCaptura * taxaBase);
  }

  public boolean capturar(Pokemon p) {
    float taxa = getTaxaBase(p);

    System.out.println(taxa);
    System.out.printf("Você tem %.2f%% de chance de capturar\n", taxa * 0.5);

    return Rand.aleatorio(1, 255) < taxa;
  }

  @Override
  public String toString() {
    return nome;
  }

  @Override
  public int compareTo(Pokebola o) {
    return nome.compareTo(o.nome);
  }
}
