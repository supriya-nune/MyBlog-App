package com.example.controller;

import com.example.model.Blog;
import com.example.model.Contact;
import com.example.service.BlogService;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/app/api")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Getter
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/public/home")

    public String loginForm(){

        return "home";
    }

    @GetMapping("/view-blogs")
    public String showBlog(Model model){
        List<Blog> blog = blogService.getAllBlogs();
        model.addAttribute("blogs",blog);
        return "view-blogs";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/add-contact")
    public String addContact(Model model){
        model.addAttribute("contact",new Contact());
        return "contact";
    }
    @GetMapping("/user/view-all-blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }




    @GetMapping("  /{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable long id) {
        Blog blog = blogService.getBlogById(id);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/add-blogs")
    @JsonFormat
    public ResponseEntity<String> createBlog(@ModelAttribute Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.ok("Blog added successfully");
    }


    @GetMapping("/add-blog")
    public String createBlogshow(Model model) {
        model.addAttribute("blog",new Blog());
        return "add-blog";
    }

    @PutMapping("/update-blog/{id}")
    public ResponseEntity<String> updateMobile(@PathVariable long id, @RequestBody Blog blog) {
        Blog updatedMobile = blogService.updateBlog(id, blog);
        if (updatedMobile != null) {
            return ResponseEntity.ok("Blog updated successfully");
        } else {
            return ResponseEntity.ok("!Sorry Blog not existed with this Id");
        }
    }


    @DeleteMapping("/delete-blog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable long id) {
        boolean deleted = blogService.deleteBlog(id);
        if (deleted) {
            return ResponseEntity.ok("Blog deleted successfully");
        } else {
            return ResponseEntity.ok("!Sorry Blog not existed with this Id");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    request.logout();
    Cookie[] list=request.getCookies();
     for (Cookie cookie:list
     ){
         Cookie cookie1=new Cookie(cookie.getName(),null);
         cookie1.setMaxAge(0);
         cookie1.setPath("/");
         response.addCookie(cookie1);
     }
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");
    request.getSession().invalidate();
    return new RedirectView("/app/api/public/home", true);
    }

}
