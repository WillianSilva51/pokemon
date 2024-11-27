package map;

import java.util.ArrayList;

import treinador.Treinador;

public class Mapa {
    private ArrayList<ArrayList<String>> mapinha = new ArrayList<>();
    private int x = 3;
    private int y = 3;

    public Mapa() {
        for (int i = 0; i < 7; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                if (i == 0 || i == 6) {
                    row.add("~");
                }
                else if (j == 0 || j == 6) {
                    row.add("|");
                } else {
                    row.add("-");
                }
            }
            mapinha.add(row);
        }

        mapinha.get(3).set(3, "T");
    }

    public void moveUp(Treinador trainer) {
        int previousX = trainer.getX();

        x = trainer.getX() - 1;
        y = trainer.getY();

        if (posicaoValida()) {
            mapinha.get(previousX).set(y, "-");
            mapinha.get(x).set(y, "T");
            trainer.setX(x);
        } else {
            System.out.println("Movimento inválido");
        }
    }

    public void moveDown(Treinador trainer) {
        int previousX = trainer.getX();

        x = trainer.getX() + 1;
        y = trainer.getY();

        if (posicaoValida()) {
            mapinha.get(previousX).set(y, "-");
            mapinha.get(x).set(y, "T");
            trainer.setX(x);
        } else {
            System.out.println("Movimento inválido");
        }
    }

    public void moveLeft(Treinador trainer) {
        int previousY = trainer.getY();

        x = trainer.getX();
        y = trainer.getY() - 1;

        if (posicaoValida()) {
            mapinha.get(x).set(previousY, "-");
            mapinha.get(x).set(y, "T");
            trainer.setY(y);
        } else {
            System.out.println("Movimento inválido");
        }
    }

    public void moveRight(Treinador trainer) {
        int previousY = trainer.getY();

        x = trainer.getX();
        y = trainer.getY() + 1;

        if (posicaoValida()) {
            mapinha.get(x).set(previousY, "-");
            mapinha.get(x).set(y, "T");
            trainer.setY(y);
        } else {
            System.out.println("Movimento inválido");
        }
    }

    private boolean posicaoValida() {
        return x >= 1 && x < mapinha.size() - 1 && y >= 1 && y < mapinha.size() - 1;
    }

    public void mostrarMapa() {
        System.out.println("Mapa:");
        for (int i = 0; i < mapinha.size(); i++) {
            System.out.print("          ");
            for (int j = 0; j < mapinha.size(); j++) {
                System.out.print(mapinha.get(i).get(j));
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Mapa:\n" + mapinha;
    }

}
