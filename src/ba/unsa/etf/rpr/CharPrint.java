package ba.unsa.etf.rpr;

public class CharPrint implements Runnable {
    private char c;
    private int max;

    public CharPrint(char c, int max) {
        this.c = c;
        this.max = max;
    }


    @Override
    public void run() {
        for (int i = 0; i < max; ++i) {
            System.out.println(c + " ");
        }
    }
}
