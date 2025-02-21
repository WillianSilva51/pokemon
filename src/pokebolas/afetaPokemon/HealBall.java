package pokebolas.afetaPokemon;

import pokemon.Pokemon;

public class HealBall extends PokebolaP {
    public HealBall() {
        nome = "HealBall";
        taxaBase = 1f;
    }

    @Override
    protected void afetaPokemon(Pokemon p) {
        p.curar(p.getHpMax());
        System.out.println("O pokemon se sente mais saudável");
    }

}
