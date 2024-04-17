package ssvv.example;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Before;
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


public class BigBangTesting {
    private Service service;
    private TemaXMLRepository temaXMLRepository;
    private TemaValidator temaValidator;
    private StudentXMLRepository studentXMLRepository;
    private StudentValidator studentValidator;
    private NotaXMLRepository notaXMLRepository;
    private NotaValidator notaValidator;

    @BeforeAll
    static void createXML() {
        File xml1 = new File("bigBangStudentTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml1))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        File xml2 = new File("bigBangTemaTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml2))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        File xml3 = new File("bigBangNotaTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml3))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        this.temaValidator = new TemaValidator();
        this.temaXMLRepository = new TemaXMLRepository(temaValidator, "bigBangTemaTest.xml");
        this.studentValidator = new StudentValidator();
        this.studentXMLRepository = new StudentXMLRepository(studentValidator, "bigBangStudentTest.xml");
        this.notaValidator = new NotaValidator();
        this.notaXMLRepository = new NotaXMLRepository(notaValidator, "bigBangNotaTest.xml");
        this.service = new Service(this.studentXMLRepository, this.temaXMLRepository, this.notaXMLRepository);
    }

    @Before
    public void setUp() {
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);  // Initialize your service
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
        service.saveStudent("5", "Maria", 937);
        service.saveTema("1", "MPP", 7, 6);
        assertEquals(-1, service.saveNota("1", "1", 10, 3, "good"));
    }

    @Test
    public void integrationTest() {
        int result = service.saveStudent("2", "Marian", 937);
        int resultTema = service.saveTema("1", "MPP", 7, 6);

        assertEquals(1, result);
        assertEquals(1, resultTema);
        assertEquals(-1, service.saveNota("2", "2", 10, 3, "good"));
    }
}
