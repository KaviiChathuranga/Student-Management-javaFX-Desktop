/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.JFXFillTransition;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.student.view.model.AgentTable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lk.ijse.student.controler.BatchControler;
import lk.ijse.student.controler.RegistrationControler;
import lk.ijse.student.controler.StudentControler;
import lk.ijse.student.controler.ValidationControler;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.jasper.main.StartUp;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.email.Gmail;
import lk.ijse.student.view.model.BatchTable;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class StudentPanelController implements Initializable,Observer {
    Node s;
    private String imgPath;
    private final ObservableList<AgentTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * 
     */

       @FXML
    private TableView myTable;

    @FXML
    private TableColumn ridCOL;

    @FXML
    private TableColumn nameCOL;

    @FXML
    private TableColumn addresssCOL;

    @FXML
    private TableColumn batchCOL;

    @FXML
    private TableColumn nicCOL;

    @FXML
    private TableColumn telCOL;

    @FXML
    private JFXTextField sNameTxt;

    @FXML
    private JFXTextField sAddressTxt;

    @FXML
    private JFXTextField sAgeTxt;

    @FXML
    private JFXComboBox batchBox;

    @FXML
    private JFXDatePicker birthDateTxt;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private JFXTextField telTxt;

    @FXML
    private JFXTextField mailTxt;

    @FXML
    private JFXTextField feeTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private Label loadDate;

    @FXML
    private Label countLbl;
 
    @FXML
    private Label filechoose;
    
    @FXML
    private Label setPhoto;
    
    private int count=0;
      
    @FXML
    void saveBtnClick(ActionEvent event) {
        boolean nicCheck=false;
        boolean emailCheck=false;
        boolean telCheck=false;
        
        try {
            nicCheck=ValidationControler.setNIc(nicTxt.getText());            
            emailCheck=ValidationControler.setEmail(mailTxt.getText());
            telCheck=ValidationControler.setTelNumber(telTxt.getText());
              if (!nicCheck) {
                  nicTxt.setStyle("-fx-text-inner-color: red");
              }else{
                  nicTxt.setStyle("-fx-text-inner-color: black");
              }
              if (!emailCheck) {
                  mailTxt.setStyle("-fx-text-inner-color: red");
              }else{
                  mailTxt.setStyle("-fx-text-inner-color: black");
              }
              if (!telCheck) {
                  telTxt.setStyle("-fx-text-inner-color: red");
              }else{
                  telTxt.setStyle("-fx-text-inner-color: black");
              }
                
            
  
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (nicCheck && emailCheck && telCheck) {
       
        
        int id=0;
       try{
            
        List<StudentDto>list=StudentControler.getAll();
            if (list==null) {
                id=0;
            }else{
                for (StudentDto studentDto : list) {
                    id=Integer.parseInt(studentDto.getSid())+1;
                }
            }
      
        String name=sNameTxt.getText();
        String address=sAddressTxt.getText();
        int age=Integer.parseInt(sAgeTxt.getText());
        String batch=(String)batchBox.getSelectionModel().getSelectedItem();
        String today=loadDate.getText();
        String dob=birthDateTxt.getValue().toString();
        String nic=nicTxt.getText();
        String tel=telTxt.getText();
        String email=mailTxt.getText();
        double fee=Double.parseDouble(feeTxt.getText());
        
        BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
        StudentDto studentDto=new StudentDto(""+id,name,address,age,dob,nic,tel,email);
        RegistrationDto registrationDto=new RegistrationDto(""+id,batchDto,studentDto,today,fee);
        
            boolean b=RegistrationControler.addReg(registrationDto);
            if (b) {
                //JOptionPane.showMessageDialog(null,"A New Student Registered into the system ");
                
               // showGoodAlert();
                loadStudent();
                
                
/////////////////////////////////////////////////  Jasper /////////////////////////////////////////
             
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(StartUp.class.getResourceAsStream("/lk/ijse/student/jasper/report/Leaf_Red.jasper"));
                        HashMap<String, Object> parHashMap = new HashMap<>();
                        parHashMap.put("oid", sNameTxt.getText());
                        //parHashMap.put("unitPrice", nicTxt.getText());
                        parHashMap.put("custName", sAgeTxt.getText());
                        parHashMap.put("custTel", telTxt.getText());
                        parHashMap.put("qty", 55);

//                        parHashMap.put("startTime", telTxt.getText());
//                        parHashMap.put("endTime", nicTxt.getText());
//                        parHashMap.put("cheId", loadDate.getText());
//                        parHashMap.put("count", birthDateTxt.getValue().toString());

                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parHashMap, new JREmptyDataSource());
                        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
                        java.awt.Container container = jasperViewer.getContentPane();

                        JDialog jDialog = new JDialog();
                        jDialog.setContentPane(container);
                        jDialog.setSize(jasperViewer.getSize());
                        jDialog.setLocationRelativeTo(null);
                        jDialog.setVisible(true);
                   
                        clearText();
                        showGoodAlert();
                        
                        
//                        Gmail gmail=new Gmail();
//                        gmail.setMail(email, "you are registerd into Orient."); 
////////////////////////////////////////////////////////////////////////////////////////////////////

               
            }else{
                // showGoodAlert();
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }else{
            showAlert();
        }
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          try {
            UnicastRemoteObject.exportObject(this, 0);
            StudentControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       
       loadStudent();
       loadBatch();
       loadDate();
       countStudent();
       
    }    

    private void loadDate() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String s="";
        loadDate.setText(day + "/" + (month + 1) + "/" + year);
    }
    ///////////////////////////////////////////////////////////////////////////
    
       @FXML
    void fileChooseClick(MouseEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.showOpenDialog(stage);
//        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
//        chooser.addChoosableFileFilter(filter);
//      
//            File selectFile = chooser.getSelectedFile();
//            
//            String path = selectFile.getAbsolutePath();
//            this.imgPath = path;
//            setPhoto.setGraphic(new ImageView(path));

     
    }
    //////////////////////////////////////////////////////////////////////////

    @Override
    public void update() throws Exception {
        loadStudent();
    }

    private void loadStudent() {
      
        for (int i = 0; i < myTable.getItems().size(); i++) {
            myTable.getItems().clear();
        }
        
        ridCOL.setCellValueFactory(new PropertyValueFactory<>("rid"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addresssCOL.setCellValueFactory(new PropertyValueFactory<>("address"));
        batchCOL.setCellValueFactory(new PropertyValueFactory<>("Batch"));
        nicCOL.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        telCOL.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        
        myTable.setItems(data);
        try {
            List<RegistrationDto> list=RegistrationControler.getAll();
            for (RegistrationDto registrationDto : list) {
              
                AgentTable agentTable=new AgentTable();
                agentTable.setRid(registrationDto.getRegId());
                agentTable.setName(registrationDto.getStudentDto().getName());
                agentTable.setAddress(registrationDto.getStudentDto().getAddress());
                agentTable.setBatch(registrationDto.getBatchDto().getName());
                agentTable.setNIC(registrationDto.getStudentDto().getNic());
                agentTable.setTEL(registrationDto.getStudentDto().getTel());
                
                data.add(agentTable);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    
    private ImageIcon ResizeImage(String ImagePath) {
        ImageIcon myImage = new ImageIcon(ImagePath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance((int)setPhoto.getWidth(), (int)setPhoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);

        return image;


    }          

    private void loadBatch() {
      // options.removeAll(options);
     
      //batchBox.getItems().clear();
        try {
            List<BatchDto>list=BatchControler.getAll();
            for (BatchDto batchDto : list) {
                
                batchBox.getItems().addAll(batchDto.getName());
                                        
        }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void showGoodAlert() {
        javafx.scene.image.Image img = new javafx.scene.image.Image("/lk/ijse/student/assets/27292700_735719656627488_946698439_n.png");
                Notifications notification = Notifications.create()
                        .title("Congrats ! This Student Registerd  !")
                        .text("Checking is Successfully.")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked notification");
                            }
                        });
                notification.darkStyle();
                notification.show();
    }

    private void showAlert() {
         javafx.scene.image.Image img = new javafx.scene.image.Image("/lk/ijse/student/assets/27394172_735719689960818_214562347_n.png");
                Notifications notification = Notifications.create()
                        .title("OOPss ! Registeation Fail !")
                        .text("Checking is not Successfully.")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked notification");
                            }
                        });
                notification.darkStyle();
                notification.show();
    }

    private void clearText() {
        
        sNameTxt.setText("");
        sAddressTxt.setText("");
        sAgeTxt.setText("");
        nicTxt.setText("");
        telTxt.setText("");
        mailTxt.setText("");
        feeTxt.setText(""); 
        
    }

    private void countStudent() {
        int count=0;
        try {
            List<RegistrationDto> list=RegistrationControler.getAll();
            for (RegistrationDto registrationDto : list) {
                count++;
            }
            countLbl.setText(""+count); 
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
