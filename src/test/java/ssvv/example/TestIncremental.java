package ssvv.example;

import org.junit.jupiter.api.*;
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

public class TestIncremental {
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml1 = new File("incrementalStudentTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml1))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File xml2 = new File("incrementalTemaTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml2))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File xml3 = new File("incrementalNotaTest.xml");
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
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "incrementalTemaTest.xml");
        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "incrementalStudentTest.xml");
        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "incrementalNotaTest.xml");
        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @AfterAll
    public static void teardown() {

        new File("studentiTest.xml").delete();
        new File("temeTest.xml").delete();
        new File("noteTest.xml").delete();
    }

    @AfterEach
    public void cleanup() {
        // Delete the student and tema after each test
        service.deleteStudent("1");
        service.deleteTema("1");
    }

    @Test
    public void testAddStudent() {
        int result = service.saveStudent("1", "Maria", 937);
        assertEquals(1, result);
    }

    @Test
    public void testAddAssignment() {
        int resultStudent = service.saveStudent("1", "Maria", 937);
        assertEquals(1, resultStudent);

        int resultAssignment = service.saveTema("1", "MPP", 7, 6);
        assertEquals(1, resultAssignment);
    }

    @Test
    public void testAddGrade() {
        int resultStudent = service.saveStudent("1", "Maria", 937);
        assertEquals(1, resultStudent);

        int resultAssignment = service.saveTema("1", "MPP", 7, 6);
        assertEquals(1, resultAssignment);

        int resultGrade = service.saveNota("1", "1", 9, 7, "good");
        assertEquals(1, resultGrade);

        service.deleteStudent("1");
        service.deleteTema("1");
    }
}
