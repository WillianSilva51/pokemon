package pokebolas.afetaCaptura;

import pokebolas.Pokebola;
import pokemon.Pokemon;

public class HeavyBall extends Pokebola {
    public HeavyBall() {
        nome = "HeavyBall";
        taxaBase = 1;
    }

    @Override
    public float getTaxaBase(Pokemon p) {
        double peso = p.getPeso();
        float valor = 0;

        if (peso <= 99.9f) {
            valor -= 20;
        } else if (peso >= 200 && peso <= 299.99f) {
            valor += 20;
        } else {
            valor += 30;
        }

        return super.getTaxaBase(p) + valor;
    }
}
