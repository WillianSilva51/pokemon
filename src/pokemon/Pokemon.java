package pokemon;

import random.Rand;

public class Pokemon {
    private String nome;
    private String tipo;
    private int vida;
    private int atk;
    private int def;
    private int velocidade;
    private int experiencia;
    private int nivel;

    public Pokemon(String nome, String tipo, int vida, int atk, int def, int velocidade, int experiencia, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.atk = atk;
        this.def = def;
        this.velocidade = velocidade;
        this.experiencia = experiencia;
        this.nivel = nivel;
    }

    public Pokemon(String nome, int nivel, String tipo) {
        this(nome, tipo, Rand.aleatorio(100, 150), Rand.aleatorio(20, 25), Rand.aleatorio(25, 35),
                Rand.aleatorio(20, 30), Rand.aleatorio(0, 10), nivel);
    }

    public Pokemon() {
        this("pikachu", "eletrico", Rand.aleatorio(100, 150), 24, 16, 27, 2, 4);
    }

    public String falar() {
        return nome + " " + nome;
    }

    public void curar() {
        vida += Rand.aleatorio(1, 100);
    }

    public void atacar(int movimento, Pokemon alvo) {
        if (taVivo()) {
            // ataque!
            System.out.println("Atacando o " + alvo + " com ataque" + movimento);
        } else {
            System.out.println("tô morto :/");
        }
    }

    boolean taVivo() {
        return this.vida > 0;
    }

    public void ganharExperiencia(int pontos) {
        this.experiencia += pontos;
        if (this.experiencia >= 10 * nivel) {
            this.nivel++;
            this.experiencia = this.experiencia - 10 * nivel;

            if (this.experiencia < 0) {
                this.experiencia = 0;
            }

            System.out.println(nome + " subiu para o nível " + this.nivel + "!");
        }

        System.out.println("Experiência do " + nome + " agora é: " + experiencia);
    }

    public void receberDano(int dano) {
        this.vida -= dano;

        if (!taVivo()) {
            this.vida = 0;
            System.out.println(nome + " morreu!");
        }

        System.out.println(nome + " recebeu " + dano + " de dano e agora tem " + this.vida + " de vida.");
    }

    public void mostrarStatus() {
        System.out.println(this.toString());
    }

    public void mostrarPokemon() {
        System.out.println("`;-.          ___,        \r\n" + //
                "  `.`\\_...._/`.-\"`        \r\n" + //
                "    \\        /      ,     \r\n" + //
                "    /()   () \\    .' `-._ \r\n" + //
                "   |)  .    ()\\  /   _.'  \r\n" + //
                "   \\  -'-     ,; '. &lt;     \r\n" + //
                "    ;.__     ,;|   &gt; \\    \r\n" + //
                "   / ,    / ,  |.-'.-'    \r\n" + //
                "  (_/    (_/ ,;|.&lt;`       \r\n" + //
                "    \\    ,     ;-`        \r\n" + //
                "     &gt;   \\    /           \r\n" + //
                "    (_,-'`&gt; .'            \r\n" + //
                "          (_,'             ");
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" + "Tipo: " + tipo + "\n" + "Vida: " + vida + "\n" + "Ataque: " + atk + "\n"
                + "Defesa: " + def + "\n" + "Velocidade: " + velocidade + "\n" + "Experiência: " + experiencia + "\n"
                + "Nível: " + nivel;
    }

    public String toString2() {
        return nome + " level: " + nivel;
    }

}