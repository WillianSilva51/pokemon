package treinador;

import java.util.ArrayList;
import java.util.Collections;

import app.audio.Audios;
import app.clearS.Clear;
import app.scanner.Scan;
import pokebolas.*;
import pokebolas.afetaPokemon.*;
import pokebolas.afetaCaptura.*;
import pokemon.Pokemon;
import treinador.pokedex.Pokedex;
import pokemon.EspeciePokemon;

public class Treinador {
    private String nome;
    private int x, y;
    private ArrayList<Pokebola> pokebolas = new ArrayList<>(Collections.nCopies(3, new Pokebola()));
    private Pokedex pokedex = new Pokedex();

    public Treinador() {
        x = 1;
        y = 1;

        nome = "Ash";
    }

    public Treinador(String nome, Pokedex pokedex) {
        pokebolas.add(new Masterball());
        pokebolas.add(new GreatBall());
        pokebolas.add(new Ultraball());
        pokebolas.add(new HealBall());
        pokebolas.add(new FriendBall());
        pokebolas.add(new HeavyBall());
        pokebolas.add(new FastBall());
        pokebolas.sort(Pokebola::compareTo);

        x = 1;
        y = 1;

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

        EspeciePokemon base = EspeciePokemon.BULBASAUR;
        Pokemon bulbasaur = new Pokemon(base.ordinal(), base.getNome(), base.getHpBase(),
                base.getAtaqueBase(),
                base.getDefesaBase(),
                base.getVelocidadeBase(), base.getAmizadeBase(), base.getPeso(), base.getTaxaDeCaptura());

        EspeciePokemon base2 = EspeciePokemon.CHARMANDER;
        Pokemon charmander = new Pokemon(base2.ordinal(), base2.getNome(), base2.getHpBase(),
                base2.getAtaqueBase(),
                base2.getDefesaBase(),
                base2.getVelocidadeBase(), base2.getAmizadeBase(), base2.getPeso(), base2.getTaxaDeCaptura());

        EspeciePokemon base3 = EspeciePokemon.SQUIRTLE;
        Pokemon squirtle = new Pokemon(base3.ordinal(), base3.getNome(), base3.getHpBase(),
                base3.getAtaqueBase(),
                base3.getDefesaBase(),
                base3.getVelocidadeBase(), base3.getAmizadeBase(), base3.getPeso(), base3.getTaxaDeCaptura());

        pokedex.registrarEncontro(bulbasaur);
        pokedex.registrarEncontro(charmander);
        pokedex.registrarEncontro(squirtle);

        while (true) {
            String choice = Scan.lerString();

            switch (choice) {
                case "1":
                    pokedex.registrarCaptura(bulbasaur);
                    return;

                case "2":
                    pokedex.registrarCaptura(charmander);
                    return;

                case "3":
                    pokedex.registrarCaptura(squirtle);
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

    public Pokedex getPokedex() {
        return pokedex;
    }

    private boolean temPokemon() {
        return !pokedex.listarCapturados().isEmpty();
    }

    public boolean temPokebola() {
        return pokebolas.isEmpty();
    }

    public void mostrarPokedex() {
        System.out.println("Pokedex:" + pokedex.toString());
    }

    public Pokebola arremessarPokebola() {
        if (temPokebola()) {
            System.out.println("Você não tem pokebolas.");
            return null;
        }

        while (true) {
            System.out.println("Você tem as seguintes pokebolas:");
            System.out.println(listarPokebolas());

            System.out.println("Escolha uma pokebola para arremessar:");
            int choice = Scan.lerInt();

            try {
                return pokebolas.get(choice - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Escolha um número válido.");
                Clear.ClearScreen();
            }
        }
    }

    public boolean capturar(Pokemon p, Pokebola pokebola) {
        pokebolas.remove(pokebola);

        if (pokebola.capturar(p)) {
            Audios.pararMusica();
            Audios.iniciarMusica("src/app/audio/soundEffects/capturado.wav");
            System.out.println("Pokemon capturado!");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Audios.iniciarMusica("src/app/audio/music/trainer.wav");

            pokedex.registrarCaptura(p);
            pokedex.registrarEncontro(p);

            return true;
        }

        else {
            System.out.println("Não conseguiu capturar");
        }

        return false;
    }

    @Override
    public String toString() {
        if (!temPokemon()) {
            return "Treinador: " + nome +
                    "\nPokemons: " + "[Você não tem pokemons]" + "\nQuantidade de Pokemons: " + pokebolas.size();
        }

        return "Treinador: " + nome +
                "\nQuantidade de Pokemons: " + pokedex.listarCapturados().size() + "\nQuantidade de Pokebolas: "
                + pokebolas.size();
    }

    private String listarPokebolas() {
        StringBuilder sb = new StringBuilder();

        for (Pokebola p : pokebolas) {
            sb.append(pokebolas.indexOf(p) + 1).append(": ").append(p.toString()).append("\n");
        }

        return sb.toString();
    }
}