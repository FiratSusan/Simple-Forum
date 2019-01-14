import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Verteilte Systeme Labor - Mini Forum';
  selectedTab: number = 0;

  url = 'http://localhost:8080/';
  headers: HttpHeaders = null;

  nt_title = '';
  nt_content = '';

  np_content = '';

  topics:ITopic[];

  constructor(private http:HttpClient){
    this.headers = new HttpHeaders();
    this.headers.append('Content-Type', 'application/json');
    this.Reload();
  }

  Reload(){
    this.http.get(this.url+'topic/all').subscribe((value) => {
      this.topics = value as ITopic[];
    });
  }

  createTopic(){
    let topic: ITopic = {} as ITopic;
    let post: IPost = {} as IPost;

    topic.title = this.nt_title;
    post.content = this.nt_content;
    topic.posts = [post];

    this.http.post(this.url+'topic', topic,{ headers: this.headers}).subscribe(() => {
      this.Reload();
      this.selectedTab = 0;
    });

  }

  createPost(t: ITopic, post:string){
    let p: IPost = {} as IPost;
    p.content = post;

    this.http.put(this.url+'topic?Id='+t.id, p, {headers: this.headers}).subscribe(
      value=>{
        this.np_content = '';
        this.Reload();
      }
    );
  }
}

interface ITopic {
  id:number;
  title:string;
  created:number;
  posts: IPost[];
}

interface IPost {
  id: number;
  content:string;
  created:number;
}
