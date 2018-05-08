package pl.politechnika.beans.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerBean implements Serializable{
    static final long serialVersionUID = 1L;

    private Long id;

    private String discountCode;

    private String zip;

    private String name;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String phone;

    private String fax;

    private String email;

    private String creditLimit;
}
