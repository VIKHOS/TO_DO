import {Injectable, Type} from '@angular/core';
import {TodoModule} from './todo.module';

export class TodoService {
  private _items: string[];

  constructor() {
    this._items = ['feed the cat', 'walk the dog', 'make coffee'];
  }

  get items(): string[] {
    return this._items;
  }
}
