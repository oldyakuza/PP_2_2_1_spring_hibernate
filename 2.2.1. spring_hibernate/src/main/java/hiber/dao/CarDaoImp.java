package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoImp implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car, User user) {
        car.setUser(user);
        sessionFactory.getCurrentSession().save(car);
    }
    @Override
    public User getOwner(String model, int series) {
        String hql = "select u from Car  c, User  u where  c.model = :model and c.series = :series and u.id = c.id";
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }
}
