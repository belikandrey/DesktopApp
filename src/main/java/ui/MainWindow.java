package ui;

import javax.swing.*;


public class MainWindow extends JFrame {
    private JPanel panel;

    public static final MainWindow MAIN_WINDOW = new MainWindow();


    private MainWindow(){
        super("High school student journal");
        setLayout(null);
        setSize(1080,700);
        setLocation(145,100);
        panel = new MainPage();
        setResizable(false);
        setContentPane(panel);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
