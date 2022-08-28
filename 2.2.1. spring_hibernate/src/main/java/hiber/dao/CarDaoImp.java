package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

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
        CriteriaBuilder cBuilder = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<User> userCriteria = cBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);

        Subquery<Car> carSubQuery = userCriteria.subquery(Car.class);
        Root<Car> carRoot = carSubQuery.from(Car.class);

        Predicate modelPred = cBuilder.equal(carRoot.get("model"), model);
        Predicate seriesPred = cBuilder.equal(carRoot.get("series"), series);
        Predicate idPred = cBuilder.equal(carRoot.get("id"), userRoot.get("id"));

        carSubQuery.select(carRoot).where(cBuilder.and(modelPred, seriesPred, idPred));
        userCriteria.select(userRoot).where(cBuilder.exists(carSubQuery));
        TypedQuery<User> typedQuery = sessionFactory.openSession().createQuery(userCriteria);

        return typedQuery.getSingleResult();
    }
}
