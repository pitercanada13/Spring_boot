package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    User getUserByCar(String bmw, int i);

    void delete(Long id);

    User findByUsername(String name);
    void register(User user);

    User getById(Long id);
    void update(User user);
}
