package com.vayam.CRUDoperation.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vayam.CRUDoperation.Repositories.UserRepository;
import com.vayam.CRUDoperation.model.Users;
import com.vayam.CRUDoperation.model.UsersDto;

import jakarta.validation.Valid;





@Controller
@RequestMapping("/Users")
public class UserController {
    
    @Autowired
    private UserRepository repo;


    @GetMapping({"","/"})
    public String showUserlist(Model model)
    {
        List<Users> users=repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("users",users);
        return "Users/index";

    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        UsersDto usersDto=new UsersDto();
        model.addAttribute("usersDto",usersDto);

        return "Users/CreateUsers";
    }

      @PostMapping("/create")
      public String createUsers(@Valid @ModelAttribute UsersDto usersDto,BindingResult result) 
      {
        
          if(usersDto.getEmail().isEmpty())
          {
              result.addError(new FieldError ("usersDto","email","the email is required"));
          }
          if(result.hasErrors())
          {
              return "Users/CreateUsers";
          }

          Users users=new Users();
          users.setName(usersDto.getName());
          users.setPhone(usersDto.getPhone());
          users.setEmail(usersDto.getEmail());
          users.setAddress(usersDto.getAddress());
          users.setDescription(usersDto.getDescription());
    
          repo.save(users);


          return "redirect:/Users";
      }

     @GetMapping("/edit")
     public String showEditpage(Model model,@RequestParam Long id)
      {
       try{
              Users users=repo.findById(id).get();
              model.addAttribute("users",users);

              UsersDto usersDto=new UsersDto();
              usersDto.setName(users.getName());
              usersDto.setEmail(users.getEmail());
              usersDto.setPhone(users.getPhone());
              usersDto.setAddress(users.getAddress());
              usersDto.setDescription(users.getDescription());
           
              model.addAttribute("usersDto", usersDto);
          }
        catch(Exception ex)
        {
           System.out.println("Exception: "+ex.getMessage());
           return "redirect:/Users";
     
        }

        return "Users/EditUser";
      }

      @PostMapping("/edit")
      public String updateUser(Model model,@RequestParam Long id,@Valid @ModelAttribute UsersDto usersDto,BindingResult result)
      {
          try {
              Users users=repo.findById(id).get();
              model.addAttribute("users", users);

              if(result.hasErrors())
              {
                return "Users/EditUser";
              }
              
              users.setName(usersDto.getName());
              users.setEmail(usersDto.getEmail());
              users.setPhone(usersDto.getPhone());
              users.setAddress(usersDto.getAddress());
              users.setDescription(usersDto.getDescription());

              repo.save(users);


          } catch (Exception ex) {
            System.out.println("Exception "+ex.getMessage());
          }
          
          return "redirect:/Users";
        }

        @GetMapping("/delete")
        public String deleteUser(@RequestParam Long id) {

            try {

                Users users=repo.findById(id).get();
                
                repo.delete(users);

                
            } catch (Exception ex) {
                System.out.println("Exception "+ex.getMessage());

            }
            return "redirect:/Users";
        }
        
      

       

     
    
}
