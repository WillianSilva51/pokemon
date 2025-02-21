package treinador;

import app.audio.Audios;
import app.scanner.Scan;
import pokebolas.Pokebola;
import pokemon.Pokemon;

public class Batalha {
  private Treinador trainer;
  private Pokemon pokemon;
  private boolean terminou = false;

  public Batalha(Treinador trainer, Pokemon pokemon) {
    this.trainer = trainer;
    this.pokemon = pokemon;
  }

  public void iniciar() {
    Audios.pararMusica();
    Audios.iniciarMusica("src/app/audio/music/battle.wav");
    Audios.loopMusica();

    System.out.println("Você encontrou um " + pokemon.getNome() + "!");
  }

  public boolean terminou() {
    return terminou;
  }

  public void proximoTurno() {
    System.out.println("Escolha uma ação:");
    System.out.println("1. Tentar Capturar");
    System.out.println("2. Fugir");

    String escolha = Scan.lerString();

    switch (escolha) {
      case "1":
        Pokebola pokebola = trainer.arremessarPokebola();

        if (pokebola != null) {
          if (tentarCaptura(pokebola)) {
            terminou = true;
          }

          else if (Math.random() > 0.5) {
            System.out.println("O " + pokemon.getNome() + " escapou!");
            trainer.getPokedex().registrarEncontro(pokemon);

            terminou = true;
          }
        }

        else {
          terminou = true;
        }

        break;
      case "2":
        System.out.println("Você fugiu da batalha!");
        trainer.getPokedex().registrarEncontro(pokemon);
        
        terminou = true;
        break;

      default:
        System.out.println("Escolha inválida.");
        break;
    }
  }

  private boolean tentarCaptura(Pokebola pokebola) {
    System.out.println("Tentando capturar " + pokemon.getNome() + " com " + pokebola.getClass().getSimpleName());

    return trainer.capturar(pokemon, pokebola);
  }
}
