package org.jpwh;

import org.hibernate.Session;
import org.jpwh.entity.Message;
import org.jpwh.service.HibernateService;


public class MainApplication {
    public static void main(String[] args) {
        Session session = HibernateService.getSessionFactory().openSession();
        session.beginTransaction();
        Message message = new Message();
        message.setText("test");
        session.save(message);
        session.getTransaction().commit();
        HibernateService.shutdown();
    }
}
