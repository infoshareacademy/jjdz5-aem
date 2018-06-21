import java.util.*;

public class Menu {

    public void Waluta() {

        Scanner scanner = new Scanner(System.in);
        String[] tablica=new String[3];
        Set<String> current=new HashSet<>();
        for(int i=0; i<tablica.length; i++) {
            current.add(scanner.nextLine());
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
