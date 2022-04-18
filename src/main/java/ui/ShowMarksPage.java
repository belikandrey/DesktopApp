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
import java.util.Arrays;

public class ShowMarksPage extends JPanel {
    private final JComboBox pupils;
    private final JComboBox subjects;
    private final JButton show;
    private JButton mainPage;

    private JTextArea marks;

    public ShowMarksPage(){
        setLayout(null);
        String[] pupArray = new String[Information.INFORMATION.getPupilList().size()];
        for(int i = 0 ; i < pupArray.length; i++){
            pupArray[i] = Information.INFORMATION.getPupilList().get(i).getName();
        }

        String [] subjArray = new String[Information.INFORMATION.getSubjects().size()];
        for(int i = 0 ; i < subjArray.length; i++){
            subjArray[i] = Information.INFORMATION.getSubjects().get(i);
        }

        pupils = new JComboBox(pupArray);
        pupils.setSize(250,75);
        pupils.setLocation(100,100);

        subjects = new JComboBox(subjArray);
        subjects.setSize(250, 75);
        subjects.setLocation(100,200);



        show = new JButton("Show!");
        show.setSize(150,50);
        show.setLocation(450,150);

        add(subjects);
        add(show);

        add(pupils);

        show.addActionListener(new ButtonListener());
    }


    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==mainPage){
                JPanel panel = new MainPage();
                MainWindow mainWindow = MainWindow.MAIN_WINDOW;
                mainWindow.getPanel().removeAll();
                mainWindow.getPanel().updateUI();
                mainWindow.setPanel(panel);
                mainWindow.setContentPane(panel);
            }else if(e.getSource()==show){
                Pupil byName = Information.INFORMATION.findByName((String) pupils.getSelectedItem());
                java.util.List<Integer> marksList =null;
                switch ((String)subjects.getSelectedItem()){
                    case "Math":
                            marksList = byName.getMarks().getMarksList().get(0);
                        break;
                    case "Informatics" :
                            marksList = byName.getMarks().getMarksList().get(1);
                        break;
                    case "Biology" :
                        marksList = byName.getMarks().getMarksList().get(2);
                        break;
                    case "Physics" :
                        marksList = byName.getMarks().getMarksList().get(3);
                        break;
                    case "English" :
                        marksList = byName.getMarks().getMarksList().get(4);
                }
                String s = Arrays.toString(marksList.toArray()).substring(1);
                marks = new JTextArea("Marks : "+s.substring(0,s.length()-1));
                marks.setFont(new Font("Arial", Font.ITALIC, 45));
                marks.setSize(600,100);
                marks.setLocation(175,350);
                mainPage = new JButton("Main page");
                mainPage.setSize(200,100);
                mainPage.setLocation(750,560);

                removeAll();
                add(marks);
                add(mainPage);
                mainPage.addActionListener(new ButtonListener());
                updateUI();
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
        graphics2D.drawString("Marks of pupil! ", 350,35);
    }
}
