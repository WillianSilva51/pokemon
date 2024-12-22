package app.screens;

import app.clearS.Clear;
import app.scanner.Scan;
import treinador.Treinador;

public class AppPokemon {
    public static void appPok(Treinador trainer) {
        while (true) {
            System.out.println(
                    "Opções para pokemons: [1] listar pokemons [2] curar pokemons, [3] remover pokemon [4] voltar");

            int choice = Scan.lerInt();

            switch (choice) {
                case 1:
                    System.out.println(trainer.listar());

                    System.out.println("Deseja ver os detalhes de um pokemon? [S] ou [N]");
                    String choice2 = Scan.lerString();

                    if (choice2.equalsIgnoreCase("s")) {
                        System.out.println("Qual pokemon deseja ver?");
                        int index = Scan.lerInt();
                        System.out.println(trainer.listar(index));
                    }

                    break;

                case 2:
                    trainer.curarPokemons();
                    break;

                case 3:
                    trainer.removerPokemon();
                    break;

                case 4:
                    System.out.println("Voltando ao menu do treinador.");
                    return;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            Clear.ClearScreen();
        }

    }

}
