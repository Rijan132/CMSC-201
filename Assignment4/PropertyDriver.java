
package propertymanagement;

public class PropertyDriver {

    public static void main(String[] args) {

        // Create a management company
        ManagementCompany company =
                new ManagementCompany("ABC Management", "12345");

        // Create property objects
        Property property1 =
                new Property("Sunset Apartments", "Rockville",
                             1500.0, "John Smith");

        Property property2 =
                new Property("Green House", "Bethesda",
                             2000.0, "Mary Johnson");

        Property property3 =
                new Property("Lake View", "Gaithersburg",
                             1800.0, "David Brown");

        // Add properties to the company
        company.addProperty(property1);
        company.addProperty(property2);
        company.addProperty(property3);

        // Display company information
        System.out.println(company);

        // Demonstrate Property copy constructor
        Property propertyCopy = new Property(property1);

        System.out.println("\nCopied Property:");
        System.out.println(propertyCopy);

        // Demonstrate ManagementCompany copy constructor
        ManagementCompany companyCopy =
                new ManagementCompany(company);

        System.out.println("\nCopied Management Company:");
        System.out.println(companyCopy);
    }
}
