package app.screens;

import app.clearS.Clear;
import app.scanner.Scan;
import treinador.Treinador;
import treinador.pokedex.MenuPokedex;

public class AppPokedex {
    public static void appPok(Treinador trainer) {
        MenuPokedex menuPokedex = new MenuPokedex(trainer.getPokedex());

        while (true) {
            System.out.println(
                    "Opções para pokemons: [1] Listar Pokemons Capturados [2] Listar Pokemons Vistos [3] Curar Pokemons [4] Remover Pokemon [5] voltar");

            int choice = Scan.lerInt();

            switch (choice) {
                case 1:
                    System.out.println(menuPokedex.listarCapturados());

                    System.out.println("Deseja ver os detalhes de um pokemon? [S] ou [N]");
                    String choice2 = Scan.lerString();

                    if (choice2.equalsIgnoreCase("s")) {
                        System.out.println("Qual pokemon deseja ver?");
                        int index = Scan.lerInt();
                        menuPokedex.verDetalhesPokemon(index);
                    }
                    break;

                case 2:
                    menuPokedex.listarVistos();
                    break;

                case 3:
                    menuPokedex.curarPokemons();
                    break;

                case 4:
                    menuPokedex.removerPokemon();

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
