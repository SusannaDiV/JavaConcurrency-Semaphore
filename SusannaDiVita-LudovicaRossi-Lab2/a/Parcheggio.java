
public class Parcheggio {

    private volatile int postiLiberi;
    private volatile boolean aperto;

    public Parcheggio(int posti) {
        this.postiLiberi = posti;
        this.aperto = true;
    }

    public synchronized int postiLiberi() {
        return postiLiberi;
    }

    public synchronized boolean eAperto() {
        return aperto;
    }
    
    public synchronized void entrata() {
        while (eAperto() && postiLiberi() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        if (!eAperto()) {
            System.out.println("\tE' chiuso, non posso entrare");
            return;
        }
        --postiLiberi;
        notifyAll();
    }

  
    public synchronized void uscita() {
        ++postiLiberi;
        notifyAll();
    }

    public synchronized void chiusura() {
        aperto = false; 
        notifyAll();
    }
} 