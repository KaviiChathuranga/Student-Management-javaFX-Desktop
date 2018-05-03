/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kavindu
 */
public class DialogMain extends Application{
    
 
    
    @Override
    public void start(Stage stage) throws Exception {
         Parent root=FXMLLoader.load(getClass().getResource("Edit.fxml"));
         FadeTransition fade=new FadeTransition(Duration.millis(2000), root);
                fade.setFromValue(0);
                fade.setToValue(0.95);
                fade.setCycleCount(1);
                fade.play();
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
