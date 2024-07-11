import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
@Injectable({providedIn: 'root'})
export class ConsultationsService {

  constructor(private http: HttpClient) { }
  getAllConsultations(): Observable<any[]> {

    return this.http.get<any[]>("http://DEV-1261.nicdev.local.gov:8080/JAVAPROJECT/webapi/CONSULTATIONS/selectAll_CONSULTATIONS")
  }
}
