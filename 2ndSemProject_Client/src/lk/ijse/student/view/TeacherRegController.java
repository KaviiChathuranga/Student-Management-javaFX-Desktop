/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.AttnendanceControler;
import lk.ijse.student.controler.SubjectControler;
import lk.ijse.student.controler.TeacherControler;
import lk.ijse.student.controler.ValidationControler;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.model.AgentTable;
import lk.ijse.student.view.model.TeacherTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class TeacherRegController implements Initializable,Observer {
private final ObservableList<TeacherTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField teacherNameTxt;

    @FXML
    private JFXTextField addressTxt;

    @FXML
    private JFXTextField telTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField ageTxt;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private TableView subTeacherTable;

    @FXML
    private TableColumn tidCOL;

    @FXML
    private TableColumn tNameCOL;

    @FXML
    private TableColumn telCOL;

    @FXML
    private TableColumn nicCOL;

    @FXML
    private TableColumn addressCOL;

    @FXML
    private TableColumn subIDCOL;

    @FXML
    private TableColumn subNameCOL;

    @FXML
    private JFXButton regBtn;

    @FXML
    private JFXButton subOnlyBtn;

    @FXML
    private JFXTextField subNameTxt;

    @FXML
    private JFXTextField typeTxt;

    @FXML
    private JFXComboBox dateCmb;

    @FXML
    void onlySubBtn(MouseEvent event) {
 
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    void regClick(MouseEvent event) {
        int tid=0;
        int subid=0;
        
          boolean nicCheck=false;
        boolean emailCheck=false;
        boolean telCheck=false;
        
        try {
            nicCheck=ValidationControler.setNIc(nicTxt.getText());            
            emailCheck=ValidationControler.setEmail(emailTxt.getText());
            telCheck=ValidationControler.setTelNumber(telTxt.getText());
              if (!nicCheck) {
                  nicTxt.setStyle("-fx-text-inner-color: red");
              }else{
                  nicTxt.setStyle("-fx-text-inner-color: black");
              }
              if (!emailCheck) {
                  emailTxt.setStyle("-fx-text-inner-color: red");
              }else{
                  emailTxt.setStyle("-fx-text-inner-color: black");
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
        
        ////////////////// add teacher //////////////////////
        
        String name=teacherNameTxt.getText();
        String address=addressTxt.getText();
        String tel=telTxt.getText();
        String email=emailTxt.getText();
        int age=Integer.parseInt(ageTxt.getText());
        
        String gender="";
            if (maleRadio.isSelected()) {
                gender="male";
            }else{
                gender="female";
            }
        String nic=nicTxt.getText(); 
        
        ////////////////// add subject //////////////////////
        
        String subName=subNameTxt.getText();
        String subType=typeTxt.getText();
        String date=(String) dateCmb.getValue(); 
        
      try {  
          
        List<SubjectDto>list2=SubjectControler.getAll();
        if (list2!=null) {
            for (SubjectDto subjectDto : list2) {
                subid=Integer.parseInt(subjectDto.getSubId())+1;
            }
        }
        
        List<TeacherDto>list1=TeacherControler.getAll();
        if (list1!=null) {
            for (TeacherDto teacherDto : list1) {
                tid=Integer.parseInt(teacherDto.getTid())+1;
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////
     
        TeacherDto teacherDto=new TeacherDto(""+tid,name,address,tel,gender,nic,email,age);
        SubjectDto subjectDto=new SubjectDto(""+subid, subName, teacherDto, subType, date);
        
        boolean b=SubjectControler.addSubject(subjectDto);
        if (b) {
            JOptionPane.showMessageDialog(null,"New Subject And Teacher Register Finished");
            
            teacherNameTxt.setText("");
            addressTxt.setText("");
            telTxt.setText("");
            emailTxt.setText("");
            ageTxt.setText("");
            nicTxt.setText("");
            subNameTxt.setText("");
            typeTxt.setText("");
            
        }else{
            JOptionPane.showMessageDialog(null,"The Registration was Failed !","",JOptionPane.WARNING_MESSAGE);
        }
        
    } catch (Exception ex) {
        Logger.getLogger(TeacherRegController.class.getName()).log(Level.SEVERE, null, ex);
    }
        }else{
            JOptionPane.showMessageDialog(null,"Sorry ! Please Input Correct details for Registration","",JOptionPane.WARNING_MESSAGE);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            UnicastRemoteObject.exportObject(this, 0);
            TeacherControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(TeacherRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dateCmb.setEditable(true);
        radio();
        loadTeacher();
        loadDate();
       
    }    

///////////////////////////////////////////////////////////////////////////////////////////////////////////
        @FXML
    void femaleClick(MouseEvent event) {
        maleRadio.setSelected(false);
    }

    @FXML
    void maleClick(MouseEvent event) {
       femaleRadio.setSelected(false);
    }

    private void radio() {
        maleRadio.setSelected(true);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void loadTeacher() {
        //subTeacherTable.getItems().remove(data); 
        
         for (int i = 0; i < subTeacherTable.getItems().size(); i++) {
            subTeacherTable.getItems().clear();
        }
         
        tidCOL.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        tNameCOL.setCellValueFactory(new PropertyValueFactory<>("Teacher"));
        telCOL.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        nicCOL.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        addressCOL.setCellValueFactory(new PropertyValueFactory<>("Address"));
        subIDCOL.setCellValueFactory(new PropertyValueFactory<>("SubID"));
        subNameCOL.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        
        subTeacherTable.setItems(data);
        
        try {
            List<SubjectDto> list=SubjectControler.getAll();
            for (SubjectDto subjectDto : list) {
            
                TeacherTable table=new TeacherTable();
                table.setTId(subjectDto.getTeacherDto().getTid());
                table.setTeacher(subjectDto.getTeacherDto().getName());
                table.setTel(subjectDto.getTeacherDto().getTel());
                table.setNic(subjectDto.getTeacherDto().getNic());
                table.setAddress(subjectDto.getTeacherDto().getAddress());
                table.setSubId(subjectDto.getSubId());
                table.setSubId(subjectDto.getName());
                
                data.add(table);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    void subCMBclick(ActionEvent event) {
        
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update() throws Exception {
        loadTeacher();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void loadDate() {
        dateCmb.getItems().addAll("Sunday");
        dateCmb.getItems().addAll("Monday");
        dateCmb.getItems().addAll("TuesDay");
        dateCmb.getItems().addAll("WendsDay");
        dateCmb.getItems().addAll("Thursday");
        dateCmb.getItems().addAll("Friday");
        dateCmb.getItems().addAll("SatDay");
    }
   
}
