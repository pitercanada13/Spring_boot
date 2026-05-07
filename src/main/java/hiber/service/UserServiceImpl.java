package hiber.service;

import hiber.dao.UserDao;
import hiber.dao.RoleDao;
import hiber.model.User;
import hiber.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao,
                           RoleDao roleDao,
                           PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔥 СОЗДАНИЕ ПОЛЬЗОВАТЕЛЯ
    @Override
    @Transactional
    public void add(User user) {

        // шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // если роли не заданы — ставим ROLE_USER по умолчанию
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role role = roleDao.findByName("ROLE_USER");
            user.setRoles(Set.of(role));
        }

        userDao.add(user);
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDao.findByEmail(username);
    }
    @Override
    @Transactional
    public User getUserByCar(String model, int series) {
        return userDao.getUserByCar(model, series);
    }


    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userDao.getById(id);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void register(User user) {

        // 1. Проверка: есть ли уже такой email
        User existingUser = userDao.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }

        // 2. (ВАЖНО) Шифрование пароля
        // если используешь Spring Security:
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Сохранение
        userDao.save(user);
    }

    // 🔄 ОБНОВЛЕНИЕ
    @Override
    @Transactional
    public void update(User user) {

        User existing = userDao.getById(user.getId());

        //  обновляем
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        existing.setEmail(user.getEmail());
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setRoles(user.getRoles());

        userDao.update(existing);
    }
}