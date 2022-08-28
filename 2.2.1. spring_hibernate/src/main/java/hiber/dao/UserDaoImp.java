package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
  // @Autowired
   private final SessionFactory sessionFactory;

   UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
      CriteriaQuery<User> criteria = builder.createQuery(User.class);
      criteria.from(User.class);
      return sessionFactory.openSession().createQuery(criteria).getResultList();
   }
}
