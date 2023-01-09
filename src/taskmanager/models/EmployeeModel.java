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
public class EmployeeModel {
            private SimpleStringProperty id;
            private SimpleStringProperty name;
            private SimpleBooleanProperty added;
            
            public EmployeeModel(String id, String name,Boolean added) {
                this.id = new SimpleStringProperty(id);
                this.name = new SimpleStringProperty(name);
                this.added = new SimpleBooleanProperty(added);
            }
            
            public String getId() {
                return id.get();
            }

            public void setId(String id) {
                this.id = new SimpleStringProperty(id);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String name) {
                this.name = new SimpleStringProperty(name);
            }
             public Boolean getAdded() {
                return added.get();
            }

            public void setAdded(Boolean added) {
                this.added = new SimpleBooleanProperty(added);
            }
            
            
}
