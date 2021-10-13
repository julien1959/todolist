import { Injectable } from '@angular/core';
import { TaskInfo } from './taskinfo';
import { Subject, Observable } from 'rxjs';//Pour l'asynchrone
import { HttpClient } from '@angular/common/http';// Librairie pour appel vers api
import { tap } from 'rxjs/operators'


const url = 'http://localhost:8080/tasks';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private _refreshTasks$ = new Subject<void>();

  get refreshTasks$(){
    return this._refreshTasks$;
  }

  constructor(
    private http: HttpClient
  ) { }

  getAllTasks(): Observable<TaskInfo[]> {
    return this.http.get<TaskInfo[]>(url);
  }

  create(newTask: TaskInfo): Observable<TaskInfo>{
    return this.http
    .post<TaskInfo>(url, newTask)
    .pipe(
      tap(() => {
        this._refreshTasks$.next();
      })
    );
  }

  delete(id): Observable<any>{
    return this.http
    .delete(`${url}/${id}`)
    .pipe(
      tap(() => {
        this._refreshTasks$.next();
      })
    );
  }

}
