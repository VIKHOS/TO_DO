import {Component, Input, OnInit} from '@angular/core';
import {TodoService} from '../todo.service';
import {TodoModel} from '../../todo-model';
import {TodoItemModel} from '../../todo-item-model';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  @Input() selectedList: TodoModel;
  private  errorMsg: String;

  constructor(private  todoService: TodoService) {

  }

  ngOnInit() {

  }
  
  completeItem(item: TodoItemModel) {

     this.todoService.updateItem(this.selectedList.id, item.id, !item.completed).subscribe(
      (v) => {
        item.completed = !item.completed;
      },
        (e) => {
          this.errorMsg = 'Error while updating item';
          console.log(e);
        }
    );
  }

  deleteItem(item: TodoItemModel) {
     this.todoService.deleteItem(this.selectedList.id, item.id).subscribe(
       (v) => {
         const indexItem = this.selectedList.items.indexOf(item);
         this.selectedList.items.splice(indexItem, 1);
       },
       (e) => {
         this.errorMsg = 'Error while deleting item';
         console.log(e);
       }
     );
  }

}
