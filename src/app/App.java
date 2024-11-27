package app;

import app.audio.Audios;
import app.clearS.Clear;
import app.scanner.Scan;
import app.screens.AppTrainer;
import map.Mapa;
import pokemon.Pokemon;
import treinador.Treinador;

public class App {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String[] colors = { "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m",
            "\u001B[36m", "\u001B[37m", "\u001B[90m", "\u001B[91m", "\u001B[92m", "\u001B[93m", "\u001B[94m",};
    private static final String color = colors[(int) (Math.random() * colors.length)];

    public static void main(String[] args) throws Exception {
        Audios.iniciarMusica("src/app/audio/music/inicio.wav");
        Audios.loopMusica();
        String[] ascii = new String[6];

        ascii[0] = " ██████╗  ██████╗ ██╗  ██╗███████╗███╗   ███╗ ██████╗ ███╗   ██╗";
        ascii[1] = " ██╔══██╗██╔═══██╗██║ ██╔╝██╔════╝████╗ ████║██╔═══██╗████╗  ██║";
        ascii[2] = " ██████╔╝██║   ██║█████╔╝ █████╗  ██╔████╔██║██║   ██║██╔██╗ ██║";
        ascii[3] = " ██╔═══╝ ██║   ██║██╔═██╗ ██╔══╝  ██║╚██╔╝██║██║   ██║██║╚██╗██║";
        ascii[4] = " ██║     ╚██████╔╝██║  ██╗███████╗██║ ╚═╝ ██║╚██████╔╝██║ ╚████║";
        ascii[5] = " ╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝";

        for (String string : ascii) {
            System.out.print("                                              ");
            System.out.println(color + string + ANSI_RESET);
            Thread.sleep(450);
        }

        Treinador trainer = new Treinador(AppTrainer.iniciarTreinador());

        trainer.pokemonInicial();

        Mapa mapinha = new Mapa();

        Pokemon pikachu = new Pokemon();

        Clear.ClearScreen();

        while (true) {

            for (String string : ascii) {
                System.out.print("                                                                  ");
                System.out.println(color + string + ANSI_RESET);
            }

            mapinha.mostrarMapa();

            System.out.println("Comandos disponíveis: [1] mover, [2] treinador, [3] sair");

            System.out.println("Faça sua escolha:");
            int choice = Scan.lerInt();

            switch (choice) {
                case 1:
                    trainer.moveChoice(mapinha);
                    break;

                case 2:
                    Clear.ClearScreen();
                    Audios.pararMusica();
                    AppTrainer.trainer(trainer, pikachu);
                    Audios.iniciarMusica("src/app/audio/music/inicio.wav");
                    break;

                case 3:
                    System.out.println("Saindo do jogo. Até logo!");
                    Scan.close();
                    Audios.pararTudo();
                    return; // Encerrar o programa

                default:
                    System.out.println("Comando Inválido, tente novamente.");
            }

            Clear.ClearScreen();
        }
    }
}