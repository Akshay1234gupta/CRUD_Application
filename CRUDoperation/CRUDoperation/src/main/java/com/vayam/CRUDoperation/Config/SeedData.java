// package com.vayam.CRUDoperation.Config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.vayam.CRUDoperation.Repositories.UserRepository;
// import com.vayam.CRUDoperation.model.Users;

// @Component
//  public class SeedData implements CommandLineRunner{


//      @Autowired
//      private UserRepository userrepo;


//      @Override
//      public void run(String... args) throws Exception 
//      {
//         Users acc01=new Users();
//         acc01.setName("akshay");
//         acc01.setEmail("akshay@gmail.com");
//         acc01.setAddress("khargone");
//         acc01.setPhone(12314213L);
//         acc01.setDescription("hello friends");
//         userrepo.save(acc01);     

//         Users acc02=new Users();
//         acc02.setName("aman");
//         acc02.setEmail("aman@gmail.com");
//         acc02.setAddress("indore");
//         acc02.setPhone(1421213L);
//         acc02.setDescription("hello friends");
//         userrepo.save(acc02);     
//      }
    
//  }

