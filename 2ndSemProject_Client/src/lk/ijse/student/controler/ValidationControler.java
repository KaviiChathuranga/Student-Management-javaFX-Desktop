/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

/**
 *
 * @author Kavindu
 */
public class ValidationControler {
    public static boolean setTelNumber(String telNumber) throws Exception {
        if(telNumber.matches(("\\d{3}-\\d{7}"))){
            return true;
        }else{
            return false;
        }
    }
    public static boolean setNIc(String nic) throws Exception {
        if(nic.matches(("^[0-9]{9}[vVxX]$"))){
            return true;
        }else{
            return false;
        }
    }
    public static boolean setEmail(String email) throws Exception {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
}





