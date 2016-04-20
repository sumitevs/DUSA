/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlImport;

/**
 *
 * @author Sumit_Saseendran
 */
public class MessageInfo {

        private String id;
        private String message;
        public MessageInfo() {            
        }
        public MessageInfo(String id, String message) {
            this.id = id;
            this.message = message;
        }
        public String getId() {
            return id;
        }
        public String getMessage() {
            return message;
        }
        
    }
