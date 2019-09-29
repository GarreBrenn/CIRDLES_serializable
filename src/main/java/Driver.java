import java.io.IOException;

public class Driver {
    private String filename = "studentinfo.txt";

    public void run() {
        Student stud = new Student("Garrett","Brenner","20131189");

        print("stud serializing...");
        try {
            Student.serialize(stud, filename);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        print("stud2 deserializing...");
        Student stud2 = new Student();
        try {
            stud2 = Student.deserialize(filename);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        print("comparing...");
        if (stud.compareTo(stud2) == 0) {
            print("The students are equal");
        }
        else {
            print("The students are not equal");
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
