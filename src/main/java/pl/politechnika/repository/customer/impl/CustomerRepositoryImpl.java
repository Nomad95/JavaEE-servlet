package pl.politechnika.repository.customer.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.config.hibernate.HibernateUtil;
import pl.politechnika.beans.customer.CustomerEntity;
import pl.politechnika.repository.customer.CustomerRepository;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateUtil.getSession();
        List<CustomerEntity> customers = (List<CustomerEntity>) session.createQuery("from CustomerEntity").list();
        session.close();
        return customers;
    }

    @Override
    public CustomerEntity getOne(Long id) {
        Session session = HibernateUtil.getSession();
        CustomerEntity customer = (CustomerEntity) session.get(CustomerEntity.class, id);
        session.close();
        return customer;
    }

    @Override
    public void insertCustomer(CustomerEntity customer) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.persist(customer);
        tx.commit();
        session.close();
    }
}
