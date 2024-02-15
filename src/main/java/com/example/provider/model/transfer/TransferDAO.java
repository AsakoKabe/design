package com.example.provider.model.transfer;

import com.example.provider.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;


public class TransferDAO {

    public Transfer findById(UUID id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(
                Transfer.class,
                id
        );
    }

    public void save(Transfer transfer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(transfer);
        tx1.commit();
        session.close();
    }

    public void update(Transfer transfer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(transfer);
        tx1.commit();
        session.close();
    }

    public void delete(Transfer transfer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(transfer);
        tx1.commit();
        session.close();
    }


    public List<Transfer> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Transfer> query = session.createQuery(" FROM Transfer");
        List<Transfer> result = query.getResultList();
        session.close();
        return result;

    }

}
