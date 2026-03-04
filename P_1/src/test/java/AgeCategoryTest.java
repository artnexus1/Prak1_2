import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeCategoryTest {

    @Test
    void testChild_YoungerThanZero() {
        assertEquals("возраст не может быть отрицательным", Main.getAgeCategory(-5));
    }

    @Test
    void testChild_Boundary0() {
        assertEquals("ребенок", Main.getAgeCategory(0));
    }

    @Test
    void testChild() {
        assertEquals("ребенок", Main.getAgeCategory(12));
    }

    @Test
    void testAdult_Boundary18() {
        assertEquals("взрослый", Main.getAgeCategory(18));
    }

    @Test
    void testAdult_Boundary64() {
        assertEquals("взрослый", Main.getAgeCategory(64));
    }

}