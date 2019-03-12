import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import {TodoSelectListComponent} from './todo/todo-select-list/todo-select-list.component';
import {TodoListComponent} from './todo/todo-list/todo-list.component';
import {TodoInputBoxComponent} from './todo/todo-input-box/todo-input-box.component';
describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        TodoSelectListComponent,
        TodoListComponent,
        TodoInputBoxComponent
      ],
      imports: [
        FormsModule
      ]
    }).compileComponents();
  }));
});
