package ba.unsa.etf.rpr;

public class NumPrint implements Runnable {
    private int i, max;

    public NumPrint(int i, int max) {
        this.i = i;
        this.max = max;
    }

    @Override
    public void run() {
        Runnable r = new CharPrint('P', 20000);
        Thread t = new Thread(r);
        t.start();
        try {
            for (int j = 0; j < max; ++j) {
                System.out.println(j + " ");
                if (j == 200) t.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
