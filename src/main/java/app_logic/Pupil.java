package app_logic;


public class Pupil {
    private static int counter = 1;
    private final int id;
    private final String name;
    private final Marks marks;

    public Pupil(String name, Marks marks) {
        this.id = counter++;
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public Marks getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
