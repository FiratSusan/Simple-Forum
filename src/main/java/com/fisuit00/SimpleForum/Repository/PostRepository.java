package com.fisuit00.SimpleForum.Repository;

import com.fisuit00.SimpleForum.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
