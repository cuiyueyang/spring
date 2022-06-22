package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>Description: </p>
 * <p>@date 2022/6/8 16:20</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Entity
@Data
@Table( name ="dd" )
@EntityListeners(AuditingEntityListener.class)
public class Dd implements Serializable {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;

    private String dd;

}
