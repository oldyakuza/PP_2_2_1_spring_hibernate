package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

@Repository
public class CarDaoImp implements CarDao {
    private final SessionFactory sessionFactory;

    CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }
    @Override
    public User getOwner(String model, int series) {
        Session session = sessionFactory.openSession();
        TypedQuery<User> query = session.
                createQuery("select u from Car c, User u where c.model = :model and c.series = :series and u.id = c.id",
                User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }
}
