package treinador.pokedex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pokemon.EspeciePokemon;
import pokemon.Pokemon;
import pokemon.registravel.Registravel;

public class Pokedex implements Registravel {
    private List<Pokemon> pokemonsCaturados;
    private Set<EspeciePokemon> pokemonsVistos;
    private HashMap<EspeciePokemon, Integer> quantidadeVistos;

    public Pokedex() {
        pokemonsCaturados = new ArrayList<>();
        pokemonsVistos = new HashSet<>();
        quantidadeVistos = new HashMap<>();
    }

    @Override
    public void registrarEncontro(Pokemon pokemon) {
        EspeciePokemon especie = EspeciePokemon.valueOf(pokemon.getNome().toUpperCase());

        int c = quantidadeVistos.getOrDefault(pokemon, 0);
        quantidadeVistos.put(especie, ++c);

        pokemonsVistos.add(especie);
    }

    @Override
    public void registrarCaptura(Pokemon pokemon) {
        pokemonsCaturados.add(pokemon);
        pokemonsCaturados.sort(Pokemon::compareTo);
    }

    public void removerPokemon(Pokemon pokemon) {
        pokemonsCaturados.remove(pokemon);
    }

    public List<Pokemon> listarCapturados() {
        return pokemonsCaturados;
    }

    public List<EspeciePokemon> listarVistos() {
        return List.copyOf(pokemonsVistos.stream().toList());
    }

    int quantidadeDePokemonsVistos() {
        return pokemonsVistos.size();
    }

    int quantidadeDePokemonsCapturados() {
        return pokemonsCaturados.size();
    }

    long contarCapturasPorEspecie(String nome) {
        return pokemonsCaturados.stream().filter(pokemon -> pokemon.getNome().equals(nome)).count();
    }

    long contarVistosPorEspecie(EspeciePokemon especie) {
        return pokemonsVistos.stream().filter(pokemon -> pokemon.equals(especie)).count();
    }

    double taxaDeSucesso(String nome) {
        return (double) contarCapturasPorEspecie(nome)
                / contarVistosPorEspecie(EspeciePokemon.valueOf(nome.toUpperCase()));
    }
}