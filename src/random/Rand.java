package random;

import java.util.random.RandomGenerator;

public class Rand {
    private Rand() {
        throw new IllegalStateException("Utility class");
    }

    private static final RandomGenerator numero = RandomGenerator.getDefault();

    public static int aleatorio(int inicio, int fim) {
        return numero.nextInt(inicio, fim);
    }

    public static double aleatorioD(double inicio, double fim) {
        return numero.nextDouble(inicio, fim);
    }

    public static boolean aleatorioB() {
        return numero.nextBoolean();
    }
}