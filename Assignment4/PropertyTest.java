
package propertymanagement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PropertyTest {

    // Test 1: Default constructor
    @Test
    public void testDefaultConstructor() {
        Property p = new Property();

        assertEquals("", p.getPropertyName());
        assertEquals("", p.getCity());
        assertEquals(0.0, p.getRentAmount(), 0.001);
        assertEquals("", p.getOwner());
    }

    // Test 2: Parameterized constructor
    @Test
    public void testParameterizedConstructor() {
        Property p = new Property("Sunset Apartments",
                "Rockville", 1500.0, "John Smith");

        assertEquals("Sunset Apartments", p.getPropertyName());
        assertEquals("Rockville", p.getCity());
        assertEquals(1500.0, p.getRentAmount(), 0.001);
        assertEquals("John Smith", p.getOwner());
    }

    // Test 3: Property copy constructor
    @Test
    public void testCopyConstructor() {
        Property original = new Property("Green House",
                "Bethesda", 2000.0, "Mary Johnson");

        Property copy = new Property(original);

        original.setRentAmount(2500.0);

        assertEquals(2000.0, copy.getRentAmount(), 0.001);
        assertEquals("Green House", copy.getPropertyName());
    }

    // Test 4: Add properties
    @Test
    public void testAddProperty() {
        ManagementCompany company =
                new ManagementCompany("ABC Management", "12345");

        Property p1 = new Property("House A",
                "Rockville", 1500.0, "John");

        Property p2 = new Property("House B",
                "Bethesda", 2000.0, "Mary");

        assertEquals(0, company.addProperty(p1));
        assertEquals(1, company.addProperty(p2));
        assertEquals(2, company.getPropertyCount());
    }

    // Test 5: Calculate total rent
    @Test
    public void testTotalRent() {
        ManagementCompany company =
                new ManagementCompany("ABC Management", "12345");

        company.addProperty(new Property(
                "House A", "Rockville", 1500.0, "John"));

        company.addProperty(new Property(
                "House B", "Bethesda", 2000.0, "Mary"));

        assertEquals(3500.0, company.totalRent(), 0.001);
    }
}
