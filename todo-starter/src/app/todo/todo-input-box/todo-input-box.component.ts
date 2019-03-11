import {Component, Input, OnInit} from '@angular/core';
import {TodoService} from '../todo.service';
import {TodoItemModel} from '../../todo-item-model';
import {TodoModel} from '../../todo-model';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-todo-input-box',
  templateUrl: './todo-input-box.component.html',
  styleUrls: ['./todo-input-box.component.css']
})
export class TodoInputBoxComponent implements OnInit {

  @Input() selectedList: TodoModel;
  public errorMsg = '';

  constructor(private  todoService: TodoService) {}

  ngOnInit() {
  }

  addItem(newItem: string) {
    // build object
    let todoItem = new TodoItemModel();
    todoItem.description = newItem;
    this.todoService.addItem(this.selectedList.id, todoItem).subscribe(
      (v) => {
        this.selectedList.items.push(JSON.parse(v as string) as TodoItemModel);
      },
      (e) => this.errorMsg = 'Error while adding new item' + e.toString()
    );

  }
}
