package zeefxcalculator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zeeshan
 */
public class ZeeFXCalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CalculatorDesign.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ZeeFXCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("CalculatorStyle.css").toExternalForm());
        
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
