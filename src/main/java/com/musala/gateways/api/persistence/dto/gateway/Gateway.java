package com.musala.gateways.api.persistence.dto.gateway;

import com.musala.gateways.api.persistence.dto.device.Device;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
//import javax.persistence.EntityListeners;

@Getter
@Setter
@Entity
@Table(name="gateways")
//@EntityListeners(AuditingEntityListener.class)
public class Gateway implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotNull
    private String serialNumber;
    @NotNull
    private String name;
    @NotNull
    private String ipv4_Address;

    @OneToMany(mappedBy = "gateway", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Device> devices;
}
