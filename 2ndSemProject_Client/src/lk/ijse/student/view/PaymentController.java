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
import lk.ijse.student.controler.PaymentControler;
import lk.ijse.student.controler.RegistrationControler;
import lk.ijse.student.controler.SubjectControler;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.PaymentDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.service.custom.PaymentService;
import lk.ijse.student.view.model.PaymentTable;
import lk.ijse.student.view.model.ResultTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class PaymentController implements Initializable,Observer {
private String sid="";
private final ObservableList<PaymentTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label countLbl;
    @FXML
    private JFXComboBox monthBox;


    @FXML
    private JFXTextField sidTxt;

    @FXML
    private Label monthLbl;

    @FXML
    private JFXComboBox subjectBox;

    @FXML
    private JFXTextField paymentTxt;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private JFXComboBox batchBox;

    @FXML
    private TableView paymentTable;

    @FXML
    private TableColumn<?, ?> dateCOL;

    @FXML
    private TableColumn<?, ?> monthCOL;

    @FXML
    private TableColumn<?, ?> ridCOL;

    @FXML
    private TableColumn<?, ?> nameCOL;

    @FXML
    private TableColumn<?, ?> bidCOL;

    @FXML
    private TableColumn<?, ?> subCOL;

    @FXML
    private TableColumn<?, ?> feeCOL;
    
    @FXML
    private JFXTextField dateTXt;
    
    /////////////////////////////////////////////////////////////
    
    @FXML
    void paymentClick(MouseEvent event) {
        int id=0;
        String sid=sidTxt.getText();
        String batch=(String) batchBox.getValue();
        String sub=(String) subjectBox.getValue();
        String month1=monthLbl.getText();
        double fee=Double.parseDouble(paymentTxt.getText());
        String date=dateTXt.getText();
        String month2=(String)monthBox.getValue();
        
        try {
            List<PaymentDto>paymentDtos=PaymentControler.getAll();
            if (paymentDtos!=null) {
                for (PaymentDto paymentDto : paymentDtos) {
                    id=Integer.parseInt(paymentDto.getPid())+1;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            RegistrationDto registrationDto=RegistrationControler.searcherAll(sid);
            BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
            System.out.println("batch "+batchDto.getBid());
            SubjectDto subjectDto=SubjectControler.searchSubjectName(sub);
            
            PaymentDto paymentDto=new PaymentDto(""+id,registrationDto,batchDto,subjectDto,date,fee,month2);
            
            boolean b=PaymentControler.addPayment(paymentDto);
            if (b) {
                JOptionPane.showMessageDialog(null,"Payment Was Sucessfuly Added");
            }else{
                JOptionPane.showMessageDialog(null,"Sorry ! can't reach this payment method","",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            UnicastRemoteObject.exportObject(this, 0);
            PaymentControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       loadPayment();
       loadBatch();
       loadMonth();
       loadDate();
       loadSubject();
     //  loadPaymentCount();
    }    

    @Override
    public void update() throws Exception {
        loadPayment();
    }

    private void loadPayment()  {
    try {
        for (int i = 0; i < paymentTable.getItems().size(); i++) {
            paymentTable.getItems().clear();
//            countLbl.setText(""+i); 
        }
        
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("Date"));
        monthCOL.setCellValueFactory(new PropertyValueFactory<>("Month"));
        ridCOL.setCellValueFactory(new PropertyValueFactory<>("RID"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("Student"));
        bidCOL.setCellValueFactory(new PropertyValueFactory<>("Batch"));
        subCOL.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        feeCOL.setCellValueFactory(new PropertyValueFactory<>("Marks"));
        
        paymentTable.setItems(data);
        List<PaymentDto>list=null;
        
        List<PaymentDto>pds=PaymentControler.getAll();
        if (pds!=null) {
            for (PaymentDto pd : pds) {
                PaymentTable table=new PaymentTable();
                table.setDate(pd.getDate());
                table.setMonth(pd.getMonth());
                table.setRID(pd.getRegistrationDto().getRegId());
                table.setStudent(pd.getRegistrationDto().getStudentDto().getName());
                table.setBatch(pd.getBatchDto().getName());
                table.setSubject(pd.getSubjectDto().getName());
                table.setfee(Double.toString(pd.getFee()));
                data.add(table);
            }
        }else{
            
        }
    } catch (Exception ex) {
        Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void loadBatch() {
         try {
            List<BatchDto>list=BatchControler.getAll();
            for (BatchDto batchDto : list) {
                
                batchBox.getItems().addAll(batchDto.getName());
                                        
                        }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMonth() {
        monthBox.getItems().addAll("January");
        monthBox.getItems().addAll("February");
        monthBox.getItems().addAll("March");
        monthBox.getItems().addAll("April");
        monthBox.getItems().addAll("May");
        monthBox.getItems().addAll("June");
        monthBox.getItems().addAll("July");
        monthBox.getItems().addAll("Agaust");
        monthBox.getItems().addAll("September");
        monthBox.getItems().addAll("October");
        monthBox.getItems().addAll("November");
        monthBox.getItems().addAll("December");
    }

    private void loadDate() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String s="";
        dateTXt.setText(day + "/" + (month + 1) + "/" + year);
    }

    private void loadSubject() {
       
       
    }
    //////////////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void batchAction(ActionEvent event) {
        try {
            BatchDto batchDto=BatchControler.searchNameOfBatch(""+batchBox.getValue());
            List<Batch_SubjectDTO>list=Batch_Teacher_Controler.search(batchDto.getBid());
            
            if (list!=null) {
                
            for (Batch_SubjectDTO batch_SubjectDTO : list) {
               
                subjectBox.getItems().addAll(batch_SubjectDTO.getSubject().getName());
                                        
                 }
            }else{
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    
    

    /////////////////////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void studentTypeAction(ActionEvent event) {
        String sid=sidTxt.getText();
        
        try {
            RegistrationDto registrationDto=RegistrationControler.searcherAll(sid);
            
            if(!this.sid.equalsIgnoreCase(sid)){
                RegistrationControler.release(this.sid);
                    this.sid=sid;
            }
            if (registrationDto != null) {
                boolean isReserved = RegistrationControler.reserve(sid);
                if (isReserved) {
                    batchBox.requestFocus();
                    
                    
                } else {
                    paymentTxt.setText("");
                    monthLbl.setText("");
                    
                    
                    JOptionPane.showMessageDialog(null, "Student Reserved from Another Client !","",JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! No Student found");
                    sidTxt.setText("");
                    paymentTxt.setText("");
                    monthLbl.setText("");
                    
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
      @FXML
    void subjectAction(ActionEvent event) {
        try {
            String sid=sidTxt.getText();
            String bid=""+batchBox.getValue();
            String subId=""+subjectBox.getValue();
            int subjectId=0;
            int batchId=0;
            
            SubjectDto subjectDto=SubjectControler.searchSubjectName(subId);
            if (subjectDto!=null) {
                subjectId=Integer.parseInt(subjectDto.getSubId());
            }
            
            BatchDto batchDto=BatchControler.searchNameOfBatch(bid);
            if (batchDto!=null) {
                System.out.println("the batch is "+batchDto.getBid());
                batchId=Integer.parseInt(batchDto.getBid());
            }
            
            String month="";
            try {
                List<PaymentDto>paymentDtoList=PaymentControler.searchPayment((""+batchId), (""+subjectId), sid);
                if (paymentDtoList!=null) {
                    
                    for (PaymentDto paymentDto : paymentDtoList) {
                         month=paymentDto.getMonth();
                       //  monthLbl.setText(month); 
//                        System.out.println("month is "+month);
                    }
                    if (month.equals("February")) { 
                        monthLbl.setText(month); 
                    }
                    
                }else{
                    System.out.println("empty rows");
                }
                
            } catch (Exception ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////

    private void loadPaymentCount() {
    try {
        String sid=sidTxt.getText();
        String bid=""+batchBox.getValue();
        String subId=""+subjectBox.getValue();
        
        
        int subjectId=0;
        int batchId=0;
        int count=0;
        
        SubjectDto subjectDto=SubjectControler.searchSubjectName(subId);
        if (subjectDto!=null) {
            subjectId=Integer.parseInt(subjectDto.getSubId());
        }
        
        BatchDto batchDto=BatchControler.searchNameOfBatch(bid);
        if (batchDto!=null) {
           // System.out.println("the batch is "+batchDto.getBid());
            batchId=Integer.parseInt(batchDto.getBid());
        }
        String month=""+monthBox.getValue();
        try {
            List<PaymentDto>list=PaymentControler.checkCount((""+batchId), (""+subjectId), month);
            for (PaymentDto paymentDto : list) {
                count++;
            }
            countLbl.setText(""+count); 
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } catch (Exception ex) {
        Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    /////////////////////////////////////////////////////////////////////////
    
        @FXML
    void monthAction(ActionEvent event) {
        loadPaymentCount();
    }
    
}
