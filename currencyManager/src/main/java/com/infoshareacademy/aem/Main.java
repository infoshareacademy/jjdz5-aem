import com.infoshareacademy.aem.FileContentReader;
import com.infoshareacademy.aem.MenuProject;

public class Main {
    public static void main(String[] args) {

        FileContentReader fileContentReader = new FileContentReader();
        MenuProject menu=new MenuProject();

        fileContentReader.readFile();
        menu.menuPanel();
        
    }
}
