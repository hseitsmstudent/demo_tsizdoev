package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * Crea
 * */
@Controller
public class PersonController extends WebMvcConfigurerAdapter{
     @Autowired
     private PersonRepository repo;

    @RequestMapping("/index")
    public String greeting( Model model) {
        model.addAttribute("repo", repo.findAll());
        return "index";
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/login").setViewName("login");
    }

    @GetMapping("/")
    public String showForm(Person person) {
        return "index";
    }

    @PostMapping("/index")
    public String checkPersonInfo(@Valid Person personForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        Person person = new Person();
        person.firstName=(String)bindingResult.getRawFieldValue("firstName");
        person.lastName=(String)bindingResult.getRawFieldValue("lastName");
        repo.save(person);
        model.addAttribute("repo", repo.findAll());
        return "index";
    }
}
