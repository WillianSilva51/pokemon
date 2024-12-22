package pokebolas;

import pokemon.Pokemon;

public class Masterball extends Pokebola {
    public Masterball() {
        nome = "Masterball";
        taxaBase = 1.0f;
    }

    @Override
    public boolean capturar(Pokemon p) {
        return true;
    }
}
