import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static int counter = 0;
    static Timer timer;
 
    public static void main(String[] args) {
        Parcheggio P = new Parcheggio(20);
        Clienti Cl[] = new Clienti[50];
        for (int i = 0; i<50; ++i) {
            Cl[i] = new Clienti(P);
        } 
        System.out.print("IL PARCHEGGIO E' ORA APERTO\n");
        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() { 
                if(counter == 1){
                    P.chiusura();   
                    timer.cancel();
                    System.out.println("\nIL PARCHEGGIO E' ORA CHIUSO");
                }
                counter++;
            }
        };
        
        for (int i=0; i<50; ++i) { 
            Cl[i].start(); 
        }
        timer = new Timer("TempoDopoCuiIlParcheggioChiude");
        timer.scheduleAtFixedRate(timerTask, 200, 1);
    }
}