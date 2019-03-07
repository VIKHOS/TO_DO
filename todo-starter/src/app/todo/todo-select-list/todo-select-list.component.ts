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


  constructor(private todoService: TodoService) {
    this.todoList = null;
    this.selectedList = -1;
  }

  ngOnInit() {
    this.todoService.getTodoList().subscribe(
      (res) => {this.todoList = res;
        if (this.todoList.length > 0) {
          this.selectedList = 0;
        }
      });
  }


  onSelect(event) {
    this.selectedList = event.srcElement.selectedIndex;
    console.log(this.selectedList);
  }
}
