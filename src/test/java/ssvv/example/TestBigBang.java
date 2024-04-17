package ssvv.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBigBang {
    //    private Service service;
//
//    @BeforeAll
//    static void createXML() {
//        File xml = new File("studentiTest.xml");
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
//            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
//                    "<inbox>\n" +
//                    "\n" +
//                    "</inbox>");
//            writer.flush();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File xml2 = new File("temeTest.xml");
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml2))) {
//            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
//                    "<inbox>\n" +
//                    "\n" +
//                    "</inbox>");
//            writer.flush();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File xml3 = new File("noteTest.xml");
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml3))) {
//            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
//                    "<inbox>\n" +
//                    "\n" +
//                    "</inbox>");
//            writer.flush();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @BeforeEach
//    public void setup() {
//        StudentValidator studentValidator = new StudentValidator();
//        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "studentiTest.xml");
//        TemaValidator temaValidator = new TemaValidator();
//        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "temeTest.xml");
//        NotaValidator notaValidator = new NotaValidator();
//        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "noteTest.xml");
//        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
//    }
//
//    @AfterAll
//    public static void teardown() {
//
//        new File("studentiTest.xml").delete();
//        new File("temeTest.xml").delete();
//        new File("noteTest.xml").delete();
//    }
//
//    @Test
//    public void testAddStudent() {
//        assertEquals(1, service.saveStudent("1", "Maria", 937));
//    }
//
//    @Test
//    public void testAddTema() {
//        assertEquals(1, service.saveTema("1", "A1", 3, 1));
//    }
//
//    @Test
//    public void testAddGrade() {
//        assertEquals(1, service.saveNota("1", "1", 9.0, 3, "correct"));
//
//        //service.deleteNota("333");
//        service.deleteStudent("1");
//        service.deleteTema("1");
//    }
//
//    @Test
//    public void integrationTest() {
//        assertEquals(1, service.saveStudent("2", "Ioana", 931));
//        assertEquals(1, service.saveTema("2", "A2", 4, 2));
//        assertEquals(1, service.saveNota("2", "2", 9.5, 4, "good"));
//
//        //service.deleteNota("333");
//        service.deleteStudent("1");
//        service.deleteTema("1");
//    }
//}
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml1 = new File("bigBangStudentTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml1))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File xml2 = new File("bigBangTemaTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml2))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File xml3 = new File("bigBangNotaTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml3))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "bigBangTemaTest.xml");
        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "bigBangStudentTest.xml");
        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "bigBangNotaTest.xml");
        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @AfterAll
    public static void teardown() {

        new File("studentiTest.xml").delete();
        new File("temeTest.xml").delete();
        new File("noteTest.xml").delete();
    }

    @Test
    public void testAddStudent() {
        int result = service.saveStudent("1", "Maria", 937);

        assertEquals(1, result);
    }

    @Test
    public void testAddAssignment() {
        int result = service.saveTema("1", "MPP", 7, 6);

        assertEquals(1, result);
    }

    @Test
    public void testAddGrade() {
        service.saveStudent("1", "Maria", 937);
        service.saveTema("1", "MPP", 7, 6);
        assertEquals(1, service.saveNota("1", "1", 9, 7, "good"));

        service.deleteStudent("1");
        service.deleteTema("1");
    }

    @Test
    public void integrationTest() {
        int result = service.saveStudent("2", "Marian", 937);
        int resultTema = service.saveTema("2", "MPP", 7, 6);

        assertEquals(1, result);
        assertEquals(1, resultTema);
        assertEquals(1, service.saveNota("2", "2", 10, 7, "good"));

        service.deleteStudent("1");
        service.deleteTema("1");
    }
}
