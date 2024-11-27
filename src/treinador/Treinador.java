package treinador;

import java.util.ArrayList;

import app.audio.Audios;
import app.clearS.Clear;
import app.scanner.Scan;
import map.Mapa;
import pokemon.Pokemon;
import random.Rand;

public class Treinador {
    private String nome;
    private int x, y;
    private ArrayList<Pokemon> lista = new ArrayList<>(6);
    private int pokebolas = 5;

    public Treinador() {
        this.nome = "Ash";
        x = 3;
        y = 3;
    }

    public Treinador(String nome) {
        x = 3;
        y = 3;

        this.nome = nome;
    }

    public int setX(int x) {
        return this.x = x;
    }

    public int setY(int y) {
        return this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void pokemonInicial() {
        System.out.println("Olá, " + nome + "! Bem-vindo ao mundo dos pokemons!");
        System.out.println("Escolha o seu pokemon inicial: [1] Bulbassauro, [2] Charmander, [3] Squirtle ");
        while (true) {
            String choice = Scan.lerString();

            switch (choice) {
                case "1":
                    lista.add(new Pokemon("Bulbassauro", 1, "Grama"));
                    return;

                case "2":
                    lista.add(new Pokemon("Charmander", 1, "Fogo"));
                    return;

                case "3":
                    lista.add(new Pokemon("Squirtle", 1, "Água"));
                    return;

                default:
                    System.out.println("Escolha um dos pokemons disponíveis.");
                    break;
            }
        }
    }

    public void mudarNome() {
        System.out.println("Deseja mudar o seu nome? [S] ou [N]");

        while (true) {
            String choice = Scan.lerString();

            switch (choice.toLowerCase()) {
                case "s":
                    System.out.print("Adicione o seu novo nome: ");
                    this.nome = Scan.lerString();
                    System.out.println("O seu novo nome é " + nome + ", belo nome!");
                    return;

                case "n":
                    System.out.println("Certo, sem mudança de nome por agora.");
                    return;

                default:
                    System.out.println("Escolha uma das opções: [S] ou [N]");
                    break;
            }
        }
    }

    public void moveChoice(Mapa mapinha) {
        while (true) {
            Clear.ClearScreen();
            mapinha.mostrarMapa();

            System.out.println(
                    "Para qual direção quer se mover?\n [W] Cima\n [S] Baixo\n [D] Direita\n [A] Esquerda\n [C] Cancelar movimento");

            String choice = Scan.lerString();

            switch (choice.toLowerCase()) {
                case "w":
                    mapinha.moveUp(this);
                    break;

                case "s":
                    mapinha.moveDown(this);
                    break;

                case "d":
                    mapinha.moveRight(this);
                    break;

                case "a":
                    mapinha.moveLeft(this);
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

    public void capturar(Pokemon p) throws Exception {
        if (pokebolas == 0) {
            System.out.println("Você não tem pokebolas.");
            return;
        }

        pokebolas--;

        if (Rand.aleatorioB()) {
            Audios.pararMusica();
            Audios.iniciarMusica("src/app/audio/soundEffects/capturado.wav");
            System.out.println("Pokemon capturado!");

            Thread.sleep(3000);

            Audios.iniciarMusica("src/app/audio/music/trainer.wav");

            if (lista.size() >= 6) {
                System.out.println("Sua equipe está cheia.");

                System.out.println("Deseja trocar um pokemon? [S] ou [N]");

                String choice = Scan.lerString();

                if (choice.equalsIgnoreCase("s")) {
                    trocarPokemon(p);

                } else {
                    System.out.println("Pokemon não trocado.");
                }
            }

            else {
                lista.add(p);
                lista.size();
            }
        }

        else {
            System.out.println("Não conseguiu capturar");
        }
    }

    public void trocarPokemon(Pokemon p) {
        removerPokemon();

        lista.add(p);
    }

    public void removerPokemon() {
        if (lista.isEmpty()) {
            System.out.println("Você não tem pokemons para remover ou trocar.");
            return;
        }

        while (true) {
            try {
                System.out.println(
                        "Você tem certeza que deseja remover ou trocar algum dos seus pokemons? (Após isso não poderá mais usá-lo) [S] ou [N]");
                String choice = Scan.lerString();

                if (choice.equalsIgnoreCase("s")) {
                    System.out.println(listar());
                    System.out.println("Escolha o número do pokemon que deseja remover:");
                    lista.remove(Scan.lerInt() - 1);
                    System.out.println("Pokemon removido");
                    return;
                }

                else {
                    System.out.println("Pokemon não removido ou trocado.");
                    return;
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Escolha um número válido.");
            }
        }
    }

    public String listar() {
        if (lista.isEmpty()) {
            return "Você não tem pokemons";
        }

        StringBuilder sb = new StringBuilder();

        for (Pokemon p : lista) {
            sb.append(lista.indexOf(p) + 1).append(": ").append(p.toString2()).append("\n");
        }

        return sb.toString();
    }

    public String listar(int index) {
        if (lista.isEmpty()) {
            return "Você não tem pokemons";
        }

        try {
            return lista.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Escolha um número válido.";
        }
    }

    public void curarPokemons() {
        if (lista.isEmpty()) {
            System.out.println("Você não tem pokemons para curar.");
            return;
        }

        System.out.println("Escolha uma opção [1] curar todos, [2] curar um pokemon específico ou [3] cancelar");
        int choice = Scan.lerInt();

        switch (choice) {
            case 1:
                System.out.println("Curando seus pokemons...");

                for (Pokemon p : lista) {
                    p.curar();
                }

                System.out.println("Todos os seus pokemons foram curados.");
                break;

            case 2:
                curarPokemonEspecifico();
                break;

            case 3:
                System.out.println("Cancelando...");
                return;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void curarPokemonEspecifico() {
        if (lista.isEmpty()) {
            System.out.println("Você não tem pokemons para curar.");
            return;
        }

        System.out.println("Escolha o número do pokemon que deseja curar:");
        System.out.println(listar());

        try {
            int index = Scan.lerInt();
            Pokemon p = lista.get(index - 1);
            p.curar();
            System.out.println(p.getNome() + " foi curado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Escolha um número válido.");
        }
    }

    public void mostrarPokemons() {
        if (lista.isEmpty()) {
            System.out.println("Você não tem pokemons.");
            return;
        }

        System.out.println("Seus pokemons:");
        for (Pokemon p : lista) {
            System.out.println(p.toString2());
        }
    }

    @Override
    public String toString() {
        if (lista.isEmpty()) {
            return "Treinador: " + nome +
                    "\nPokemons: " + "[Você não tem pokemons]" + "\nQuantidade de Pokemons: " + pokebolas;
        }

        return "Treinador: " + nome +
                "\nQuantidade de Pokemons: " + lista.size() + "\nQuantidade de Pokebolas: " + pokebolas;
    }
}