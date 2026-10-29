
package propertymanagement;

public class ManagementCompany {

    private String name;
    private String taxId;
    private Property[] properties;
    private int propertyCount;

    // Parameterized constructor
    public ManagementCompany(String name, String taxId) {
        this.name = name;
        this.taxId = taxId;
        properties = new Property[5];
        propertyCount = 0;
    }

    // Copy constructor
    public ManagementCompany(ManagementCompany other) {
        this.name = other.name;
        this.taxId = other.taxId;
        this.properties = new Property[5];
        this.propertyCount = other.propertyCount;

        for (int i = 0; i < propertyCount; i++) {
            properties[i] = new Property(other.properties[i]);
        }
    }

    // Add a property
    public int addProperty(Property p) {
        if (propertyCount >= properties.length) {
            return -1;
        }

        if (p == null) {
            return -2;
        }

        properties[propertyCount] = new Property(p);
        propertyCount++;
        return propertyCount - 1;
    }

    // Calculate total rent
    public double totalRent() {
        double total = 0;

        for (int i = 0; i < propertyCount; i++) {
            total += properties[i].getRentAmount();
        }

        return total;
    }

    // Get number of properties
    public int getPropertyCount() {
        return propertyCount;
    }

    // Display company information
    @Override
    public String toString() {
        String result = "Management Company: " + name
                + "\nTax ID: " + taxId
                + "\n\nProperties:\n";

        for (int i = 0; i < propertyCount; i++) {
            result += "\n" + properties[i].toString() + "\n";
        }

        result += "\nTotal Rent: $" + totalRent();

        return result;
    }
}
