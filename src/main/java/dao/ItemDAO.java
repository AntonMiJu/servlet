package dao;

import objects.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class ItemDAO {
    private static final String delete = "DELETE FROM ITEMS WHERE ID = ?";

    public Item save(Item item) {
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
            session.close();
            return item;
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Item update(Item item){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
            session.close();
            return item;
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void delete(long id){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(delete);
            query.setParameter(1, id);

            tr.commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Delete gone wrong");
            System.err.println(e.getMessage());
        }
    }

    public Item findById(long id) { // поиск продукта по id
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.get(Item.class,id);
        } catch (HibernateException e) {
            System.err.println("Finding by id gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
