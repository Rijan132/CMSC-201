package guilab;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This panel is the basic panel, inside which other panels are placed.  
 * Before beginning to implement, design the structure of your GUI in order to 
 * understand what panels go inside which ones, and what buttons or other components
 * go in which panels.  
 * @author ralexander
 *
 */
//make the main panel's layout be a VBox
public class FXMainPane extends VBox {

	//student Task #2:
	//  declare five buttons, a label, and a textfield
	//  declare two HBoxes
	Button helloButton, howdyButton, chineseButton, clearButton, exitButton;
	Label feedbackLabel;
	TextField feedbackField;
	HBox buttonBox, feedbackBox;
	//student Task #4:
	//  declare an instance of DataManager
	private DataManager dataManager;
	/**
	 * The MainPanel constructor sets up the entire GUI in this approach.  Remember to
	 * wait to add a component to its containing component until the container has
	 * been created.  This is the only constraint on the order in which the following 
	 * statements appear.
	 */
	FXMainPane() {
		//student Task #2:
		//  instantiate the buttons, label, and textfield
		//  instantiate the HBoxes
		helloButton = new Button("Hello");
		howdyButton = new Button("Howdy");
		chineseButton = new Button("Chinese");
		clearButton = new Button("Clear");
		exitButton = new Button("Exit");

		feedbackLabel = new Label("Feedback:");
		feedbackField = new TextField();

		buttonBox = new HBox();
		feedbackBox = new HBox();
		//student Task #4:
		//  instantiate the DataManager instance
		dataManager = new DataManager();
		//  set margins and set alignment of the components
		setPadding(new Insets(15));
		setSpacing(10);

		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);

		feedbackBox.setSpacing(10);
		feedbackBox.setAlignment(Pos.CENTER);
		Insets inset = new Insets(10);

		HBox.setMargin(helloButton, inset);
		HBox.setMargin(howdyButton, inset);
		HBox.setMargin(chineseButton, inset);
		HBox.setMargin(clearButton, inset);
		HBox.setMargin(exitButton, inset);

		HBox.setMargin(feedbackLabel, inset);
		HBox.setMargin(feedbackField, inset);
		//student Task #3:
		//  add the label and textfield to one of the HBoxes
		//  add the buttons to the other HBox
		//  add the HBoxes to this FXMainPanel (a VBox)
		feedbackBox.getChildren().addAll(feedbackLabel, feedbackField);

		buttonBox.getChildren().addAll(helloButton, howdyButton, chineseButton, clearButton, exitButton);

		getChildren().addAll(buttonBox, feedbackBox);
		helloButton.setOnAction(new ButtonHandler());
		howdyButton.setOnAction(new ButtonHandler());
		chineseButton.setOnAction(new ButtonHandler());
		clearButton.setOnAction(new ButtonHandler());
		exitButton.setOnAction(new ButtonHandler());
	}
	
	//Task #4:
	//  create a private inner class to handle the button clicks
	private class ButtonHandler implements EventHandler<ActionEvent> {

	    @Override
	    public void handle(ActionEvent event) {

	        if (event.getSource() == helloButton) {
	            feedbackField.setText(dataManager.getHello());
	        }
	        else if (event.getSource() == howdyButton) {
	            feedbackField.setText(dataManager.getHowdy());
	        }
	        else if (event.getSource() == chineseButton) {
	            feedbackField.setText(dataManager.getChinese());
	        }
	        else if (event.getSource() == clearButton) {
	            feedbackField.setText("");
	        }
	        else if (event.getSource() == exitButton) {
	            Platform.exit();
	            System.exit(0);
	        }
	    }
	}
}
	
