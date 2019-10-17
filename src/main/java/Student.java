import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import com.thoughtworks.xstream.XStream;

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
        this("none", "none", "0");
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
        filename = filename + ".csv";
        Path path = Paths.get(filename);
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
            StringBuffer oneLine = new StringBuffer();
            oneLine.append("First Name,Last Name,CWID");
            writer.write(oneLine.toString());

            writer.newLine();

            oneLine = new StringBuffer();
            oneLine.append(stud.firstName);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(stud.lastName);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(stud.cwid);
            writer.write(oneLine.toString());

            writer.flush();
            writer.close();
    }

    public static void serializeToXML(Student stud, String filename)
            throws FileNotFoundException, IOException {
        XStream xstream = new XStream();
        String xml = xstream.toXML(stud);

        filename = filename + ".xml";
        Path path = Paths.get(filename);
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(xml);
        writer.write(oneLine.toString());
        writer.flush();
        writer.close();
    }

    public static Student deserializeFromBinary(String filename)
            throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
        return (Student) is.readObject();
    }

    public static Student deserializeFromCSV(String filename) throws FileNotFoundException{
        filename = filename + ".csv";
        Scanner scanner = new Scanner(new File(filename));
        scanner.nextLine();
        String firstLine = scanner.nextLine();
        String[] firstLineArry = firstLine.split(",");
        String firstName = firstLineArry[0];
        String lastName = firstLineArry[1];
        String cwid = firstLineArry[2];
        return new Student(firstName, lastName, cwid);
    }

    public static Student deserializeFromXML(String filename) throws FileNotFoundException, IOException{
        XStream xstream = new XStream();
        filename = filename + ".xml";
        Path path = Paths.get(filename);
        String fileStr = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        System.out.println("FileStraasdfasdf: \n" + fileStr);
        return (Student) xstream.fromXML(fileStr);
    }

    public void printInfo() {
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("cwid: " + cwid);
    }

    private String getInfo() {
        return firstName + lastName + cwid;
    }

    @Override
    public int compareTo(Student otherstud) {
        return getInfo().compareToIgnoreCase(otherstud.getInfo());
    }

    @Override
    public int hashCode() {
        //not really sure I did this correctly
        int result = 15;

        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + cwid.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object student) {
        //variable to hold what's returned
        boolean isTrue = true;

        //first check if both are Student
        if (student instanceof Student) {
            //cast student to a Student (object)
            Student otherstud = (Student) student;
            //now check if each of the fields match independently

            isTrue = isTrue && (this.getFname().compareToIgnoreCase(otherstud.getFname()) == 0);
            isTrue = isTrue && (this.getLname().compareToIgnoreCase(otherstud.getLname()) == 0);
            isTrue = isTrue && (this.getCwid().compareToIgnoreCase(otherstud.getCwid()) == 0);
        }
        return isTrue;
    }

}
