package hiber.dao;

import hiber.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // 🔍 найти роль по имени (КЛЮЧЕВОЙ метод для Security)
    @Override
    public Role findByName(String name) {
        return getCurrentSession()
                .createQuery("from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    //
    @Override
    public void save(Role role) {
        getCurrentSession().save(role);
    }

    //
    @Override
    public List<Role> getAllRoles() {
        return getCurrentSession()
                .createQuery("from Role", Role.class)
                .list();
    }
}