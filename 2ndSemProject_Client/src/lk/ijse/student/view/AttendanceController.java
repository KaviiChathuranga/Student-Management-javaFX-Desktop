/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.AttnendanceControler;
import lk.ijse.student.controler.BatchControler;
import lk.ijse.student.controler.Batch_Teacher_Controler;
import lk.ijse.student.controler.RegistrationControler;
import lk.ijse.student.controler.SubjectControler;
import lk.ijse.student.dto.AttendanceDto;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.model.AttendanceTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class AttendanceController implements Initializable,Observer {
private String sub="";
private final ObservableList<AttendanceTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
       
    @FXML
    private TableView attTable;
    
    @FXML
    private Label countLbl;

    @FXML
    private JFXTextField sidTxt;

    @FXML
    private JFXComboBox batchCMB;

    @FXML
    private JFXTextField dayTxt;

    @FXML
    private JFXButton attBtn;

    @FXML
    private JFXTimePicker timeTxt;

    @FXML
    private JFXComboBox subCMB;

    @FXML
    private TableColumn ridCOL;

    @FXML
    private TableColumn nameCOL;

    @FXML
    private TableColumn timeCOL;

    @FXML
    private TableColumn batchCOL;

    @FXML
    private TableColumn subjectCOL;

    @FXML
    private TableColumn dateCOL;

    @FXML
    void attBtnClick(MouseEvent event) {
    try {
        String sid=sidTxt.getText();
        String batch =(String) batchCMB.getValue();
        String subject=(String) subCMB.getValue();
        String time=timeTxt.getValue().toString();
        String date=dayTxt.getText();
        
        int id=0;
        List<AttendanceDto>list=AttnendanceControler.getAll();
        if (list==null) {
            id=0;
        }else{
            for (AttendanceDto attendanceDto : list) {
                id=Integer.parseInt(attendanceDto.getAid())+1;
            }
        }
        
        try {
            RegistrationDto registrationDto=RegistrationControler.searcherAll(sid);
            if (registrationDto!=null) {
                SubjectDto subjectDto=SubjectControler.searchSubjectName(subject);
                BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
                System.out.println("batch= "+batchDto.getBid());
                AttendanceDto attendanceDto=new AttendanceDto(""+id,registrationDto,batchDto,subjectDto,date,time);
                
                boolean b=AttnendanceControler.add(attendanceDto);
                if (b) {
                    JOptionPane.showMessageDialog(null,"Good Attendance Marked");
                sidTxt.setText("");
               
                }else{
                     JOptionPane.showMessageDialog(null,"Sorry ! This Attendance Can't Mark","",JOptionPane.WARNING_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null,"Sorry ! This Student Not Registered Yet","",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } catch (Exception ex) {
        Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            UnicastRemoteObject.exportObject(this, 0);
            AttnendanceControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadAttendance();
        loadBatch();
        loadDate();
//        setSubjects();
    }    

    @Override
    public void update() throws Exception {
       loadAttendance();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
     @FXML
    void batchAction(ActionEvent event) {
         String batch=""+batchCMB.getValue();
        try {
            BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
            List<Batch_SubjectDTO>list=Batch_Teacher_Controler.search(batchDto.getBid());
            
            
            if (list!=null) {
                
            for (Batch_SubjectDTO batch_SubjectDTO : list) {
                
                subCMB.getItems().addAll(batch_SubjectDTO.getSubject().getName());
                                        
            }
            }
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAttendance() {
        
         for (int i = 0; i < attTable.getItems().size(); i++) {
            attTable.getItems().clear();
         }
         
       ridCOL.setCellValueFactory(new PropertyValueFactory<>("rid"));
       nameCOL.setCellValueFactory(new PropertyValueFactory<>("student"));
       timeCOL.setCellValueFactory(new PropertyValueFactory<>("time"));
       batchCOL.setCellValueFactory(new PropertyValueFactory<>("batch"));
       subjectCOL.setCellValueFactory(new PropertyValueFactory<>("subject"));
       dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
       
        attTable.setItems(data);
        List<AttendanceDto> list = null;
        
    try {
        list = AttnendanceControler.getAll();
        for (AttendanceDto attendanceDto : list) {
            AttendanceTable at=new AttendanceTable();
            at.setRid(attendanceDto.getRegistrationDto().getRegId());
            at.setStudent(attendanceDto.getRegistrationDto().getStudentDto().getName());
            at.setTime(attendanceDto.getTime());
            at.setBatch(attendanceDto.getBatchDto().getName());
            at.setSubject(attendanceDto.getSubjectDto().getName());
            at.setDate(attendanceDto.getDate());
            data.add(at);
         }
    } catch (Exception ex) {
        Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void loadBatch() {
         try {
            List<BatchDto>list=BatchControler.getAll();
            for (BatchDto batchDto : list) {
                
                batchCMB.getItems().addAll(batchDto.getName());
                                        
            }
        } catch (Exception ex) {
            Logger.getLogger(TeacherRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

      @FXML
    void subAction(ActionEvent event) {
        String subName=""+subCMB.getValue();
        
        
         try {
            SubjectDto subjectDto=SubjectControler.searchSubjectName(subName);
            

            if(!this.sub.equalsIgnoreCase(subjectDto.getSubId())){
                SubjectControler.releaseSubject(this.sub);
                    this.sub=subjectDto.getSubId();
            }
            
                boolean isReserved = SubjectControler.reserveSubject(subjectDto.getSubId());
                if (isReserved) {
                    
                    JOptionPane.showMessageDialog(null, "Reserved");
                   // subCMB.getItems().removeAll(0);
                }
            
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {
       Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String s="";
        dayTxt.setText(day + "/" + (month + 1) + "/" + year);
    }
    
}
