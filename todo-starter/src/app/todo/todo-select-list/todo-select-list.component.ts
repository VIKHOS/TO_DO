import {Component, OnInit, ViewChild} from '@angular/core';
import {TodoModel} from '../../todo-model';
import {TodoService} from '../todo.service';
import {TodoListComponent} from '../todo-list/todo-list.component';

@Component({
  selector: 'app-todo-select-list',
  templateUrl: './todo-select-list.component.html',
  styleUrls: ['./todo-select-list.component.css'],
})
export class TodoSelectListComponent implements OnInit {

  todoList: TodoModel[];
  selectedList: number;
  errorMsg: String;


  constructor(private todoService: TodoService) {
    this.todoList = null;
    this.selectedList = -1;
    this.errorMsg = null;
  }

  ngOnInit() {
    this.fetchAllList();
  }

  fetchAllList() {
    this.todoService.getTodoList().subscribe(
      (res) => {this.todoList = res;
        if (this.todoList.length > 0) {
          this.selectedList = 0;
        }
      });
  }


  createTodoList(newItemName: String) {
    this.todoService.createTodoList(newItemName).subscribe(
      (value) => this.fetchAllList(),
      (e: ErrorEvent) => this.errorMsg = 'Error while creating new list: ' + e.message
    );
  }

  onSelect(event) {
    this.selectedList = event.srcElement.selectedIndex;
    console.log(this.selectedList);
  }
}
