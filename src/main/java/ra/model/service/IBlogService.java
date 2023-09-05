package ra.model.service;

import org.springframework.stereotype.Service;
import ra.model.dto.BlogDto;
import ra.model.entity.Blog;

import java.util.List;

@Service
public interface IBlogService {
    List<Blog> findAll();

    Blog findById(Long id);

    void save(BlogDto b);

    void delete(Long id);
}
