/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Sreehari Rajan
 */
public class TaskStatusModel {
    private SimpleStringProperty empid;
            private SimpleStringProperty empremarks;
            private SimpleBooleanProperty empstatus;
            private SimpleBooleanProperty empverified;
            
            public TaskStatusModel(String empid, String empremarks,Boolean empstatus,Boolean empverified) {
                this.empid = new SimpleStringProperty(empid);
                this.empremarks = new SimpleStringProperty(empremarks);
                this.empstatus = new SimpleBooleanProperty(empstatus);
                this.empverified = new SimpleBooleanProperty(empverified);

            }
            
            public String getEmpid() {
                return empid.get();
            }

            public void setEmpid(String empid) {
                this.empid = new SimpleStringProperty(empid);
            }

            public String getEmpremarks() {
                return empremarks.get();
            }

            public void setEmpremarks(String empremarks) {
                this.empremarks = new SimpleStringProperty(empremarks);
            }
             public Boolean getEmpstatus() {
                return empstatus.get();
            }

            public void setEmpstatus(Boolean empstatus) {
                this.empstatus = new SimpleBooleanProperty(empstatus);
            }
            public Boolean getEmpverified() {
                return empverified.get();
            }

            public void setEmpverified(Boolean empverified) {
                this.empverified = new SimpleBooleanProperty(empverified);
            }
}
