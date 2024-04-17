package ssvv.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void t3_saveTema_emptyID_notSaved() {
        int result = service.saveTema("", "SSVV", 6, 5);
        assertEquals(-1, result);
    }

    @Test
    public void t4_saveTema_nullID_notSaved() {
        int result = service.saveTema(null, "SSVV", 6, 5);
        assertEquals(-1, result);
    }

    @Test
    public void t5_saveTema_emptyDescriere_notSaved() {
        int result = service.saveTema("3", "", 6, 5);
        assertEquals(-1, result);
    }

    @Test
    public void t6_saveTema_nullDescriere_notSaved() {
        int result = service.saveTema("3", null, 6, 5);
        assertEquals(-1, result);
    }

    @Test
    public void t7_saveTema_tooSmallDeadline_notSaved() {
        int result = service.saveTema("3", "SSVV", 0, 5);
        assertEquals(-1, result);
    }
    @Test
    public void t8_saveTema_tooLargeDeadline_notSaved() {
        int result = service.saveTema("3", "SSVV", 18, 5);
        assertEquals(-1, result);
    }

    @Test
    public void t9_saveTema_deadlineSmallerThanStartline_notSaved() {
        int result = service.saveTema("3", "SSVV", 4, 5);
        assertEquals(-1, result);
    }

    @Test
    public void t10_saveTema_tooSmallStartline_notSaved() {
        int result = service.saveTema("3", "SSVV", 5, 0);
        assertEquals(-1, result);
    }


}
