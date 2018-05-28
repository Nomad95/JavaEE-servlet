package pl.politechnika.repository.customer;

import pl.politechnika.beans.customer.CustomerEntity;

import java.util.List;

public interface CustomerRepository {

    List<CustomerEntity> getAll();

    CustomerEntity getOne(Long id);

    void insertCustomer(CustomerEntity customer);
}
