package pokebolas.afetaCaptura;

import pokemon.Pokemon;

public class Masterball extends PokebolaC {
    public Masterball() {
        nome = "Masterball";
        taxaBase = 1.0f;
    }

    @Override
    public boolean capturar(Pokemon pokemon) {
        afetaCaptura(pokemon);
        return true;
    }

    @Override
    protected void afetaCaptura(Pokemon pokemon) {
        System.out.println("Efeito da Masterball: Captura imediata");
    }
}
