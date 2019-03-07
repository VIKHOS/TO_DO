import {Injectable, Type} from '@angular/core';
import {TodoModule} from './todo.module';
import { HttpClient } from '@angular/common/http';
import {TodoModel} from '../todo-model';
import {TodoItemModel} from '../todo-item-model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TodoService {
  private apiUrl = 'http://localhost:8083';

  constructor(private httpClient: HttpClient) {
  }


  public getTodoList() {
    return this.httpClient.get<TodoModel[]>(`${this.apiUrl}/todos`);
  }

  public addItem(id: number, todoItem: TodoItemModel) {
    console.log(todoItem);
    let result = -1;
   this.httpClient.post(`${this.apiUrl}/todos/item/${id}`, todoItem).subscribe(
     (v) => result  = v as number
   );

   return (result === 0);

  }
}
