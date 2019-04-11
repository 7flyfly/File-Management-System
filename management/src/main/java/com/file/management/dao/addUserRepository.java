package com.file.management.dao;

import com.file.management.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface addUserRepository extends JpaRepository<User,String> {

}
