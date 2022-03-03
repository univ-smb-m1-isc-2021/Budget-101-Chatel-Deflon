import { Component, OnInit } from '@angular/core';
import {ApiService, Todo} from "../../services/api.service";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  todos: Todo[] = [];

  constructor(
    private apiService: ApiService
  ) { }

  ngOnInit(): void {
    this.apiService.getTodos().subscribe(todos => {
      this.todos = todos;
    });
  }

  removeTodos(): void {
    this.todos = [];
  }

}
