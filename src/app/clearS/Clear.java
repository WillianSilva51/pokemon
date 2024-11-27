package app.clearS;

public class Clear {

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ClearScreen() {
        try {
            Thread.sleep(100); // pausa de 0,1 segundo
            if (System.console().readLine("Pressione Enter para continuar...") == null) {
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clear();
    }
}
