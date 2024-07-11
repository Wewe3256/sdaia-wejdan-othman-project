import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SchedulesService {

  constructor(private http: HttpClient) { }
  getAllSchedules(): Observable<any[]> {

    return this.http.get<any[]>("http://DEV-1261.nicdev.local.gov:8080/JAVAPROJECT/webapi/SCHEDULES/selectAll_SCHEDULES")
  }
}
