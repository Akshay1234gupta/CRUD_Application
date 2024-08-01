package com.vayam.CRUDoperation.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vayam.CRUDoperation.model.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
    
}
