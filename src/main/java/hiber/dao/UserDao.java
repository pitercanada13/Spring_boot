package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   User getUserByCar(String model, int series);
   List<User> listUsers();
   User findByEmail(String email);

   void delete(Long id);
   User getById(Long id);
   void update(User user);
   User findByUsername(String username);
   void save(User user);
}
