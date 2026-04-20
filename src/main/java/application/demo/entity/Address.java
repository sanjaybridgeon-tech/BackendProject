package application.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {

    private String name;
    private String phone;
    private String pincode;
    private String state;
    private String city;
    private String house;
    private String area;
    private String type;
}