import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {TodoListComponent} from './todo/todo-list/todo-list.component';
import {TodoModule} from './todo/todo.module';
import {TodoInputBoxComponent} from './todo/todo-input-box/todo-input-box.component';
import { HttpClientModule } from '@angular/common/http';
import { TodoSelectListComponent } from './todo/todo-select-list/todo-select-list.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TodoModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [TodoSelectListComponent]
})
export class AppModule { }
