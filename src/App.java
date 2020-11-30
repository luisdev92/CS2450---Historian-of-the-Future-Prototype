
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
	
	ImageView[] menuButtonsView; 
	Button[] menuButtons; 
	HBox body; 
	VBox primaryContainer; 
	
	public static void main(String[] args) {
		System.out.println("Run");
		launch(args); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		HBox menu;
		VBox mainLogo, sypnopsis;
		Button homeLogoBtn;
		Label mainText, frontText;
		
		menu = new HBox(10); 
		
		menuButtons = new Button[6]; 
		
		menuButtons[0] = new Button("Home"); 
		menuButtons[1] = new Button("Topics");
		menuButtons[2] = new Button("Services");
		menuButtons[3] = new Button("Events");
		menuButtons[4] = new Button("Art");
		menuButtons[5] = new Button("Contact");
	
		for (int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].setGraphic(new ImageView(new Image(new FileInputStream("src/img/menu" + (i + 1) + ".jpg"))));
			menuButtons[i].setFont(Font.font(10));
			// menuButtons[i].getStyleClass().add("menuButton" + (i + 1));
			menuButtons[i].setId("btn" + (i + 1));
			menuButtons[i].setOnAction(new ButtonChanger());
		}
		
		
		homeLogoBtn = new Button();
		homeLogoBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/img/home.png"))));
		homeLogoBtn.setId("homeLogoBtn");
		homeLogoBtn.setOnAction(new ButtonChanger());
		
		menu.getChildren().add(homeLogoBtn); 
		
		for (int i = 0; i < menuButtons.length; i++) {
			menu.getChildren().add(menuButtons[i]); 
		}
	
		ImageView logo = new ImageView(new Image(new FileInputStream("src/img/logo.jpg")));
		// Primary Body When Load 
		mainLogo = new VBox(10, logo); 
		
		mainText = new Label("Charles Osten"); 
		mainText.getStyleClass().add("main-text");
		
		frontText = new Label("Historian of the Future\n" + 
				"Strategic Research\n" + 
				"Technical Due Diligence\n" + 
				"Lecturer Author Speaker");
		frontText.getStyleClass().add("main-label");
		
		sypnopsis = new VBox(mainText, frontText); 
		sypnopsis.setAlignment(Pos.CENTER);
		
		body = new HBox(10, mainLogo, sypnopsis); 
		
		
		body.setAlignment(Pos.CENTER);
		primaryContainer = new VBox(10, menu, body); 
	
		primaryContainer.setPadding(new Insets(10));
		
		Scene scene = new Scene(primaryContainer, 1000, 800); 
		
		scene.getStylesheets().add("style.css");
		primaryStage.setTitle("Historian of the Future");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	class ButtonChanger implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event) {
			Button btn = (Button) event.getSource();
			String id = btn.getId(); 
			
			System.out.println(id);
			
			if (id.equals("btn1") || id.equals("homeLogoBtn")) {
				body.getChildren().clear();
				
				VBox mainLogo, sypnopsis;
				Label mainText, frontText;
				
				ImageView logo = null; 
				try {
					logo = new ImageView(new Image(new FileInputStream("src/img/logo.jpg")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Primary Body When Load 
				mainLogo = new VBox(logo); 
				
				mainText = new Label("Charles Osten"); 
				mainText.getStyleClass().add("main-text");
				
				frontText = new Label("Historian of the Future\n" + 
						"Strategic Research\n" + 
						"Technical Due Diligence\n" + 
						"Lecturer Author Speaker");
				frontText.getStyleClass().add("main-label");
				
				sypnopsis = new VBox(mainText, frontText); 
				sypnopsis.setAlignment(Pos.CENTER);	
				
				body.getChildren().addAll(mainLogo, sypnopsis); 
			} else if (id.equals("btn2")) {

		
				body.getChildren().clear();
				Label topic1 = new Label("Topics");
				
				topic1.getStyleClass().add("topic-label");
				
				Button[] links = new Button[5];
				
				links[0] = new Button("Bioconvergence");
				links[1] = new Button("Nanotechnology");
				links[2] = new Button("Synthetic Sentience"); 
				links[3] = new Button("The Nanotechnology Imperative");
				links[4] = new Button("Internet as an Organism"); 

				VBox topicScreen = new VBox(10); 
				HBox container = new HBox(topicScreen);
				
				topicScreen.setAlignment(Pos.CENTER);
				container.setAlignment(Pos.CENTER);
				
				topicScreen.getChildren().add(topic1); 
				
				for (int i = 0; i < links.length; i++) {
					links[i].setId("link" + (i + 1));
					links[i].setOnAction(new ButtonChanger());
					topicScreen.getChildren().add(links[i]); 
				}
				
				body.getChildren().add(container); 
				
			} else if (id.equals("btn3")) {
				
				body.getChildren().clear();
				
				ScrollPane s1 = new ScrollPane(); 
				s1.getStyleClass().add("scroll-pane");
				
				Label contact = new Label("Contact"); 
				Label email = new Label("Email: charles000@aol.com");
				Label phone = new Label("Phone: 1800-300-1999"); 
				
				
				Label senior = new Label("Senior Consultant");
				Label service1 = new Label("Strategic Development, Technology and Market Dynamics, "
						+ "Technical Due Diligence, Industry Analysis, White Papers,"
						+ "Presentations, Instructional Services and Lectures. ");
				service1.setWrapText(true);
				
				ImageView svclogo1 = null;
				ImageView svclogo2 = null; 
				try {
					svclogo1 = new ImageView(new Image(new FileInputStream("src/img/service1.jpg")));
					svclogo2 = new ImageView(new Image(new FileInputStream("src/img/service2.jpg")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Label service2 = new Label("Domain expertise includes Biological Metaphors in Computing, Reconfigurable Computing Architectures & Systems, MEMS, Specialized Materials and Nanotechnology.");
				service2.setWrapText(true);
				
				Label service3 = new Label("30+ year professional history and combined experience in specialized materials, electronics, photonics and electro-\n" + 
						"optical hardware, computing architectures, & related fields.");
				service3.setWrapText(true);
				
				VBox container = new VBox(contact, email, phone, senior, service1, svclogo1, service2, svclogo2, service3); 
				s1.setContent(container);
				
				
				body.getChildren().add(s1);
				
				
			} else if (id.equals("btn4")) {
				
				body.getChildren().clear();
				
				ScrollPane s1 = new ScrollPane();
				s1.getStyleClass().add("scroll-pane2");
				
				ImageView event1, event2, event3, event4, event5, event6 = null;
				
				try {
					event1 = new ImageView(new Image(new FileInputStream("src/img/event1.jpg")));
					event2 = new ImageView(new Image(new FileInputStream("src/img/event2.jpg")));
					event3 = new ImageView(new Image(new FileInputStream("src/img/event3.jpg")));
					event4 = new ImageView(new Image(new FileInputStream("src/img/event4.jpg")));
					event5 = new ImageView(new Image(new FileInputStream("src/img/event5.jpg")));
					event6 = new ImageView(new Image(new FileInputStream("src/img/event6.jpg")));
					
					// Joshua Tree 2012
					Label title1 = new Label("Joshua Tree 2012"); 
					VBox cont1 = new VBox(event1, title1); 
					
					Label title2 = new Label("Rain Forest of Kona"); 
					VBox cont2 = new VBox(event2, title2); 
					
					Label title3 = new Label("NanoSIG Electronics and Photonics Forum"); 
					VBox cont3 = new VBox(event3, title3); 
					
					Label title4 = new Label("Evolvable Networks and Biological Event"); 
					VBox cont4 = new VBox(event4, title4); 
					title4.setWrapText(true);
					
					Label title5 = new Label("Aztec Fire Dance Event"); 
					VBox cont5 = new VBox(event5, title5); 
					
					Label title6 = new Label("Quantum Biology Event SF Tesla Society"); 
					VBox cont6 = new VBox(event6, title6); 
					
					HBox eventCont1 = new HBox(10, cont1, cont2, cont3);
					HBox eventCont2 = new HBox(10, cont4, cont5, cont6); 
					
					VBox master = new VBox(eventCont1, eventCont2);
					
					s1.setContent(master);
					s1.setPrefWidth(1000);
					body.getChildren().add(s1);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			// Redirects to the Art Page 
			} else if (id.equals("btn5")) {
				
				body.getChildren().clear();
				
				ScrollPane s1 = new ScrollPane(); 
				s1.getStyleClass().add("scroll-pane");
				
				Label intro = new Label("Art Work"); 
				
				ImageView art1 = null;
				ImageView art2 = null; 
				ImageView art3 = null; 
				
				try {
					art1 = new ImageView(new Image(new FileInputStream("src/img/art1.jpg")));
					art2 = new ImageView(new Image(new FileInputStream("src/img/art2.jpg")));
					art3 = new ImageView(new Image(new FileInputStream("src/img/art3.jpg")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Label artDesc1 = new Label("Archival acrylic paint"); 
				Label artDesc2 = new Label("Dendrite series");
				Label artDesc3 = new Label("Fractal Infinity series"); 
				
				artDesc1.setWrapText(true);
				
				VBox container = new VBox(10, intro, art1, artDesc1, art2, artDesc2, art3, artDesc3);
				s1.setContent(container);
				
				s1.setFitToWidth(true);
				s1.setPrefWidth(1000);
				body.getChildren().add(s1); 
		
			} else if (id.equals("btn6")) {
				
				body.getChildren().clear();
				
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(10, 10, 10, 10));
				grid.setVgap(5);
				grid.setHgap(5);
				
				TextField name = new TextField();
				name.setPromptText("Enter Name");
				name.setPrefColumnCount(10);
				name.getText();
				grid.getChildren().add(name);
				GridPane.setConstraints(name, 0, 0);
				
				
				TextField lastName = new TextField();
				lastName.setPromptText("Enter Email");
				GridPane.setConstraints(lastName, 0, 1);
				grid.getChildren().add(lastName);
	
				TextField comment = new TextField();
				comment.setPrefColumnCount(15);
				comment.setPromptText("Enter your comment.");
				GridPane.setConstraints(comment, 0, 2);
				grid.getChildren().add(comment);
				
				//Defining the Submit button
				Button submit = new Button("Submit");
				GridPane.setConstraints(submit, 1, 0);
				grid.getChildren().add(submit);
				//Defining the Clear button
				Button clear = new Button("Clear");
				GridPane.setConstraints(clear, 1, 1);
				grid.getChildren().add(clear);
				
				Label title = new Label("Contact");
				VBox container = new VBox(title, grid);
				
				final Label label = new Label();
				GridPane.setConstraints(label, 0, 3);
				GridPane.setColumnSpan(label, 2);
				grid.getChildren().add(label);
				
				submit.setOnAction((e) -> {
			        if ((comment.getText() != null && !comment.getText().isEmpty())) {
			            label.setText(name.getText() + " " + lastName.getText() + ", "
			                + "thank you for your comment!");
			        } else {
			            label.setText("You have not left a comment.");
			        }
				});
				
				clear.setOnAction((e) -> {
			        name.clear();
			        lastName.clear();
			        comment.clear();
			        label.setText(null);
				});
			
				container.setAlignment(Pos.CENTER);
				
				body.getChildren().add(container);
				
			} else if (id.equals("link1")) {
				
				body.getChildren().clear();
				Label title1 = new Label("Bioconvergence - Progenitor of the Nanotechnology Age");
				Label text1 = new Label("Previous, recent \"ages\" of technology and economic development, "
						+ "with substantial, and irreversible social and cultural influences, such as the "
						+ "\"Computing Age\", subsequently evolving into the \"Information Age\" -"
						+ " driven by the combination of the internet, and ubiquitously "
						+ "distributed computing capacity, are only the minimal progenitors"
						+ "of what is about to emerge, \"the Nanotechnology Age\", "
						+ "the first stage progenitor of which is the phenomena of bioconvergence.");
				text1.setWrapText(true);
				try {
					ImageView image1 = new ImageView(new Image(new FileInputStream("src/img/link1.jpg")));
					
					VBox cont1 = new VBox(10, title1, text1, image1); 
					
					body.getChildren().add(cont1);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (id.equals("link2")) {
				
				body.getChildren().clear();
				Label title1 = new Label("Nanotechnology - Industrial Revolution of the Next Millennium");
				Label text1 = new Label("Nanotechnology, as a multi-disciplinarian, system level "
						+ "arena of scientific and technical development, is rapidly"
						+ " evolving away from the realms of academic obscurity, and "
						+ "into becoming the transitional threshold of an emergent "
						+ "industrial revolution. Nanotechnology is a collection of"
						+ "molecular scale manufacturing systems, which collectively "
						+ "should be viewed as a gateway to an entire new industrial infrastructure.");
				text1.setWrapText(true);
				try {
					ImageView image1 = new ImageView(new Image(new FileInputStream("src/img/link2.jpg")));
					
					VBox cont1 = new VBox(10, title1, text1, image1); 
					
					body.getChildren().add(cont1);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (id.equals("link3")) {
				body.getChildren().clear();
				Label title1 = new Label("Synthetic Sentience on Demand -\n" + 
						"a Strategic Commodity Resource");
				Label text1 = new Label("Evolutionary computing platforms,"
						+ " self evolving neural networks and “artificial brains”,"
						+ " hyper networks of systems  where the networks themselves "
						+ "are biological systems, evolutionary in nature, defensive, "
						+ "adaptive.   Emanating from this realm is a form of synthetic "
						+ "sentience, the capacity for perceiving, a sense of self, the "
						+ "threshold of emergent consciousness.   This is not “AI” "
						+ "(artificial intelligence) in the traditional sense,"
						+ " but something very different, more in the realms of "
						+ "artificial life, growing and evolving its way into every facet of current and future life.");
				text1.setWrapText(true);
				try {
					ImageView image1 = new ImageView(new Image(new FileInputStream("src/img/link3.jpg")));
					
					VBox cont1 = new VBox(10, title1, text1, image1); 
					
					body.getChildren().add(cont1);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (id.equals("link4")) {
				body.getChildren().clear();
				Label title1 = new Label("The NanoBiology Imperative");
				Label text1 = new Label("All living things are nanofoundries.  "
						+ "Nature has perfected the artform of biological nanotechnology"
						+ " for billions of years.  Now, an emerging technology domain is "
						+ "poised to present a toolkit from which new lifeforms can be crafted, "
						+ "the inner molecular workings of living cells can be directly manipulated,"
						+ " even aging may be treated not as a disease, but as a reversable pathology.  "
						+ " The very definition of life itself is perched at the edge of the next great "
						+ "revolution in medicine: nanobiology.   Currently existing are technologies and "
						+ "applications in the arenas of biomolecular components and biocompatible surfaces "
						+ "integrated into microscale systems, implantable biochip devices, "
						+ "synthetically engineered quasi-viral components, modified DNA, "
						+ "structured proteomics,  pseudoproteins, biomolecular \"devices\".  "
						+ "What is coming are artificially engineered organelles and cells, "
						+ "technologies which combine organic and inorganic materials "
						+ "and substrates into integrated nanoscale systems, "
						+ "\"biomolecular prosthetics\", and intra-cellular "
						+ "modification strategies which will redefine the very "
						+ "essense of what is commonly referred to as life.");
				text1.setWrapText(true);
				try {
					ImageView image1 = new ImageView(new Image(new FileInputStream("src/img/link4.jpg")));
					
					VBox cont1 = new VBox(10, title1, text1, image1); 
					
					body.getChildren().add(cont1);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (id.equals("link5")) {
				body.getChildren().clear();
				Label title1 = new Label("The Internet as an Organism :\n" + 
						"Emergent Human / Internet Symbiosis");
				Label text1 = new Label("We as a human species, are adapting, in rapid succession, "
						+ "to a series of evolutionary increments, contrived and accelerated by "
						+ "our own collective efforts, one of the outcomes of which is the"
						+ " \"spawning\" of synthetic intelligences, and their respective "
						+ "modes of communication, and our adaptation to an operational ecology "
						+ "with which even now we are symbiotically, and irreversably connected.");
				text1.setWrapText(true);
				try {
					ImageView image1 = new ImageView(new Image(new FileInputStream("src/img/link5.jpg")));
					
					VBox cont1 = new VBox(10, title1, text1, image1); 
					
					body.getChildren().add(cont1);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
	}
}
