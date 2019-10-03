import java.io.*;
import java.nio.*;
import java.util.*;

public class Student implements Comparable<Student>, Serializable{
    private String fname;
    private String lname;
    private String cwid;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    //changed access to public in order to use in driver class
    public Student() {
        fname = "none";
        lname = "none";
        cwid = "0";
    }

    public Student(String fname, String lname, String cwid) {
        this.fname = fname;
        this.lname = lname;
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
            oneLine.append(stud.fname);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(stud.lname);
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
        String fname = firstLineArry[0];
        String lname = firstLineArry[1];
        String cwid = firstLineArry[2];
        return new Student(fname, lname, cwid);
    }

    public void printInfo() {
        System.out.println("First name: " + fname);
        System.out.println("Last name: " + lname);
        System.out.println("CWID: " + cwid);
    }

    private String getInfo() {
        return fname + lname + cwid;
    }

    @Override
    public int compareTo(Student otherstud) {
        return getInfo().compareTo(otherstud.getInfo());
    }

}
