
package hiber.dao;


import hiber.model.User;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      Query<User> query =
              sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.list();
   }

   @Override
   public User getUserByCar(String model, int series) {

      return sessionFactory
              .getCurrentSession()
              .createQuery(
                      "from User u where u.car.model = :model and u.car.series = :series",
                      User.class
              )
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }
   @Override
   public User findByEmail(String email) {
      return (User) sessionFactory.getCurrentSession()
              .createQuery("from User u where u.email = :email")
              .setParameter("email", email)
              .uniqueResult();
   }

   @Override
   public void delete(Long id) {
      User user = sessionFactory.getCurrentSession().get(User.class, id);
      if (user != null) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }
   @Override
   public User getById(Long id) {
      return sessionFactory.getCurrentSession().get(User.class, id);
   }

   @Override
   public void update(User user) {
      sessionFactory.getCurrentSession().update(user);
   }


   @Override
   @Transactional(readOnly = true)
   public User findByUsername(String username) {

      Session session = sessionFactory.getCurrentSession();

      try {
         return session.createQuery(
                         "from User u where u.email = :username", User.class)
                 .setParameter("username", username)
                 .getSingleResult();

         //u.email

      } catch (NoResultException e) {
         return null;
      }
   }
   @Override
   public void save(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
}

