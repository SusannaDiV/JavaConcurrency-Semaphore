public class Clienti extends Thread {
    Parcheggio parcheggio;

    public Clienti(Parcheggio p) {
        this.parcheggio = p;
    }

    @Override
    public void run() {
        int cont = 0;
        while (parcheggio.eAperto() && cont<100000) {
            parcheggio.entrata(); 
            if (!parcheggio.eAperto())
                break;
            try {
                System.out.print(getName()+": è entrato\n");                   
                sleep(2);
            } catch(InterruptedException e) {e.printStackTrace();}
            System.out.println(getName()+": è uscito");
            parcheggio.uscita();
            ++cont;
        }
        System.out.println(getName() + " ha finito correttamente");
    }
}