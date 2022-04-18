package app_logic;

import ui.Information;

import java.util.ArrayList;
import java.util.List;

public class Marks{
    private final List<List<Integer>> marks;
    // 0 - Math, 1 - Informatics, 2 - Biology, 3 - Physics, 4 - English
    public Marks(){
        marks = new ArrayList<>();
        for(int i = 0 ; i < 5; i ++){
            marks.add(new ArrayList<>());
        }
    }

    public List<List<Integer>> getMarksList() {
        return marks;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "marks for "+ Information.INFORMATION.getSubjects().get(0) +" = "+ marks.get(0) +"" +
                ", for "+Information.INFORMATION.getSubjects().get(1)+" = "+marks.get(1)+"" +
                ", for "+Information.INFORMATION.getSubjects().get(2)+" = "+marks.get(2)+"" +
                ", for "+Information.INFORMATION.getSubjects().get(3)+" = "+marks.get(3)+"" +
                ", for "+Information.INFORMATION.getSubjects().get(4)+" = "+marks.get(4)+
                '}';
    }
}
