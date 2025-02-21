package treinador.pokedex;

import app.scanner.Scan;
import pokemon.EspeciePokemon;
import pokemon.Pokemon;

public class MenuPokedex {
    Pokedex pokedex;

    public MenuPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    private boolean temPokemon() {
        return !pokedex.listarCapturados().isEmpty();
    }

    public String listarCapturados() {
        if (!temPokemon()) {
            return "Você não tem pokemons";
        }

        StringBuilder sb = new StringBuilder();

        for (Pokemon p : pokedex.listarCapturados()) {
            sb.append("----------------------\n").append(pokedex.listarCapturados().indexOf(p) + 1).append(": ")
                    .append(p.toString2())
                    .append("\n");
        }

        return sb.toString();
    }

    public void listarVistos() {
        StringBuilder sb = new StringBuilder();

        for (EspeciePokemon p : pokedex.listarVistos()) {
            sb.append("----------------------\n").append(p.getNome()).append(": ")
                    .append(contarVistosPorEspecie(p))
                    .append("\n");
        }

        System.out.println(sb.toString());
    }

    public void quantidadeDePokemonsCapturados() {
        System.out.println(pokedex.quantidadeDePokemonsCapturados());
    }

    public void quantidadeDePokemonsVistos() {
        System.out.println(pokedex.quantidadeDePokemonsVistos());
    }

    public long contarCapturasPorEspecie(String nome) {
        return pokedex.contarCapturasPorEspecie(nome);
    }

    public long contarVistosPorEspecie(EspeciePokemon especie) {
        return pokedex.contarVistosPorEspecie(especie);
    }

    public void verTaxaDeCaptura(String nome) {
        System.out.println(pokedex.taxaDeSucesso(nome));
    }

    public void verDetalhesPokemon(int index) {
        System.out.println(pokedex.listarCapturados().get(index - 1).toString());
    }

    public void trocarPokemon(Pokemon p) {
        removerPokemon();

        pokedex.listarCapturados().add(p);
    }

    public void removerPokemon() {
        if (!temPokemon()) {
            System.out.println("Você não tem pokemons para remover ou trocar.");
            return;
        }

        while (true) {
            try {
                System.out.println(
                        "Você tem certeza que deseja remover ou trocar algum dos seus pokemons? (Após isso não poderá mais usá-lo) [S] ou [N]");
                String choice = Scan.lerString();

                if (choice.equalsIgnoreCase("s")) {
                    System.out.println(listar());
                    System.out.println("Escolha o número do pokemon que deseja remover:");
                    pokedex.removerPokemon(null); // AJEITAR
                    System.out.println("Pokemon removido");
                    return;
                }

                else {
                    System.out.println("Pokemon não removido ou trocado.");
                    return;
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Escolha um número válido.");
            }
        }
    }

    public String listar() {
        if (!temPokemon()) {
            return "Você não tem pokemons";
        }

        StringBuilder sb = new StringBuilder();

        for (Pokemon p : pokedex.listarCapturados()) {
            sb.append("----------------------\n").append(pokedex.listarCapturados().indexOf(p) + 1).append(": ")
                    .append(p.toString2())
                    .append("\n");
        }

        return sb.toString();
    }

    public String listar(int index) {
        if (!temPokemon()) {
            return "Você não tem pokemons";
        }

        try {
            return pokedex.listarCapturados().get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Escolha um número válido.";
        }
    }

    public void curarPokemons() {
        if (!temPokemon()) {
            System.out.println("Você não tem pokemons para curar.");
            return;
        }

        System.out.println("Escolha uma opção [1] curar todos, [2] curar um pokemon específico ou [3] cancelar");
        int choice = Scan.lerInt();

        switch (choice) {
            case 1:
                System.out.println("Curando seus pokemons...");

                for (Pokemon p : pokedex.listarCapturados()) {
                    p.curar();
                }

                System.out.println("Todos os seus pokemons foram curados.");
                break;

            case 2:
                curarPokemonEspecifico();
                break;

            case 3:
                System.out.println("Cancelando...");
                return;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void curarPokemonEspecifico() {
        if (!temPokemon()) {
            System.out.println("Você não tem pokemons para curar.");
            return;
        }

        System.out.print("\n" + listar());
        System.out.println("Escolha o número do pokemon que deseja curar:");

        try {
            int index = Scan.lerInt();
            Pokemon p = pokedex.listarCapturados().get(index - 1);
            p.curar();
            System.out.println(p.getNome() + " foi curado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Escolha um número válido.");
        }
    }

    public void mostrarPokemons() {
        if (!temPokemon()) {
            System.out.println("Você não tem pokemons.");
            return;
        }

        System.out.println("Seus pokemons:");
        for (Pokemon p : pokedex.listarCapturados()) {
            System.out.println(p.toString2());
        }
    }
}
