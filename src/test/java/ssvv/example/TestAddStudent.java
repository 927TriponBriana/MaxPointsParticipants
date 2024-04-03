package ssvv.example;

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

//    @AfterAll
//    static void removeXML() {
//        new File("fisiere/studentiTest.xml").delete();
//    }

    @Test
    public void tc1_saveStudent_validInput_successfullySaved() {
        int result = service.saveStudent("5", "Maria", 937);

        assertEquals(1, result);
    }

    @Test
    public void tc2_saveStudent_existingID_notSaved(){
        int firstSaveResult = service.saveStudent("1", "Maria", 931);
        assertEquals(1, firstSaveResult);

        int secondSaveResult = service.saveStudent("1", "Maria", 931);
        assertEquals(0, secondSaveResult);
    }

    @Test
    public void tc3_saveStudent_nullID_exceptionThrown() {
        int result = service.saveStudent(null, "Maria", 931);
        assertEquals(1, result);

    }

    @Test
    public void tc4_saveStudent_emptyID_exceptionThrown() {
        int result = service.saveStudent("", "Maria", 931);
        assertEquals(1, result);
    }

    @Test
    public void tc5_saveStudent_nullName_exceptionThrown() {
        int result = service.saveStudent("2", null, 931);
        assertEquals(1, result);
    }

    @Test
    public void tc6_saveStudent_emptyName_exceptionThrown() {
        int result = service.saveStudent("3", "", 931);
        assertEquals(1, result);
    }

    @Test
    public void tc7_saveStudent_invalidGroup_exceptionThrown() {
        int result = service.saveStudent("4", "Maria", -931);
        assertEquals(1, result);
    }

}