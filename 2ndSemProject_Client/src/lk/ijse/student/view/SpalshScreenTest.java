 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Kavindu
 */
public class SpalshScreenTest extends Application{
    public static boolean isPlashLoaded=false;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    private void Splash(Stage mainStage){
        
        try { 
            Stage splashStage=new Stage(StageStyle.TRANSPARENT);
            Parent root=FXMLLoader.load(getClass().getResource("Splashh.fxml"));
            Scene scene=new Scene(root);
            splashStage.setScene(scene);
            mainStage.show();
            
            PauseTransition transition=new PauseTransition(javafx.util.Duration.millis(5000));
             transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    splashStage.close();
                    mainStage.show();
                }
     
            });
            transition.play();
            
            
        } catch (IOException ex) {
            Logger.getLogger(SpalshScreenTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) {
        launch(args); 
    }
    
}
