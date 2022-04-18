package ui;

import app_logic.Pupil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RemovePupilPage extends JPanel {
    private final JComboBox comboBox;
    private final JButton remove;
    public RemovePupilPage(){
        setLayout(null);
        String[] pupArray = new String[Information.INFORMATION.getPupilList().size()];
        for(int i = 0 ; i < pupArray.length; i++){
            pupArray[i] = Information.INFORMATION.getPupilList().get(i).getName();
        }
        comboBox = new JComboBox(pupArray);
        comboBox.setSize(250,100);
        comboBox.setLocation(100,200);

        remove = new JButton("Remove!");
        remove.setSize(150,75);
        remove.setLocation(250,350);

        add(comboBox);
        add(remove);
        remove.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==remove){
                Pupil byName = Information.INFORMATION.findByName((String) comboBox.getSelectedItem());
                Information.INFORMATION.getPupilList().remove(byName);
                try {
                    FileWriter fileWriter = new FileWriter(new File("input"+ File.separator+"pupils.txt"), false);
                    int j = 0, size = Information.INFORMATION.getPupilList().size();
                    for(Pupil pupil : Information.INFORMATION.getPupilList()){
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
                        j++;
                        if(j==size){
                            fileWriter.write(builder.toString());
                        }else{
                            fileWriter.write(builder.toString()+"\n");
                        }
                    }
                    fileWriter.close();
                } catch (IOException ioException) {
                    System.out.println(ioException.toString());
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

        graphics2D.setFont(new Font("Arial", Font.ITALIC, 35));

        graphics2D.drawString("Choose people to remove!", 250,35);
    }
}
