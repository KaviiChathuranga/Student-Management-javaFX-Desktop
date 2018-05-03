/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.reservation;

import java.util.HashMap;
import java.util.Map;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 * @param <T>
 */
public class ReservstionImpl <T extends SuperService>{
     private static class ResPage<T>{
        
        private T service;
        private boolean internally;

        public ResPage(T service, boolean internally) {
            this.service = service;
            this.internally = internally;
        }

        /**
         * @return the service
         */
        public T getService() {
            return service;
        }

        /**
         * @param service the service to set
         */
        public void setService(T service) {
            this.service = service;
        }

        /**
         * @return the internally
         */
        public boolean isInternally() {
            return internally;
        }

        /**
         * @param internally the internally to set
         */
        public void setInternally(boolean internally) {
            this.internally = internally;
        }
        
        
    }
    
    private Map<Object, ResPage<T>> resBook = new HashMap<>();
    
    public boolean reserve(Object key, T service, boolean internally){
        if (resBook.containsKey(key)){
            if (resBook.get(key).service == service){
                return true;
            }else{
                return false;
            }
        }else{
            resBook.put(key, new ResPage<>(service, internally));
            return true;
        }
    }
    
    public boolean isReservedInternally(Object key){
        if (resBook.containsKey(key)){
            return (resBook.get(key).internally);
        }else{
            return false;
        }
    }
    
    public boolean release(Object key){
        if (resBook.containsKey(key)){
            resBook.remove(key);
            return true;
        }else{
            return false;
        }
    }
}
