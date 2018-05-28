package pl.politechnika.beans.customer;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customers")
@SequenceGenerator(name = "customers_sequence", sequenceName = "customers_seq", allocationSize = 1)
public class CustomerEntity implements Serializable{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "zip")
    private String zip;

    @Column(name = "name")
    private String name;

    @Column(name = "addressline1")
    private String addressLine1;

    @Column(name = "addressline2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "credit_limit")
    private String creditLimit;
}
