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
        model.addAttribute("Users",users);
        return "Users/index";

    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        UsersDto UsersDto=new UsersDto();
        model.addAttribute("UsersDto",UsersDto);

        return "Users/CreateUsers";
    }

     @PostMapping("/create")
     public String createUsers(@Valid @ModelAttribute UsersDto UsersDto,BindingResult result) 
     {
        
         if(UsersDto.getEmail().isEmpty())
         {
             result.addError(new FieldError ("UsersDto","email","the email is required"));
         }
         if(result.hasErrors())
         {
             return "Users/CreateUsers";
         }

         Users Users=new Users();
         Users.setName(UsersDto.getName());
         Users.setPhone(UsersDto.getPhone());
         Users.setEmail(UsersDto.getEmail());
         Users.setAddress(UsersDto.getAddress());
         Users.setDescription(UsersDto.getDescription());
    
         repo.save(Users);


         return "redirect:/Users";
     }

     @GetMapping("/edit")
     public String showEditpage(Model model,@RequestParam int id)
      {
     try{
           Users Users=repo.findById(id).get();
           model.addAttribute("Users",Users);

           UsersDto UsersDto=new UsersDto();
           UsersDto.setName(Users.getName());
           UsersDto.setEmail(Users.getEmail());
           UsersDto.setPhone(Users.getPhone());
           UsersDto.setAddress(Users.getAddress());
           UsersDto.setDescription(Users.getDescription());
           
           model.addAttribute("userDto", UsersDto);
        }
    catch(Exception ex){
        System.out.println("Exception: "+ex.getMessage());
        return "redirect:/Users";
     
   }

    return "Users/EditUser";
 }

     
    
}
