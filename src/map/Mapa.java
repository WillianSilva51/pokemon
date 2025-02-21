package map;

import java.util.ArrayList;

import app.audio.Audios;
import app.clearS.Clear;
import app.scanner.Scan;
import pokemon.Encontro;
import treinador.Batalha;
import treinador.Treinador;

public class Mapa {
    private ArrayList<ArrayList<String>> mapinha;
    private int x = 1;
    private int y = 1;

    public Mapa() {
        mapinha = new ArrayList<>();

        inicializarMapa();
    }

    private void inicializarMapa() {
        for (int i = 0; i < 9; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 8) {
                    row.add("~");
                } else if (j == 0 || j == 8) {
                    row.add("|");
                } else {
                    if (Math.random() < 0.5) {
                        row.add("w"); 
                    } else {
                        row.add("-");
                    }
                }
            }
            mapinha.add(row);
        }

        mapinha.get(x).set(y, "T");
    }

    private final void movimentoInvalido() {
        System.out.println("Movimento inválido");
        if (System.console().readLine("Pressione Enter para continuar...") == null) {
        }
    }

    public void moveChoice(Treinador trainer) {
        Audios.pararMusica();
        Audios.iniciarMusica("src/app/audio/music/move.wav");
        while (true) {
            Clear.clear();
            mostrarMapa();

            System.out.println(
                    "Para qual direção quer se mover?\n [W] Cima\n [S] Baixo\n [D] Direita\n [A] Esquerda\n [C] Cancelar movimento");

            String choice = Scan.lerString();

            switch (choice.toLowerCase()) {
                case "w":
                    moveUp(trainer);
                    break;

                case "s":
                    moveDown(trainer);
                    break;

                case "d":
                    moveRight(trainer);
                    break;

                case "a":
                    moveLeft(trainer);
                    break;

                case "c":
                    System.out.println("Movimento cancelado");
                    return;

                default:
                    System.out.println("Não existe essa opção.");
                    break;
            }
        }
    }

    private void matagal(Treinador trainer) {
        if (mapinha.get(x).get(y).equals("w")) {
            if (Math.random() < 0.5) {
                Batalha a = new Batalha(trainer, Encontro.getRandomPokemon());

                a.iniciar();

                do {
                    a.proximoTurno();
                } while (a.terminou() != true);

                Audios.pararMusica();

                Audios.iniciarMusica("src/app/audio/music/move.wav");

                Clear.ClearScreen();
            }
        }
    }

    public void moveUp(Treinador trainer) {
        int previousX = trainer.getX();

        x = trainer.getX() - 1;
        y = trainer.getY();

        if (posicaoValida()) {
            matagal(trainer);
            mapinha.get(previousX).set(y, "-");
            mapinha.get(x).set(y, "T");
            trainer.setX(x);
        } else {
            movimentoInvalido();
        }
    }

    public void moveDown(Treinador trainer) {
        int previousX = trainer.getX();

        x = trainer.getX() + 1;
        y = trainer.getY();

        if (posicaoValida()) {
            matagal(trainer);
            mapinha.get(previousX).set(y, "-");
            mapinha.get(x).set(y, "T");
            trainer.setX(x);
        } else {
            movimentoInvalido();
        }
    }

    public void moveLeft(Treinador trainer) {
        int previousY = trainer.getY();

        x = trainer.getX();
        y = trainer.getY() - 1;

        if (posicaoValida()) {
            matagal(trainer);
            mapinha.get(x).set(previousY, "-");
            mapinha.get(x).set(y, "T");
            trainer.setY(y);
        } else {
            movimentoInvalido();
        }
    }

    public void moveRight(Treinador trainer) {
        int previousY = trainer.getY();

        x = trainer.getX();
        y = trainer.getY() + 1;

        if (posicaoValida()) {
            matagal(trainer);
            mapinha.get(x).set(previousY, "-");
            mapinha.get(x).set(y, "T");
            trainer.setY(y);
        } else {
            movimentoInvalido();
        }
    }

    private boolean posicaoValida() {
        return (x >= 1 && x < mapinha.size() - 1) && (y >= 1 && y < mapinha.size() - 1);
    }

    public void mostrarMapa() {
        System.out.println("Mapa:");
        for (int i = 0; i < mapinha.size(); i++) {
            System.out.print("       ");
            for (int j = 0; j < mapinha.size(); j++) {
                System.out.print(mapinha.get(i).get(j) + "  ");
            }
            System.out.println();
        }
    }

    // \u001B[0m \u001B[32m

    @Override
    public String toString() {
        return "Mapa:\n" + mapinha;
    }
}
