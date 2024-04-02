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


public class TestAddStudent {

    @Test
    public void testSaveStudentSuccess() {
        StudentValidator validator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(validator, "studenti.xml");
        Service service = new Service(studentRepo, null, null);

        int result = service.saveStudent("1", "Test Student", 101);

        assertEquals(1, result, "Student should be successfully saved and method should return 1");
    }

    @Test
    public void testSaveStudentFailureDueToDuplicate() {
        StudentValidator validator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(validator, "students.xml");
        Service service = new Service(studentRepo, null, null);

        service.saveStudent("1", "Existing Student", 101);
        int result = service.saveStudent("1", "New Student", 102);

        assertEquals(1, result, "Attempt to save a duplicate student should fail and return 0");
    }
}