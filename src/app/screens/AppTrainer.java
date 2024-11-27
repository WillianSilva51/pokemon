package app.screens;

import app.audio.Audios;
import app.clearS.Clear;
import app.scanner.Scan;
import pokemon.Pokemon;
import treinador.Treinador;

public class AppTrainer {
    public static void trainer(Treinador trainer, Pokemon pikachu) throws Exception {
        Audios.iniciarMusica("src/app/audio/music/trainer.wav");
        Audios.loopMusica();

        while (true) {
            System.out.println(trainer.toString());

            System.out.println(
                    "Opções do treinador: [1] nome, [2] Menu do Pokemons, [3] voltar");
            int choice = Scan.lerInt();

            switch (choice) {
                case 1:
                    trainer.mudarNome();
                    break;

                case 2:
                    AppPokemon.appPok(trainer, pikachu);
                    break;

                case 3:
                    System.out.println("Voltando ao menu principal.");
                    Audios.pararMusica();
                    return;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            Clear.ClearScreen();
        }

    }

    public static String iniciarTreinador() {
        while (true) {
            System.out.println("Treinador, como é seu nome?");

            return Scan.lerString();
        }

    }
}
