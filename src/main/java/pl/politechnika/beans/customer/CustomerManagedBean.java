package pl.politechnika.beans.customer;

import pl.politechnika.repository.customer.CustomerRepository;
import pl.politechnika.repository.customer.impl.CustomerRepositoryImpl;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.Objects;

//@SessionScoped
//@ManagedBean(name = "customerManagedBean")
public class CustomerManagedBean {

    private CustomerRepository customerRepository;

    private CustomerEntity currentCustomer;

    DataModel customers;

    private int selectedItemIndex;

    public CustomerManagedBean() {
        this.customerRepository = new CustomerRepositoryImpl();
    }

    public DataModel getCustomers() {
        if (Objects.isNull(customers)) {
            customers = new ListDataModel(customerRepository.getAll());
        }

        return customers;
    }

    public CustomerEntity getSelected() {
        if (Objects.isNull(currentCustomer)) {
            currentCustomer = new CustomerEntity();
            selectedItemIndex = -1;
        }

        return currentCustomer;
    }

    void recreateModel() {
        customers = null;
    }

    public String prepareView() {
        currentCustomer = (CustomerEntity) getCustomers().getRowData();

        return "view";
    }

    public String prepareList() {
        recreateModel();

        return "index";
    }
}
