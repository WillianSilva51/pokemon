package treinador.pokedex.pokedexManeger;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import treinador.pokedex.Pokedex;

public class PokedexManeger {

    public static void salvarPokedex(Pokedex pokedex) {
        // Salva a pokedex em um arquivo
        try (FileOutputStream file = new FileOutputStream("pokedexSave.json");
                ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(pokedex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Pokedex carregarPokedex() {

        return null;
    }
}
