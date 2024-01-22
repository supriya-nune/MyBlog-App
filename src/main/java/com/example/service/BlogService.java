package com.example.service;

import com.example.model.Blog;
import com.example.repository.BlogRepository;
import com.example.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    /**
     * Method for fetch all blog details from database through repository
     * @return
     */
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    /**
     * Method for fetch blog details using id from database through repository
     * @param id
     * @return
     */
    public Blog getBlogById(long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.orElse(null);
    }

    /**
     * Method for add blog details from database through repository
     * @param blog
     * @return
     */
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /**
     * Method for update blog details from database through repository
     * @param id
     * @param blog
     * @return
     */
    public Blog updateBlog(long id, Blog blog) {
        if (blogRepository.existsById(id)) {
            blog.setId(id);
            return blogRepository.save(blog);
        } else {
            return null;
        }
    }

    /**
     * Method for delete blog details from database through repository
     * @param id
     * @return
     */
    public boolean deleteBlog(long id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
