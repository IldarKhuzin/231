package example.dao;

import example.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("Select u From User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void removeById(Long id) {
        entityManager.createQuery("Delete User Where id =:id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User findById(Long id) {

        return entityManager.createQuery("From User Where id =:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
