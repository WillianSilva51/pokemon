package app.screens;

import app.clearS.Clear;
import app.scanner.Scan;
import pokemon.Pokemon;
import treinador.Treinador;

public class AppPokemon {
    public static void appPok(Treinador trainer, Pokemon pikachu) throws Exception {
        while (true) {
            System.out.println(
                    "Opções para pokemons: [1] capturar [2] listar pokemons, [3] curar pokemons, [4] remover pokemon [5] voltar");

            int choice = Scan.lerInt();

            switch (choice) {
                case 1:
                    System.out.println("Você tenta capturar um " + pikachu.toString2());
                    trainer.capturar(pikachu);
                    break;

                case 2:
                    System.out.println(trainer.listar());

                    System.out.println("Deseja ver os detalhes de um pokemon? [S] ou [N]");
                    String choice2 = Scan.lerString();

                    if (choice2.equalsIgnoreCase("s")) {
                        System.out.println("Qual pokemon deseja ver?");
                        int index = Scan.lerInt();
                        System.out.println(trainer.listar(index));
                    }
                    break;

                case 3:
                    trainer.removerPokemon();
                    break;

                case 4:
                    trainer.curarPokemons();
                    break;

                case 5:
                    System.out.println("Voltando ao menu do treinador.");
                    return;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            Clear.ClearScreen();
        }

    }

}
