import java.io.IOException;

public class Driver {
    private String filename = "studentinfo";

    private boolean serializeToCSV(Student stud) {
        boolean successful = true;
        print("stud serializing to CSV...");
        try {
            Student.serializeToCSV(stud, filename);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            successful = false;
        }
        stud.printInfo();
        return successful;
    }

    private Student deserializeFromCSV() {
        print("stud2 deserializing...");
        Student stud2 = new Student();
        try {
            stud2 = Student.deserializeFromCSV(new String(filename));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return stud2;
    }



    public void run() {
        Student stud = new Student("Garrett","Brenner","20131189");

        //variable to keep track if an object was successfully serialized
        boolean wasSerialized;

        wasSerialized = serializeToCSV(stud);

        //if the object was successfully serialized, deserialize
        if (wasSerialized) {
            Student stud2 = deserializeFromCSV();

            print("comparing...");
            if (stud.equals(stud2)) {
                print("The students are equal");
            }
            else {
                print("The students are not equal");
            }
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
