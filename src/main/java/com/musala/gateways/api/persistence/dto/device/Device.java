package com.musala.gateways.api.persistence.dto.device;

import com.musala.gateways.api.persistence.dto.gateway.Gateway;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name= "peripheral_devices")
//@EntityListeners(AuditingEntityListener.class)
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int uid;
    private String vendor;
    @CreatedDate
    private Date createdDate;
    private String status;

    @ManyToOne
    @JoinColumn(referencedColumnName = "serialNumber")
    private Gateway gateway;

}
