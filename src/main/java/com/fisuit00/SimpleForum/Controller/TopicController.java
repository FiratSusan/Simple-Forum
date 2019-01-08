package com.fisuit00.SimpleForum.Controller;

import com.fisuit00.SimpleForum.Model.Post;
import com.fisuit00.SimpleForum.Model.Topic;
import com.fisuit00.SimpleForum.Repository.TopicRepository;
import com.fisuit00.SimpleForum.Repository.WithOutPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepo;

    @GetMapping("topic/all")
    public List<WithOutPosts> GetTopics(){
        return topicRepo.findAllBy();
    }

    @GetMapping("topic")
    public List<Post> GetPostsForTopic(@RequestParam("Id") Long topicId){

        assert topicRepo.existsById(topicId) : "Provided topic id does not exists";

        return topicRepo.getOne(topicId).getPosts();
    }

    @PostMapping("topic")
    public void CreateTopic(@RequestBody Topic t){

        assert t != null : "Invalid topic provided";
        assert !t.getTitle().isEmpty() : "No title provided";
        assert t.getPosts().size() == 1 : "No or to many initial post provided";
        assert !t.getPosts().get(0).getContent().isEmpty() : "No text in initial post";

        long created = System.currentTimeMillis();
        t.setCreated(created);
        t.getPosts().get(0).setCreated(created);

        this.topicRepo.save(t);
    }

    @PutMapping("topic")
    public void AddPost(@RequestParam("Id") Long topicId, @RequestBody Post p){

        assert topicRepo.existsById(topicId) : "Provided topic id does not exists";
        assert !p.getContent().isEmpty() : "Provided post contains no content";
        assert p.getContent().length() < 256 : "Post is to long";

        p.setCreated(System.currentTimeMillis());

        Topic t = this.topicRepo.getOne(topicId);
        t.getPosts().add(p);
        this.topicRepo.save(t);
    }
}
