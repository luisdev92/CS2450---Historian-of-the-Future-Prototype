import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	
	ImageView[] menuButtonsView; 
	Button[] menuButtons; 
	
	public static void main(String[] args) {
		System.out.println("Run");
		launch(args); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		HBox menu = new HBox(10); 
		
		menuButtons = new Button[5]; 
		
		menuButtons[0] = new Button("Mission"); 
		menuButtons[1] = new Button("Overview");
		menuButtons[2] = new Button("Events");
		menuButtons[3] = new Button("Contents");
		menuButtons[4] = new Button("Services");
		
		menuButtons[0].setGraphic(new ImageView(new Image(new FileInputStream("src/img/menu1.jpg"))));
		menuButtons[1].setGraphic(new ImageView(new Image(new FileInputStream("src/img/menu2.jpg"))));
		menuButtons[2].setGraphic(new ImageView(new Image(new FileInputStream("src/img/menu3.jpg"))));
		menuButtons[3].setGraphic(new ImageView(new Image(new FileInputStream("src/img/menu4.jpg"))));
		menuButtons[4].setGraphic(new ImageView(new Image(new FileInputStream("src/img/menu5.jpg"))));
				
		Button homeLogoBtn = new Button();
		homeLogoBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/img/home.png"))));
		
		menu.getChildren().add(homeLogoBtn); 
		
		for (int i = 0; i < 5; i++) {
			menu.getChildren().add(menuButtons[i]); 
		}
	
		// Primary Body When Load 
		VBox mainLogo = new VBox(); 
		VBox sypnopsis = new VBox(); 
		
		HBox body = new HBox(mainLogo, sypnopsis); 
		
		VBox primaryContainer = new VBox(menu, body); 
		
		
		Scene scene = new Scene(primaryContainer, 1000, 500); 
		
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
