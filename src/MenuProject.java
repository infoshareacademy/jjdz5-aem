

import javax.swing.event.SwingPropertyChangeSupport;
import java.util.*;

public class MenuProject {

MenuInformation menuInformation=new MenuInformation();

    public void menuPanel() {
        menuInformation.readMenu();
        pick();

    }
        public void pick () {


            System.out.println("Wybierz interesującą Cię metodę");
            int choose=0;

            do{
                Scanner scanner = new Scanner(System.in);
                choose=scanner.nextInt();

                switch (choose) {
                    case 1:
                     menuInformation.programInformation();
                     break;
                    case 2:
                        System.out.println("Metoda umożliwia wczytanie pliku do konsoli");
                        break;
                    case 3:
                        menuInformation.currentCurrency();
                        break;
                    case 4:
                        System.out.println("Metoda pokazuje globalną wartość min oraz max walut");
                        break;
                    case 5:
                        System.out.println("Metoda pokazuje lokalną wartość min oraz max  walut na podstawie wybranego zakresu czasowego");
                        break;
                    case 6:
                        System.out.println("Metoda umożliwia przeliczenia wskazanej waluty");
                        break;
                    case 7:
                        menuPanel();
                        break;
                    case 0:
                        System.out.println("Dziękujemy za skorzystanie z programu\n");
                        break;
                     default:
                         System.out.println("MenuProject nie posiada numeru " + choose + ". Podaj ponownie numer metody, którą chcesz wywołać");
                         break;
                }
            } while(choose!=0);

            System.out.println("     ****************************************");
            System.out.println("     Koniec programu\n\n");
        }

}


