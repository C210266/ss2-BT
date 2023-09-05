package ra.model.repository;

import ra.model.entity.Blog;

import java.util.List;

public interface IBlogRepository {
    List<Blog> findAll();

    Blog findById(Long id);

    void save(Blog b);

    void delete(Long id);

}
