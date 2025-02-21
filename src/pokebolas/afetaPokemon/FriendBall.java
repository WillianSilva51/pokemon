package pokebolas.afetaPokemon;

import pokemon.Pokemon;

public class FriendBall extends PokebolaP {
    public FriendBall() {
        nome = "FriendBall";
        taxaBase = 1f;
    }

    @Override
    protected void afetaPokemon(Pokemon p) {
        p.setAmizade(200);
        System.out.println("O pokemon se sente mais confortável com você");
    }

}
