package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.model.dto.BlogDto;
import ra.model.entity.Blog;
import ra.model.repository.IBlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id);
    }

    private String pathUpload = "C:\\Users\\Admin\\IdeaProjects\\ss2-JPA-BT\\src\\main\\webapp\\WEB-INF\\upload\\";

    @Override
    public void save(BlogDto b) {
        String imagUrl = null;
        if (!(b.getImage().isEmpty())) {
            imagUrl = b.getImage().getOriginalFilename();
            try {
                FileCopyUtils.copy(b.getImage().getBytes(), new File(pathUpload + imagUrl));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Blog blog = new Blog(b.getId(), b.getTitle(), b.getContent(), imagUrl, b.getCreateDate());
        blogRepository.save(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.delete(id);
    }
}
