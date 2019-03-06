import { Component, OnInit } from '@angular/core';
import {TodoService} from '../todo.service';
import {TodoModel} from '../../todo-model';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  todoList: TodoModel[];

  constructor(private todoService: TodoService) {
    this.todoList = null;
  }

  ngOnInit() {
    this.todoService.getTodoList().subscribe((res) => this.todoList = res);
  }

}
