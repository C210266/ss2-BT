package ra.model.repository;

import org.springframework.stereotype.Repository;
import ra.model.entity.Blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BlogRepository implements IBlogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> typed = entityManager.createQuery("select b from Blog as b", Blog.class);
        return typed.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        TypedQuery<Blog> query = entityManager.createQuery("select b from Blog as b where  b.id=:id", Blog.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog b) {
        if (b.getId() == null) {
            entityManager.persist(b);
        } else {
            entityManager.merge(b);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
