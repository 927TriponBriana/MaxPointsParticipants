package ssvv.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddGrade {
    private NotaXMLRepository notaXMLRepository;
    private NotaValidator notaValidator;
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml = new File("noteTest.xml");
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
        this.notaValidator = new NotaValidator();
        this.notaXMLRepository = new NotaXMLRepository(notaValidator, "noteTest.xml");
        this.service = new Service(null, null, this.notaXMLRepository);
    }

    @Test
    public void tc1_saveNota_validInput_successfullySaved() {
        int result = service.saveNota("1", "1", 9, 1, "good");

        assertEquals(1, result);
    }

}
