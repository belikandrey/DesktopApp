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

public class AddMarksPage extends JPanel {
    private final JComboBox pupils;
    private final JComboBox subjects;
    private final JComboBox mark;

    private final JButton put;


    public AddMarksPage(){
        setLayout(null);
        String[] pupArray = new String[Information.INFORMATION.getPupilList().size()];
        for(int i = 0 ; i < pupArray.length; i++){
            pupArray[i] = Information.INFORMATION.getPupilList().get(i).getName();
        }

        String [] subjArray = new String[Information.INFORMATION.getSubjects().size()];
        for(int i = 0 ; i < subjArray.length; i++){
            subjArray[i] = Information.INFORMATION.getSubjects().get(i);
        }

        String[] marks = new String[11];
        for(int i = 0 ; i <= 10; i++){
            marks[i] = String.valueOf(i);
        }

        pupils = new JComboBox(pupArray);
        pupils.setSize(200,100);
        pupils.setLocation(150,150);

        add(pupils);

        subjects = new JComboBox(subjArray);
        subjects.setSize(200,100);
        subjects.setLocation(150,250);

        add(subjects);

        mark = new JComboBox(marks);
        mark.setSize(200,100);
        mark.setLocation(150,350);

        add(mark);

        put = new JButton("Put");
        put.setSize(200,50);
        put.setLocation(150,450);

        add(put);
        put.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == put){
                Pupil byName = Information.INFORMATION.findByName((String) pupils.getSelectedItem());

                int markInt = Integer.parseInt((String) mark.getSelectedItem());

                String subject =(String) subjects.getSelectedItem();

                switch (subject){
                    case "Math":
                        byName.getMarks().getMarksList().get(0).add(markInt);
                        break;
                    case "Informatics":
                        byName.getMarks().getMarksList().get(1).add(markInt);
                        break;
                    case "Biology" :
                        byName.getMarks().getMarksList().get(2).add(markInt);
                        break;
                    case "Physics" :
                        byName.getMarks().getMarksList().get(3).add(markInt);
                        break;
                    case "English" :
                        byName.getMarks().getMarksList().get(4).add(markInt);
                        break;
                }

                Information.INFORMATION.reinitialization();

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

        graphics2D.setFont(new Font("Arial", Font.ITALIC, 45));

        graphics2D.drawString("Don't forget to put 2!", 350,35);
    }
}
