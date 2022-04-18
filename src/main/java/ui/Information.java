package ui;

import app_logic.Marks;
import app_logic.Pupil;

import java.io.*;
import java.util.*;

public class Information {
    private List<Pupil> pupilList;
    private List<String> subjects;

    public static final Information INFORMATION = new Information();

    private Information(){}

    public void initialization(){
        try {
            subjects = new ArrayList<>();
            subjects.add("Math");
            subjects.add("Informatics");
            subjects.add("Biology");
            subjects.add("Physics");
            subjects.add("English");
            Scanner scanner = new Scanner(new FileInputStream(new File("input"+ File.separator+"pupils.txt")));
            pupilList = new ArrayList<>();
            if(!scanner.hasNextLine()){
                scanner.close();
                return;
            }
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                if(s.equals("")){
                    continue;
                }
                String[] values = s.split(";");

                Marks marks = new Marks();
                for(int i = 0 ; i < marks.getMarksList().size(); i++){
                    String[] mark = values[i+1].split(", ");
                    for (String value : mark) {
                        marks.getMarksList().get(i).add(Integer.parseInt(value));
                    }
                }
                pupilList.add(new Pupil(values[0], marks));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    public List<Pupil> getPupilList() {
        return pupilList;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public Pupil findByName(String name){
        Pupil pupil = null;
        for(Pupil temp : pupilList){
            if(temp.getName().equals(name)){
                pupil = temp;
                break;
            }
        }
        return pupil;
    }

    public void reinitialization(){
        try {
            FileWriter writer = new FileWriter(new File("input"+ File.separator+"pupils.txt"), false);
            for(Pupil pupil : pupilList) {
                StringBuilder builder = new StringBuilder(pupil.getName() + ";");
                for (List<Integer> tempList : pupil.getMarks().getMarksList()) {
                    for (int i = 0; i < tempList.size(); i++) {
                        builder.append(tempList.get(i));
                        if (i == tempList.size() - 1) {
                            continue;
                        }
                        builder.append(", ");
                    }
                    builder.append(";");
                }
                writer.write("\n" + builder.toString());
            }
            writer.close();
        } catch (IOException ioException) {
            System.err.println(ioException.toString());
        }
    }
}
