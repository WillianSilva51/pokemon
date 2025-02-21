package pokebolas.afetaPokemon;

import pokebolas.Pokebola;
import pokemon.Pokemon;

public abstract class PokebolaP extends Pokebola {

    @Override
    public boolean capturar(Pokemon p) {
        afetaPokemon(p);
        return super.capturar(p);
    }

    abstract protected void afetaPokemon(Pokemon p);
}
