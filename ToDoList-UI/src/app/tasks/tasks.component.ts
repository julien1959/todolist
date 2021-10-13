import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { TaskInfo } from '../taskinfo';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  //taskForm: FormGroup;
  tasks: TaskInfo[] = [];

  taskForm = this.fb.group({
    name: ['']
  })

  constructor(
    private fb: FormBuilder,
    private taskService: TaskService
  ) { }


  ngOnInit(): void {

    this.taskService.refreshTasks$.subscribe(() => {
      this.getTasks();
    });

    this.getTasks();
  }

  getTasks(){
    this.taskService.getAllTasks().subscribe(
      data => {
        this.tasks = data;
      },
      error => {
        console.log(error);
      })
  }


  createTask(){
    const newTask: TaskInfo = this.taskForm.value;
    //this.tasks.push(newTask);
      this.taskService.create(newTask).subscribe(
        response => {
          console.log(response);
        }, error => {
          console.log(error);
        }
      );
    }

    deleteTask(id){
      //this.tasks.splice(id, 1);
      this.taskService.delete(id).subscribe(
        response => {
          console.log(response);
        }, error => {
          console.log(error);
        }
      );
    }

}


