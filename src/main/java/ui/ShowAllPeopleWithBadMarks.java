package ui;

import app_logic.Pupil;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShowAllPeopleWithBadMarks extends JPanel {
    private final JButton mainPage;

    public ShowAllPeopleWithBadMarks(){
        setLayout(null);
        mainPage = new JButton("Main page");
        mainPage.setSize(250,100);
        mainPage.setLocation(700, 550);
        add(mainPage);
        mainPage.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==mainPage){
                JPanel panel = new MainPage();
                MainWindow mainWindow = MainWindow.MAIN_WINDOW;
                mainWindow.getPanel().removeAll();
                mainWindow.getPanel().updateUI();
                mainWindow.setPanel(panel);
                mainWindow.setContentPane(panel);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        try {
            BufferedImage image = ImageIO.read(new File("src"+ File.separator+"main"+
                    File.separator+"resources"+File.separator+"background1.jpg"));
            graphics2D.drawImage(image, 0,0,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D.setColor(Color.WHITE);

        graphics2D.setFont(new Font("Arial", Font.ITALIC, 35));

        graphics2D.drawString("Pupils with bad marks(less than 4)", 250,35);

        java.util.List<Pupil> pupilWithBadMarks = new ArrayList<>();
        for(Pupil pupil : Information.INFORMATION.getPupilList()){
            for(java.util.List<Integer> marks : pupil.getMarks().getMarksList()){
                if(marks.contains(0) || marks.contains(1) || marks.contains(2) || marks.contains(3)){
                    pupilWithBadMarks.add(pupil);
                    break;
                }
            }
        }
        int deltaY = 50;
        graphics2D.setFont(new Font("Arial",Font.ITALIC, 35));
        if(pupilWithBadMarks.size()>5){
            graphics2D.setFont(new Font("Arial", Font.ITALIC, 20));
            deltaY = 25;
        }
        int x = 75, y = 100;
        for(Pupil pupil : pupilWithBadMarks){
            graphics2D.drawString("-"+pupil.getName(), x,y);
            y+=deltaY;
        }
    }
}
