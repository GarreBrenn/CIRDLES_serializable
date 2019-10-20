import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void serializeToBinary() {
        Student garrett = new Student("Garrett", "Brenner", "20131189");
        try {
            Student.serializeToBinary(garrett, "gbinfo");
        } catch (Exception e) {
            fail("Serializing caught an exception");
        }

        Student garrett2 = new Student();
        try {
            garrett2 = Student.deserializeFromBinary("gbinfo");
        } catch (Exception e) {
            fail("Deserializing caught an exception");
        }

        assertEquals(garrett, garrett2);
    }

    @Test
    public void serializeToCSV() {
        Student garrett = new Student("Garrett", "Brenner", "20131189");
        try {
            Student.serializeToCSV(garrett, "gbinfo");
        } catch (Exception e) {
            fail("Serializing caught an exception");
        }

        Student garrett2 = new Student();
        try {
            garrett2 = Student.deserializeFromCSV("gbinfo");
        } catch (Exception e) {
            fail("Deserializing caught an exception");
        }

        assertEquals(garrett, garrett2);
    }

    @Test
    public void serializeToXML() {
        Student garrett = new Student("Garrett", "Brenner", "20131189");
        try {
            Student.serializeToXML(garrett, "gbinfo");
        } catch (Exception e) {
            fail("Serializing caught an exception");
        }

        Student garrett2 = new Student();
        try {
            garrett2 = Student.deserializeFromXML("gbinfo");
        } catch (Exception e) {
            fail("Deserializing caught an exception");
        }

        assertEquals(garrett, garrett2);
    }
}