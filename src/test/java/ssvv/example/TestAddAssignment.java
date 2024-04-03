package ssvv.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddAssignment {

    private TemaXMLRepository temaXMLRepository;
    private TemaValidator temaValidator;
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml = new File("temeTest.xml");
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
        this.temaValidator = new TemaValidator();
        this.temaXMLRepository = new TemaXMLRepository(temaValidator, "temeTest.xml");
        this.service = new Service(null, this.temaXMLRepository, null);
    }

//    @AfterAll
//    static void removeXML() {
//        new File("temeTest.xml").delete();
//    }

    @Test
    public void tc1_saveTema_validInput_successfullySaved() {
        int result = service.saveTema("1", "MPP", 7, 6);

        assertEquals(1, result);
    }

    @Test
    public void tc2_saveTema_existingID_notSaved(){
        int firstSaveResult = service.saveTema("2", "CN", 8, 6);
        assertEquals(1, firstSaveResult);

        int secondSaveResult = service.saveTema("2", "CN", 8, 6);
        assertEquals(0, secondSaveResult);
    }
}
