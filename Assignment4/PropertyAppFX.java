
package propertymanagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PropertyAppFX extends Application {

    private ManagementCompany company =
            new ManagementCompany("ABC Management", "12345");

    @Override
    public void start(Stage stage) {

        // Create labels and text fields
        TextField nameField = new TextField();
        nameField.setPromptText("Property Name");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField rentField = new TextField();
        rentField.setPromptText("Rent Amount");

        TextField ownerField = new TextField();
        ownerField.setPromptText("Owner");

        // Create buttons
        Button addButton = new Button("Add Property");
        Button totalButton = new Button("Calculate Total Rent");

        // Create output area
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Add Property button action
        addButton.setOnAction(event -> {

            try {
                String name = nameField.getText();
                String city = cityField.getText();
                double rent = Double.parseDouble(rentField.getText());
                String owner = ownerField.getText();

                Property property =
                        new Property(name, city, rent, owner);

                int result = company.addProperty(property);

                if (result == -1) {
                    outputArea.setText("Property array is full!");
                } else {
                    outputArea.setText(company.toString());

                    nameField.clear();
                    cityField.clear();
                    rentField.clear();
                    ownerField.clear();
                }

            } catch (NumberFormatException e) {
                outputArea.setText("Please enter a valid rent amount.");
            }
        });

        // Calculate Total Rent button action
        totalButton.setOnAction(event -> {
            outputArea.setText(company.toString());
        });

        // Layout
        VBox root = new VBox(10);

        root.setPadding(new Insets(15));

        root.getChildren().addAll(
                new Label("Property Management Application"),
                nameField,
                cityField,
                rentField,
                ownerField,
                addButton,
                totalButton,
                outputArea
        );

        // Create scene
        Scene scene = new Scene(root, 450, 550);

        stage.setTitle("Property Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
