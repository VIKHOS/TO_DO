import {Injectable, Type} from '@angular/core';
import {TodoModule} from './todo.module';
import { HttpClient } from '@angular/common/http';
import {TodoModel} from '../todo-model';

@Injectable({
  providedIn: 'root'
})

export class TodoService {
  private _items: string[];
  private apiUrl: string;

  constructor(private httpClient: HttpClient) {
    this._items = ['feed the cat', 'walk the dog', 'make coffee'];
    this.apiUrl = 'http://localhost:8083';
  }

  get items(): string[] {
    return this._items;
  }

  public getTodoList(){
    return this.httpClient.get<TodoModel[]>(`${this.apiUrl}/todos`);
  }
}
