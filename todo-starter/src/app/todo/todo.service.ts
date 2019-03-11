import {Injectable, Type} from '@angular/core';
import {TodoModule} from './todo.module';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TodoModel} from '../todo-model';
import {TodoItemModel} from '../todo-item-model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TodoService {
  private apiUrl = '//localhost:8083';

  getHttpOptionsNoAuth(setResponseTypeForText = false): object {
    const headers: any = {};

    headers.headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    });

    if (setResponseTypeForText) {
      headers.responseType = 'text';
    }
    return headers;
  }

  constructor(private httpClient: HttpClient) {
  }

  public createTodoList(name: String) {
    return this.httpClient.post(`${this.apiUrl}/todos/${name}`, null );
  }

  public getTodoList() {
    return this.httpClient.get<TodoModel[]>(`${this.apiUrl}/todos`);
  }

  public addItem(id: number, todoItem: TodoItemModel) {

    return this.httpClient.post(`${this.apiUrl}/todos/item/${id}`, todoItem, this.getHttpOptionsNoAuth(true));
  }


  public updateItem(todoListId: number, todoItemId: number, completed: boolean) {
    return this.httpClient.put(`${this.apiUrl}/todos/item/${todoListId}/${todoItemId}/${completed}`, completed);
  }


  public deleteItem(todoListId: number, todoItemId: number) {
    return this.httpClient.delete(`${this.apiUrl}/todos/item/${todoListId}/${todoItemId}`);
  }
}
