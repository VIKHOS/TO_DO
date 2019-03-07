import { NgModule, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodoListComponent } from './todo-list/todo-list.component';
import { TodoInputBoxComponent } from './todo-input-box/todo-input-box.component';
import {TodoService} from './todo.service';
import {FormsModule} from '@angular/forms';
import {TodoSelectListComponent} from './todo-select-list/todo-select-list.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [TodoService],
  declarations: [TodoListComponent, TodoInputBoxComponent, TodoSelectListComponent]
})
export class TodoModule {



}
