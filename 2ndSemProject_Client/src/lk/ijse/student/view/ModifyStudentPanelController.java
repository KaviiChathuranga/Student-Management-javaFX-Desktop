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
import java.sql.SQLException;
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
import lk.ijse.student.controler.RegistrationControler;
import lk.ijse.student.controler.StudentControler;
import lk.ijse.student.controler.ValidationControler;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.model.AgentTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class ModifyStudentPanelController implements Initializable,Observer {
private String ssid="";
private String s="";
private final ObservableList<AgentTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
     @FXML
    private JFXTextField sNameTxt;

    @FXML
    private JFXTextField sAddressTxt;

    @FXML
    private JFXTextField sAgeTxt;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private JFXTextField telTxt;

    @FXML
    private JFXTextField mailTxt;

    @FXML
    private Label loadDate;

    @FXML
    private JFXTextField sBatchTxt1;

    @FXML
    private TableView myTable;

    @FXML
    private TableColumn sid;

    @FXML
    private TableColumn name;

    @FXML
    private TableColumn addresss;

    @FXML
    private TableColumn batch;

    @FXML
    private TableColumn nic;

    @FXML
    private TableColumn tel;

    @FXML
    private JFXButton removeOkBtn;

    @FXML
    private JFXButton updateOkBtn;

    @FXML
    private JFXTextField bdyTxt;

    @FXML
    private JFXTextField sidTxt;

    @FXML
    private JFXComboBox bidBox;

    @FXML
    private JFXComboBox subBox;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            UnicastRemoteObject.exportObject(this, 0);
            StudentControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(ModifyStudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     loadTable();
       ////////////////////////////////////////////////////////////////////////////////////////////////////////
      loadBatch();
    }    

    @Override
    public void update() throws Exception {
        //JOptionPane.showMessageDialog(null,"A Student Updated");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    void idAction(ActionEvent event) {
        
        
        String sid=sidTxt.getText();
     ////////////////////////////////////////////////////////////////////////////////////////////////////   
        
         
        try {
            RegistrationDto registrationDto=RegistrationControler.searcherAll(sid);
            
            if(!this.s.equalsIgnoreCase(sid)){
                RegistrationControler.release(this.s);
                    this.s=sid;
            }
            if (registrationDto != null) {
                boolean isReserved = StudentControler.reserveStudent(sid);
                if (isReserved) {
                        
                      try {
             RegistrationDto registrationDto1=RegistrationControler.searcherAll(sid);
             if (registrationDto1!=null) {
                // System.out.println("sc dg"+registrationDto.getStudentDto().getDob());
                 ssid=registrationDto1.getStudentDto().getSid();
                 sNameTxt.setText(registrationDto1.getStudentDto().getName());
                 sAddressTxt.setText(registrationDto1.getStudentDto().getAddress());
                 sAgeTxt.setText(""+registrationDto1.getStudentDto().getAge());
                 sBatchTxt1.setText(registrationDto1.getBatchDto().getName());
                 mailTxt.setText(registrationDto1.getStudentDto().getEmail());
                 telTxt.setText(registrationDto1.getStudentDto().getTel());
                 nicTxt.setText(registrationDto1.getStudentDto().getNic());
                 bdyTxt.setText(registrationDto1.getStudentDto().getDob());
             }else{
                    sNameTxt.setText("");
                    sAgeTxt.setText("");
                    sAddressTxt.setText("");
                    telTxt.setText("");
                    mailTxt.setText("");
                    nicTxt.setText("");
                    bdyTxt.setText("");
                 JOptionPane.showMessageDialog(null,"Sorry ! this Student not Found","",JOptionPane.WARNING_MESSAGE);
             }
         } catch (Exception ex) {
             Logger.getLogger(ModifyStudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
         }
                    
                } else {
                    sNameTxt.setText("");
                    sAgeTxt.setText("");
                    telTxt.setText("");
                    sAddressTxt.setText("");
                    batch.setText("");
                    mailTxt.setText("");
                    nicTxt.setText("");
                    bdyTxt.setText("");
                    
                    JOptionPane.showMessageDialog(null, "Reserved");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! No Student found");
                    sNameTxt.setText("");
                    sAgeTxt.setText("");
                    telTxt.setText("");
                    sAddressTxt.setText("");
                    batch.setText("");
                    mailTxt.setText("");
                    nicTxt.setText("");
                    bdyTxt.setText("");
                    
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
       
       
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    void updateBtnClick(MouseEvent event) {
        
        
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
        
        
        String name=sNameTxt.getText();
        int age=Integer.parseInt(sAgeTxt.getText());
        String address=sAddressTxt.getText();
        String tel=telTxt.getText();
        String email=mailTxt.getText();
        String nic=nicTxt.getText();
        String day=bdyTxt.getText();
        
        StudentDto studentDto=new StudentDto(ssid,name,address,age,day,nic,tel,email);
        
    try {
        boolean b=StudentControler.updateStudent(studentDto);
        if (b) {
            JOptionPane.showMessageDialog(null,"The Student Update Finished");
            sNameTxt.setText("");
            sAgeTxt.setText("");
            sAddressTxt.setText("");
            telTxt.setText("");
            mailTxt.setText("");
            nicTxt.setText("");
            bdyTxt.setText("");
        }else{
            JOptionPane.showMessageDialog(null,"Sorry ! this student can't update now");
        }
    } catch (Exception ex) {
        Logger.getLogger(ModifyStudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
    }
        }else{
             JOptionPane.showMessageDialog(null,"Sorry ! Please Input Correct details for Registration","",JOptionPane.WARNING_MESSAGE);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
        @FXML
    void batchClick(MouseEvent event) {
       
    }

    private void loadTable() {
       for (int i = 0; i < myTable.getItems().size(); i++) {
            myTable.getItems().clear();
        }
        
        sid.setCellValueFactory(new PropertyValueFactory<>("rid"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addresss.setCellValueFactory(new PropertyValueFactory<>("address"));
        batch.setCellValueFactory(new PropertyValueFactory<>("Batch"));
        nic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        tel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        
        myTable.setItems(data);
        try {
            List<RegistrationDto> list=RegistrationControler.getAll();
            for (RegistrationDto registrationDto : list) {
              // System.out.println("xsx"+registrationDto.getRegId()+" "+registrationDto.getStudentDto().getTel());
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

    private void loadBatch() {
       try {
            List<BatchDto>list=BatchControler.getAll();
            for (BatchDto batchDto : list) {
                
                bidBox.getItems().addAll(batchDto.getName());
                                        
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    void batchAction(ActionEvent event) {
         String batch=""+bidBox.getValue();
    try {
        BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
        
      
       sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
       name.setCellValueFactory(new PropertyValueFactory<>("name"));
       addresss.setCellValueFactory(new PropertyValueFactory<>("address"));
       this.batch.setCellValueFactory(new PropertyValueFactory<>("Batch"));
       nic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
       tel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
       
       myTable.setItems(data);  
       List<RegistrationDto>l1=RegistrationControler.getBatchStudent(batchDto.getBid());
        if (l1==null) {
            
        }else{
        for (RegistrationDto registrationDto : l1) {
            AgentTable a=new AgentTable();
            a.setRid(registrationDto.getRegId());
            a.setName(registrationDto.getStudentDto().getName());
            a.setAddress(registrationDto.getStudentDto().getAddress());
            a.setBatch(registrationDto.getBatchDto().getName());
            a.setNIC(registrationDto.getStudentDto().getNic());
            a.setTEL(registrationDto.getStudentDto().getTel());
            data.add(a);
        }
        }
       
        
        
    } catch (Exception ex) {
        Logger.getLogger(ModifyStudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
    }

    
}
