package pokebolas.afetaCaptura;

import pokebolas.Pokebola;
import pokemon.Pokemon;

public abstract class PokebolaC extends Pokebola {

    @Override
    public boolean capturar(Pokemon p) {
        afetaCaptura(p);
        return super.capturar(p);
    }

    abstract protected void afetaCaptura(Pokemon pokemon);
}