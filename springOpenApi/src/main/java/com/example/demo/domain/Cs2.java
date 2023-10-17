package com.example.demo.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Description: </p>
 * <p>Date: 2023/04/11 14:26</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Entity
@Data
@Table( name ="cs2" )
public class Cs2 {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    public String Id;

}
