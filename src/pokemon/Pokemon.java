package pokemon;

import random.Rand;

public class Pokemon implements Comparable<Pokemon> {
    private String nome;
    private int especie;
    private int hpBase;
    private int hpAtual;
    private int hpMax;
    private int taxaDeCaptura;
    private double peso;
    private int ataqueBase;
    private int defesaBase;
    private int velocidade;
    private int experiencia;
    private int nivel;
    private int amizade;

    public Pokemon(int especie, String nome, int hpBase, int ataque, int defesa, int velocidade, int amizade,
            double peso, int taxaDeCaptura) {
        this.especie = especie;
        this.nome = nome;
        this.hpBase = hpBase;
        this.ataqueBase = ataque;
        this.defesaBase = defesa;
        this.velocidade = velocidade;
        this.amizade = amizade;
        this.peso = peso;
        this.taxaDeCaptura = taxaDeCaptura;
        this.nivel = 1;
        this.hpMax = calcularHP(nivel);
        this.hpAtual = hpMax;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getTaxaDeCaptura() {
        return taxaDeCaptura;
    }

    public void setTaxaCaptura(int taxa) {
        taxaDeCaptura = taxa;
    }

    public double getPeso() {
        return peso;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int setAmizade(int amizade) {
        return this.amizade = amizade;
    }

    private int calcularHP(int nivel) {
        return ((2 * hpBase * nivel) / 100) + nivel + 10;
    }

    public String falar() {
        return nome + " " + nome;
    }

    public void curar() {
        curar(Rand.aleatorio(1, hpMax));
    }

    public void curar(int quantidade) {
        if (hpAtual + quantidade > hpMax) {
            hpAtual = hpMax;
        } else {
            hpAtual += quantidade;
        }
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
        return this.hpAtual > 0;
    }

    public void ganharExperiencia(int pontos) {
        experiencia += pontos;
        if (experiencia >= 10 * nivel) {
            nivel++;
            experiencia = experiencia - 10 * nivel;

            if (experiencia < 0) {
                experiencia = 0;
            }

            System.out.println(nome + " subiu para o nível " + this.nivel + "!");
        }

        System.out.println("Experiência do " + nome + " agora é: " + experiencia);
    }

    public void receberDano(int dano) {
        hpAtual -= dano;

        if (!taVivo()) {
            hpAtual = 0;
            System.out.println(nome + " morreu!");
        }

        System.out.println(nome + " recebeu " + dano + " de dano e agora tem " + this.hpAtual + " de hpAtual.");
    }

    public void mostrarStatus() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" + "Especie: " + especie + "\n" + "Vida: " + hpAtual + "\n" + "Ataque: "
                + ataqueBase
                + "\n"
                + "Defesa: " + defesaBase + "\n" + "Velocidade: " + velocidade + "\n" + "Peso: " + peso + "\n"
                + "Experiência: " + experiencia + "\n" + "Amizade: " + amizade + "\n"
                + "Nível: " + nivel;
    }

    public String toString2() {
        return nome + " level: " + nivel;
    }

    @Override
    public int compareTo(Pokemon o) {
        return Integer.compare(especie, especie);
    }
}