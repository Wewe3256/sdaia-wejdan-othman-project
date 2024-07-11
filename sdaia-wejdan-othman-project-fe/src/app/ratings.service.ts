import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RatingsService {

  constructor(private http: HttpClient) { }
  getAllRatings(): Observable<any[]> {

    return this.http.get<any[]>("http://DEV-1261.nicdev.local.gov:8080/JAVAPROJECT/webapi/RATINGS/selectAll_RATINGS")
  }
}
