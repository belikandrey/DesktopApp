import ui.Information;
import ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        Information information = Information.INFORMATION;
        information.initialization();
        MainWindow mainWindow = MainWindow.MAIN_WINDOW;
        mainWindow.setVisible(true);
    }
}
