package pokebolas.afetaCaptura;

import pokemon.Pokemon;

public class FastBall extends PokebolaC {
    public FastBall() {
        nome = "FastBall";
        taxaBase = 1f;
    }

    @Override
    protected void afetaCaptura(Pokemon pokemon) {
        if (pokemon.getVelocidade() >= 100) {
            taxaBase = 4f;
        }
    }

}
