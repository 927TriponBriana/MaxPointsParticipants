package ssvv.example;

import domain.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import service.Service;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TestAddStudent {
    private StudentXMLRepository studentFileRepository;
    private StudentValidator studentValidator;
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml = new File("studentiTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
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
        this.studentValidator = new StudentValidator();
        this.studentFileRepository = new StudentXMLRepository(studentValidator, "studentiTest.xml");
        this.service = new Service(this.studentFileRepository, null, null);
    }

    @AfterAll
    static void removeXML() {
        new File("fisiere/studentiTest.xml").delete();
    }

    @Test
    public void testSaveStudentSuccess() {
        int result = service.saveStudent("5", "Antonia", 937);

        assertEquals(1, result, "Student should be successfully saved and method should return 1");
    }

    @Test
    public void testSaveStudentFailureDueToDuplicate() {
        service.saveStudent("1", "Ana", 931);
        int result = service.saveStudent("1", "Ana", 931);

        assertEquals(0, result, "Attempt to save a duplicate student should fail and return 0");
    }
}