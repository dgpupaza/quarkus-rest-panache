package ro.appvalue.quarkus;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Table
@Entity(name = "members")
public class Member extends PanacheEntity {
    
    public String name;
    public String email;
    public String phone;

}