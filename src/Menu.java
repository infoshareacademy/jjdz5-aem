

import javax.swing.event.SwingPropertyChangeSupport;
import java.util.*;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void menuPanel(){
        System.out.println("1. Pokaż dostępne waluty");

    }

    public void pick(){
        System.out.println("Wybierz interesującą Cię metodę");
        switch (scanner.nextInt()){
            case 1: Waluta();
            break;
        }
    }


    public void Waluta() {


        String[] tablica={"PLN", "WA", "EU"};
        Set<String> current=new HashSet<>();
        for(int i=0; i<tablica.length; i++) {
            current.add(tablica[i]);
        }

        Map<Integer,String> mapa=new HashMap<Integer, String>();
        int i=1;
        System.out.println("Program posiada poniższe waluty: ");
        for(String arg:current) {
            mapa.put(i,arg);
            System.out.println(i + "." + arg );
            i++;
        }


    }
}
