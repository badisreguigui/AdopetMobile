package GUI;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartingPage extends Application {
    private double x;
    private double y;

    
    @Override
    public void start(Stage primaryStage) throws IOException {
         try {
            Parent root= FXMLLoader.load(getClass().getResource("AjoutOffre.fxml"));
            primaryStage.setTitle("Formulaire offre");
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
          
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - x);
                    primaryStage.setY(event.getScreenY() - y);
                }
            });

            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
        }
        
        
        /*Parent root= FXMLLoader.load(getClass().getResource("AjoutOffre.fxml"));
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Ajouter offre");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

