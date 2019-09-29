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

    private Student() {
        fname = "none";
        lname = "none";
        cwid = "0";
    }

    public Student(String fname, String lname, String cwid) {
        this.fname = fname;
        this.lname = lname;
        this.cwid = cwid;
    }

    public static void serialize(Student stud, String filename) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
        os.writeObject(stud);


        /*
        final String CSV_SEPARATOR = ",";
        try
        {
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
        catch (UnsupportedEncodingException e) {
            System.out.print(e);
        }
        catch (FileNotFoundException e){
            System.out.print(e);
        }
        catch (IOException e){
            System.out.print(e);
        }*/
    }

    public static Student deserialize(String filename) {
        //deserialize to csv using java.util.nio
        try (Scanner scanner = new Scanner(new File(filename));
        ) {
            scanner.nextLine();
            String firstLine = scanner.nextLine();
            String[] firstLineArry = firstLine.split(",");
            String fname = firstLineArry[0];
            String lname = firstLineArry[1];
            String cwid = firstLineArry[2];
            return new Student(fname, lname, cwid);
        }
        catch (FileNotFoundException e) {
            System.out.println(e + "\nBase Student object is returned");
            return new Student();
        }
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
