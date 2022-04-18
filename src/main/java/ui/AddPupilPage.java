package ui;

import app_logic.Marks;
import app_logic.Pupil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;


public class AddPupilPage extends JPanel {
    private final JTextArea name;
    private final JButton add;
    private final JTextArea marksPattern;
    private final JTextArea marksForMath;
    private final JTextArea marksForInformatics;
    private final JTextArea marksForBiology;
    private final JTextArea marksForPhysics;
    private final JTextArea marksForEnglish;


    public AddPupilPage(){
        setLayout(null);
        name = new JTextArea("Enter name of pupil");
        name.setSize(150,50);
        name.setLocation(75,75);

        add = new JButton("Add pupil");
        add.setSize(150,75);
        add.setLocation(800,550);

        marksPattern = new JTextArea("7,8,5,7,9");
        marksPattern.setFont(new Font("Arial", Font.ITALIC, 25));
        marksPattern.setSize(250,50);
        marksPattern.setLocation(75, 150);
        marksPattern.setEnabled(false);

        marksForMath = new JTextArea("Enter marks for Math");
        marksForMath.setSize(250, 50);
        marksForMath.setLocation(75, 225);

        marksForInformatics = new JTextArea("Enter marks for Informatics");
        marksForInformatics.setSize(250,50);
        marksForInformatics.setLocation(75, 300);

        marksForBiology = new JTextArea("Enter marks for Biology");
        marksForBiology.setSize(250, 50);
        marksForBiology.setLocation(75, 375);

        marksForPhysics = new JTextArea("Enter marks for Physics");
        marksForPhysics.setSize(250,50);
        marksForPhysics.setLocation(75, 450);

        marksForEnglish = new JTextArea("Enter marks for English");
        marksForEnglish.setSize(250,50);
        marksForEnglish.setLocation(75,525);

        add(marksForEnglish);
        add(marksForPhysics);
        add(marksForBiology);
        add(marksForInformatics);
        add(name);
        add(add);
        add(marksPattern);
        add(marksForMath);

        marksForPhysics.addMouseListener(new TextListener());
        marksForBiology.addMouseListener(new TextListener());
        marksForInformatics.addMouseListener(new TextListener());
        name.addMouseListener(new TextListener());
        marksForMath.addMouseListener(new TextListener());
        marksForEnglish.addMouseListener(new TextListener());

        add.addActionListener(new ButtonListener());

    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==add){
                Marks marks = new Marks();
                List<List<Integer>> marksList = marks.getMarksList();
                for(String temp : marksForMath.getText().split(",")){
                    marksList.get(0).add(Integer.parseInt(temp));
                }
                for(String temp : marksForInformatics.getText().split(",")){
                    marksList.get(1).add(Integer.parseInt(temp));
                }
                for(String temp : marksForBiology.getText().split(",")){
                    marksList.get(2).add(Integer.parseInt(temp));
                }
                for(String temp : marksForPhysics.getText().split(",")){
                    marksList.get(3).add(Integer.parseInt(temp));
                }
                for(String temp : marksForEnglish.getText().split(",")){
                    marksList.get(4).add(Integer.parseInt(temp));
                }

                Pupil pupil = new Pupil(name.getText(), marks);

                Information.INFORMATION.getPupilList().add(pupil);

                try {
                    FileWriter writer = new FileWriter(new File("input"+ File.separator+"pupils.txt"), true);
                    StringBuilder builder = new StringBuilder(pupil.getName()+";");
                    for(List<Integer> tempList : pupil.getMarks().getMarksList()){
                        for(int i = 0 ; i < tempList.size(); i++){
                            builder.append(tempList.get(i));
                            if(i==tempList.size()-1){
                                continue;
                            }
                            builder.append(", ");
                        }
                        builder.append(";");
                    }
                    writer.write("\n"+builder.toString());
                    writer.close();
                } catch (IOException ioException) {
                    System.err.println(ioException.toString());
                }

                JPanel panel = new MainPage();
                MainWindow mainWindow = MainWindow.MAIN_WINDOW;
                mainWindow.getPanel().removeAll();
                mainWindow.getPanel().updateUI();
                mainWindow.setPanel(panel);
                mainWindow.setContentPane(panel);
            }
        }
    }

    class TextListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        public void mousePressed(MouseEvent mouseEvent) {

        }

        public void mouseReleased(MouseEvent mouseEvent) {

        }

        //Мышь на нашем поле
        public void mouseEntered(MouseEvent mouseEvent) {
            if(mouseEvent.getSource()==name
                    && name.getText().equals("Enter name of pupil")){
                name.setText("");
                name.setFont(new Font("Arial", Font.ITALIC, 15));
            }else if(mouseEvent.getSource()==marksForMath && marksForMath.getText().equals("Enter marks for Math")){
                marksForMath.setText("");
                marksForMath.setFont(new Font("Arial", Font.ITALIC, 15));
            }else if(mouseEvent.getSource()==marksForInformatics && marksForInformatics.getText().equals("Enter marks for Informatics")){
                marksForInformatics.setText("");
                marksForInformatics.setFont(new Font("Arial", Font.ITALIC, 15));
            }else if(mouseEvent.getSource() == marksForBiology && marksForBiology.getText().equals("Enter marks for Biology")){
                marksForBiology.setText("");
                marksForBiology.setFont(new Font("Arial", Font.ITALIC, 15));
            }else if(mouseEvent.getSource()==marksForPhysics && marksForPhysics.getText().equals("Enter marks for Physics")){
                marksForPhysics.setText("");
                marksForPhysics.setFont(new Font("Arial", Font.ITALIC, 15));
            }else if(mouseEvent.getSource()==marksForEnglish && marksForEnglish.getText().equals("Enter marks for English")){
                marksForEnglish.setText("");
                marksForEnglish.setFont(new Font("Arial", Font.ITALIC, 15));

            }
        }

        //Мышь не на нашем поле
        public void mouseExited(MouseEvent mouseEvent) {
            if(mouseEvent.getSource()==name){
                if(name.getText().equals(""))
                    name.setText("Enter name of pupil");
            }else if(mouseEvent.getSource()==marksForMath){
                if(marksForMath.getText().equals(""))
                    marksForMath.setText("Enter marks for Math");
            }else if(mouseEvent.getSource()==marksForInformatics){
                if(marksForInformatics.getText().equals(""))
                    marksForInformatics.setText("Enter marks for Informatics");
            }else if(mouseEvent.getSource() == marksForBiology){
                if(marksForBiology.getText().equals(""))
                    marksForBiology.setText("Enter marks for Biology");
            }else if(mouseEvent.getSource()==marksForPhysics){
                if(marksForPhysics.getText().equals("")){
                    marksForPhysics.setText("Enter marks for Physics");
                }
            }else if(mouseEvent.getSource()==marksForEnglish){
                if(marksForEnglish.getText().equals("")){
                    marksForEnglish.setText("Enter marks for English");
                }
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
            System.err.println(e.toString());
        }
        graphics2D.setColor(Color.WHITE);

        graphics2D.setFont(new Font("Arial", Font.ITALIC, 20));
        graphics2D.drawString("Pattern for input : ", 75,145);
        graphics2D.setFont(new Font("Arial", Font.ITALIC, 35));

        graphics2D.drawString("Enter some information about new pupil! :)", 250,35);
    }
}
