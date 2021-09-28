// I worked on the homework assignment alone, using only course materials.
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
/**
 * Starbase GUI setup.
 * @author Mustaf
 * @version 1.0
 */
public class Starbase extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        BorderPane subRoot = new BorderPane();

        // Background Image
        Image image = new Image("file:space.png");
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty();
        imageView.fitWidthProperty();
        root.getChildren().add(imageView);

        // Heading
        VBox header = new VBox();
        header.setPadding(new Insets(10, 10, 10, 10));
        header.setAlignment(Pos.CENTER);
        Text text1 = new Text("Welcome to Starbase 1331!");
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
        header.getChildren().add(text1);
        subRoot.setTop(header);

        // Footer TextField
        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_CENTER);
        footer.setPadding(new Insets(10, 10, 10, 10));
        TextField textF = new TextField();
        footer.getChildren().add(textF);

        // Footer ComboBox
        ComboBox<Starship> cbox = new ComboBox<>();
        cbox.getItems().addAll(Starship.CONSTITUTION, Starship.DEFIANT, Starship.GALAXY, Starship.INTREPID);
        footer.getChildren().add(cbox);
        // Footer DockButton
        Button dock = new Button("Dock Ship");
        footer.getChildren().add(dock);
        dock.setStyle("-fx-background-color: white");

        // Footer ExitButton
        Button evac = new Button("Evacuate Docks");
        footer.getChildren().add(evac);
        evac.setStyle("-fx-background-color: red");
        subRoot.setBottom(footer);

        // Middle Section
        HBox mid = new HBox();
        mid.setAlignment(Pos.CENTER);
        mid.setSpacing(10);
        Button[] but = new Button[8];
        for (int i = 0; i < but.length; i++) {
            but[i] = new Button("EMPTY");
            but[i].setPrefSize(200, 200);
            mid.getChildren().add(but[i]);
        }
        for (int i = 0; i < but.length; i++) {
            int finalI = i;
            but[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    but[finalI].setText("EMPTY");
                    but[finalI].setStyle("-fx-background-color: white");
                }
            });
        }
        subRoot.setCenter(mid);
        evac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < but.length; i++) {
                    but[i].setText("EMPTY");
                    but[i].setStyle("-fx-background-color: white");
                }
            }
        });
        dock.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (textF.getText() != null && !(textF.getText().trim().isEmpty())) {
                    boolean success = false;
                    for (int i = 0; i < but.length; i++) {
                        if (but[i].getText().equals("EMPTY")) {
                            but[i].setText(textF.getText() + "\n" + cbox.getValue());
                            textF.clear();
                            but[i].setStyle("-fx-background-color: green");
                            success = true;
                            break;
                        }
                    }
                    if (!success) {
                        Stage stage = new Stage();
                        stage.setTitle("No Ports Available");
                        BorderPane bPane2 = new BorderPane();
                        bPane2.setPadding(new Insets(10, 10, 10, 10));
                        bPane2.setTop(new Label("              " + textF.getText()
                                + " did not receive docking clearance!"));
                        Button button = new Button("Continue");
                        bPane2.setBottom(button);
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                stage.close();
                            }
                        });
                        Scene scen = new Scene(bPane2, 400, 300);
                        stage.setScene(scen);
                        stage.show();
                    }
                }
            }
        });
        // Final Additions
        root.getChildren().add(subRoot);
        Scene scene = new Scene(root, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Starbase Command");
        primaryStage.show();

    }

    /**
     * Run the program.
     * @param args does the args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
