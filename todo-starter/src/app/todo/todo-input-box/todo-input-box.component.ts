import { Component, OnInit } from '@angular/core';
import {TodoService} from '../todo.service';
import {TodoItemModel} from '../../todo-item-model';

@Component({
  selector: 'app-todo-input-box',
  templateUrl: './todo-input-box.component.html',
  styleUrls: ['./todo-input-box.component.css']
})
export class TodoInputBoxComponent implements OnInit {

  private selectedList = 0;
  public errorMsg = '';

  constructor(private  todoService: TodoService) {}

  ngOnInit() {
  }

  addItem(newItem: string) {
    // build object
    let todoItem = new TodoItemModel();
    todoItem.description = newItem;
    let res = this.todoService.addItem(this.selectedList, todoItem);
    if (!res) {
      this.errorMsg = 'Error while adding new item';
    }
  }

}
