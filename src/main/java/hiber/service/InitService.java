package hiber.service;

import hiber.model.Car;
import hiber.model.Role;
import hiber.model.User;
import hiber.dao.RoleDao;
import hiber.dao.UserDao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitService {

    private final UserService userService;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserDao userDao,
                       RoleDao roleDao,
                       PasswordEncoder passwordEncoder,
                       UserService userService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Transactional
    public void init() {

        // ===== РОЛИ =====
        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        Role userRole = roleDao.findByName("ROLE_USER");

        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleDao.save(adminRole);
        }

        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleDao.save(userRole);
        }


        if (userDao.findByEmail("admin@mail.com") == null) {

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User();
            admin.setEmail("admin@mail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(roles);

            userDao.save(admin);
            System.out.println(" ADMIN создан ");
        }


        createUserIfNotExists("Ivan", "Ivanov", "ivan@mail.com",
                new Car("BMW", 5), userRole);

        createUserIfNotExists("Олег", "Корсаков", "oleg.ev.k@ya.ru",
                new Car("Нива", 3102), userRole);

        createUserIfNotExists("Лариса", "Петрова", "lara@gmail.ru",
                new Car("Matiz", 102), userRole);


        List<User> users = userService.listUsers();
        for (User u : users) {
            System.out.println("Id = " + u.getId());
            System.out.println("Email = " + u.getEmail());

            if (u.getCar() != null) {
                System.out.println("Car = " + u.getCar().getModel());
            } else {
                System.out.println("Car = NO CAR");
            }

            System.out.println();
        }
//        for (User u : users) {
//            System.out.println(u.getEmail() + " -> " + u.getCar().getModel());
//        }
    }

    private void createUserIfNotExists(String firstName,
                                       String lastName,
                                       String email,
                                       Car car,
                                       Role role) {

        if (userDao.findByEmail(email) == null) {

            Set<Role> roles = new HashSet<>();
            roles.add(role);

            User user = new User(firstName, lastName, email, car);

            user.setPassword("1234");
            user.setRoles(roles);

            userService.add(user);
        }
    }
}
//    private void createUserIfNotExists(String name, String lastName,
//                                       String email, Car car, Role role) {
//
//        if (userDao.findByEmail(email) == null) {
//
//            Set<Role> roles = new HashSet<>();
//            roles.add(role);
//
//
//            User user = new User(name, lastName, email, car);
//            user.setPassword(passwordEncoder.encode("1234")); // пароль для входа
//            user.setRoles(roles);
//
//            userService.add(user);
//        }
//    }\

//}
/*package hiber.service;

import hiber.model.Car;
import hiber.model.Role;
import hiber.model.User;
import hiber.dao.RoleDao;
import hiber.dao.UserDao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Component
public class InitService {

    private final UserService userService;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserDao userDao,
                       RoleDao roleDao,
                       PasswordEncoder passwordEncoder,
                       UserService userService ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    //@PostConstruct
    @Transactional
    public void init() {

        // создаём роли если их нет
        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        Role userRole = roleDao.findByName("ROLE_USER");

        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleDao.save(adminRole);
        }

        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleDao.save(userRole);
        }

        // создаём admin если нет
        //
        if (userDao.findByEmail("admin@mail.com") == null) {

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User();
            admin.setEmail("admin@mail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(roles);

            userDao.save(admin);

            System.out.println("admin@mail.com / admin");
        }


            userService.add(new User("Ivan", "Ivanov", "ivan@mail.com",
                    new Car("BMW", 5)));

            userService.add(new User("Олег", "Корсаков", "oleg.ev.k@ya.ru",
                    new Car("Нива", 3102)));

            userService.add(new User("Лариса", "Петрова", "lara@gmail.ru",
                    new Car("Matiz", 102)));

            User foundUser = userService.getUserByCar("BMW", 5);
            System.out.println(foundUser.getFirstName());

            List<User> users = userService.listUsers();
            for (User u : users) {
                System.out.println("Id = " + u.getId());
                System.out.println("First Name = " + u.getFirstName());
                System.out.println("Last Name = " + u.getLastName());
                System.out.println("Email = " + u.getEmail());
                System.out.println();
            }

    }
}

 */