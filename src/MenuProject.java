

import javax.swing.event.SwingPropertyChangeSupport;
import java.util.*;

public class MenuProject {



    public void menuPanel() {
        System.out.println("    ****************************************");
        System.out.println("    *                 MENU                 *");
        System.out.println("    ****************************************\n");
        System.out.println("    1. Informacja o programie");
        System.out.println("    2. Wczytaj plik kursu walut");
        System.out.println("    3. Wyświetl dostępne  waluty");
        System.out.println("    4. Wyświetl ekstrema globalne");
        System.out.println("    5. Wyświetl ekstrema lokalne");
        System.out.println("    6. Kalkulator walut");
        System.out.println("    0. Zakończ działanie aplikacji");

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
                        System.out.println("    ****************************************");
                        System.out.println("    *       INFORMACJE O PROGRAMIE         *");
                        System.out.println("    ****************************************\n");
                        System.out.println("Program obsługi walut ma za zadanie w łatwy sposób pomóc użytkownikowi w sprawdzeniu i przeliczeniu kursu walut. \n " +
                                "Program udostępnia następujące opcje: \n 1. Wczytanie pliku kursu walut \n " +
                                "Użytkownik zostaje poproszony o wskazanie lokalizacji, z której");
                    case 2:
                        System.out.println("Metoda umożliwia wczytanie pliku do konsoli");
                        break;
                    case 3:
                        System.out.println("Metoda umożliwia pokazanie dostępnych walut w wyznaczonym zakresie czasowym oraz z danego dnia");
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


