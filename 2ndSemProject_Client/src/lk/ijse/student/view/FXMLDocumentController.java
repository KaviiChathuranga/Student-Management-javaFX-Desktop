/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.transitions.JFXFillTransition;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.student.observer.Observer;


/**
 *
 * @author Kavindu
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private JFXButton regBtn;

    @FXML
    private AnchorPane middleAnchorPane;
    
    @FXML
    private JFXHamburger hamburg;
   
    @FXML
    private Label headLabl;
    
    @FXML
    private JFXButton modifyBtn;
    
    @FXML
    private JFXButton examBtn;
    
    @FXML
    private JFXButton attBtn;
    
    @FXML
    private JFXButton paymentBtn;
    
        @FXML
    private JFXButton searchStudentBtn;
        @FXML
    private ImageView image;
        @FXML
    private JFXSlider slider2;
        @FXML
    private JFXSlider slider1;
        @FXML
    private JFXSlider slider3;
        @FXML
    private JFXButton logoutBtn;

        @FXML
    private Label timeLbl;
        @FXML
    private Label dateLbl;
        @FXML
    private JFXButton ResultBtn;



        Node s;

///////////////////////////////////////////////////////////////////////////////////////////
        
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        headLbl();
        imagePlay();
        loadImage();
        loadTime();
        
        sliderSet();
           
    }  
    ////////////////////////////////////////////////////////////////////////////////////////
    
       @FXML    
    void clickReg(ActionEvent event) {
        
        if (s!=null) {
            middleAnchorPane.getChildren().remove(s);
        }
        try {
            s=FXMLLoader.load(getClass().getResource("StudentPanel.fxml"));
            JFXFillTransition fXFillTransition=new JFXFillTransition();
            fXFillTransition.setDuration(Duration.millis(7000));
            middleAnchorPane.getChildren().add(s);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void clickHam(MouseEvent event) {
  
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void modifyBtnClick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);

             }
             try {
                 s=FXMLLoader.load(getClass().getResource("ModifyStudentPanel.fxml"));
                 //JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    //////////////////////////////////////////////////////////////////////////////////
    
       @FXML
    void paymentBtnClick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);
             }
             try {
                 s=FXMLLoader.load(getClass().getResource("Payment.fxml"));
                // JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
       
        @FXML
    void searchBtnClick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);
             }
             try {
                 s=FXMLLoader.load(getClass().getResource("Search.fxml"));
                // JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void attBtnClick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);
             }
             try {
                 s=FXMLLoader.load(getClass().getResource("Attendance.fxml"));
                // JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void logOutBtnClick(ActionEvent event) {
     
        AnchorPane box;
        try {
            box = FXMLLoader.load(getClass().getResource("/lk/ijse/student/view/Login.fxml"));
              FadeTransition fade=new FadeTransition(Duration.millis(2000), box);
                fade.setFromValue(0);
                fade.setToValue(0.95);
                fade.setCycleCount(1);
                fade.play();
                
                    SpalshScreenTest screenTest=new SpalshScreenTest();
                    Stage stage=new Stage(StageStyle.UNDECORATED); 
                    screenTest.start(stage);        
                    ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
                    
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////

    private void loadTime() {
         Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
                        Calendar time = Calendar.getInstance();
                        
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        
                        timeLbl.setText(simpleDateFormat.format(time.getTime()));
         }),
                    new KeyFrame(Duration.seconds(1))
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            
            Date d=new Date();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYY/MM/dd");
            dateLbl.setText(simpleDateFormat1.format(d));
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////

    private void loadImage() {
       Image imageIcon=new Image("lk/ijse/student/assets/68523225-student-wallpapers.jpg");
        image.setImage(imageIcon);
        
        Image regImage=new Image("lk/ijse/student/assets/Add User Male_40px.png");
        regBtn.setGraphic(new ImageView(regImage));
        
        Image modiImage=new Image("lk/ijse/student/assets/Edit Property_40px.png");
        modifyBtn.setGraphic(new ImageView(modiImage));
        
        Image paymentImage=new Image("lk/ijse/student/assets/Coins_40px.png");
        paymentBtn.setGraphic(new ImageView(paymentImage));
        
        Image attImage=new Image("lk/ijse/student/assets/Thumb Up_40px.png");
        attBtn.setGraphic(new ImageView(attImage));
        
        Image examImage=new Image("lk/ijse/student/assets/Hand With Pen_40px.png");
        examBtn.setGraphic(new ImageView(examImage));
        
        Image searchImage=new Image("lk/ijse/student/assets/Search_40px.png");
        searchStudentBtn.setGraphic(new ImageView(searchImage));
        
        Image logOutImage=new Image("lk/ijse/student/assets/Logout Rounded Up_30px.png");
        logoutBtn.setGraphic(new ImageView(logOutImage));
        
        Image resultImage=new Image("lk/ijse/student/assets/Graduation Cap_40px.png");
        ResultBtn.setGraphic(new ImageView(resultImage));
        
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////

    private void imagePlay() {
      
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.seconds(2.2));
        transition2.setNode(image);
        transition2.setToX(+245.5); 
        
        transition2.play();
        
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////

    private void headLbl() {
       JFXFillTransition fXFillTransition=new JFXFillTransition();
        fXFillTransition.setDuration(Duration.millis(4000));
        fXFillTransition.setRegion(headLabl); 
        
        fXFillTransition.setFromValue(Color.INDIGO);
        fXFillTransition.setFromValue(Color.BLACK);
        fXFillTransition.setToValue(Color.INDIGO);
        fXFillTransition.setFromValue(Color.CORAL);
        
        fXFillTransition.setToValue(Color.BLACK );
        
        fXFillTransition.play();
           
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////
    
      @FXML
    void resultBtnClick(ActionEvent event) {
         if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);
             }
             try {
                 s=FXMLLoader.load(getClass().getResource("Result.fxml"));
                // JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////
    
       @FXML
    void examBtnclick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);
             }
             try {
                 s=FXMLLoader.load(getClass().getResource("Exam.fxml"));
                // JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////

    private void sliderSet() {
         
   
//             FadeTransition ft = new FadeTransition(Duration.millis(4000), slider1);
//             
//             ft.setFromValue(00);
//             slider1.setValue(50);
//             ft.setToValue(85);
//             ft.setCycleCount(Timeline.INDEFINITE);
//             ft.setAutoReverse(true);
//             ft.play();  


// new Thread(() -> {
//     
//          slider1.setValue(50);
//          slider1.setValue(100);
//        }).start();
           
    }
    
}
