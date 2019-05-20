package com.file.management.dao;


import com.file.management.pojo.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoDao extends JpaRepository<Video,Integer> {
    //查询
   List<Video> findAll();

   Video findById(int id);
}

