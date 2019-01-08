package com.fisuit00.SimpleForum.Repository;

import com.fisuit00.SimpleForum.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Dictionary;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByOrderByCreatedDesc();

    //@Query("SELECT t.id, t.title, t.created FROM Topic t ORDER BY Created DESC")
    List<WithOutPosts> findAllBy();
}