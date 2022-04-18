package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPage extends JPanel {
    private final JButton addPupil;
    private final JButton removePupil;
    private final JButton showMarks;
    private final JButton addMark;
    private final JButton showAllPeopleWithBadMarks;
    private final JButton exit;

    public MainPage(){
        setLayout(null);

        addPupil = new JButton("Add pupil");
        addPupil.setSize(100,50);
        addPupil.setLocation(300,300);

        removePupil = new JButton("Remove pupil");
        removePupil.setSize(100,50);
        removePupil.setLocation(650,300);

        showMarks = new JButton("Show marks");
        showMarks.setSize(100,50);
        showMarks.setLocation(100,450);

        addMark = new JButton("Add mark");
        addMark.setSize(100,50);
        addMark.setLocation(850,450);

        showAllPeopleWithBadMarks = new JButton("Show all pupils with bad marks");
        showAllPeopleWithBadMarks.setSize(250, 75);
        showAllPeopleWithBadMarks.setLocation(400, 425);

        exit = new JButton("Exit");
        exit.setSize(100,50);
        exit.setLocation(900, 600);

        add(exit);
        add(addPupil);
        add(removePupil);
        add(showMarks);
        add(addMark);
        add(showAllPeopleWithBadMarks);

        exit.addActionListener(new ButtonListener());
        addPupil.addActionListener(new ButtonListener());
        removePupil.addActionListener(new ButtonListener());
        showMarks.addActionListener(new ButtonListener());
        addMark.addActionListener(new ButtonListener());
        showAllPeopleWithBadMarks.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==exit){
                System.exit(0);
            }
            else if(e.getSource()==addPupil){
                    setPage(new AddPupilPage());
            }else if(e.getSource()==removePupil){
                    setPage(new RemovePupilPage());
            }else if(e.getSource()==showMarks){
                    setPage(new ShowMarksPage());
            }else if(e.getSource()==addMark){
                    setPage(new AddMarksPage());
            }else if(e.getSource()==showAllPeopleWithBadMarks){
                    setPage(new ShowAllPeopleWithBadMarks());
            }
        }
        private void setPage(JPanel page){
            MainWindow mainWindow = MainWindow.MAIN_WINDOW;
            mainWindow.getPanel().removeAll();
            MainWindow.MAIN_WINDOW.getPanel().updateUI();
            mainWindow.setPanel(page);
            mainWindow.setContentPane(mainWindow.getPanel());
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        BufferedImage image ;
        try {
            image = ImageIO.read(new File("src"+ File.separator+"main"+
                    File.separator+"resources"+File.separator+"background.jpg"));
            graphics.drawImage(image, 0, 0 , this);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        graphics.setFont(new Font("Arial", Font.BOLD, 35));
        graphics.setColor(Color.WHITE);
        graphics.drawString("Hello!", 150,70);
        graphics.drawString("Enter necessary function!", 150, 150);
        graphics.drawString("Don't forget to put someone 2!", 430, 220);
    }
}
