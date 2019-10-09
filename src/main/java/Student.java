import java.io.*;
import java.nio.*;
import java.util.*;

public class Student implements Comparable<Student>, Serializable{
    private String firstName;
    private String lastName;
    private String cwid;

    public String getFname() {
        return firstName;
    }

    public void setFname(String firstName) {
        this.firstName = firstName;
    }

    public String getLname() {
        return lastName;
    }

    public void setLname(String lastName) {
        this.lastName = lastName;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    //changed access to public in order to use in driver class
    public Student() {
        firstName = "none";
        lastName = "none";
        cwid = "0";
    }

    public Student(String firstName, String lastName, String cwid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cwid = cwid;
    }

    public static void serializeToBinary(Student stud, String filename) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
        os.writeObject(stud);
    }

    public static void serializeToCSV(Student stud, String filename) throws FileNotFoundException, IOException {
        final String CSV_SEPARATOR = ",";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append("First Name,Last Name,CWID");
            bw.write(oneLine.toString());

            bw.newLine();

            oneLine = new StringBuffer();
            oneLine.append(stud.firstName);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(stud.lastName);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(stud.cwid);
            bw.write(oneLine.toString());

            bw.flush();
            bw.close();
    }

    public static Student deserializeFromBinary(String filename)
            throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
        return (Student) is.readObject();
    }

    public static Student deserializeFromCSV(String filename) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        scanner.nextLine();
        String firstLine = scanner.nextLine();
        String[] firstLineArry = firstLine.split(",");
        String firstName = firstLineArry[0];
        String lastName = firstLineArry[1];
        String cwid = firstLineArry[2];
        return new Student(firstName, lastName, cwid);
    }

    public void printInfo() {
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("CWID: " + cwid);
    }

    private String getInfo() {
        return firstName + lastName + cwid;
    }

    @Override
    public int compareTo(Student otherstud) {
        return getInfo().compareTo(otherstud.getInfo());
    }

    //why does @Override not overwrite parent class (Object.equals)?
    public boolean equals(Object student) {
        //variable to hold what's returned
        boolean isTrue = true;

        //first check if both are Student
        if (student instanceof Student) {
            //cast student to a Student (object)
            Student otherstud = (Student) student;
            //now check if each of the fields match independently
            if (!this.getFname().equals(((Student) otherstud).getFname())) {
                isTrue = false;
            }
            if (!this.getLname().equals(((Student) otherstud).getLname())) {
                isTrue = false;
            }
            if (!this.getCwid().equals(((Student) otherstud).getCwid())) {
                isTrue = false;
            }

        }
        return isTrue;
    }

}
