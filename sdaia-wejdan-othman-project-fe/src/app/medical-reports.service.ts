import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MedicalReportsService {

  constructor(private http: HttpClient) { }

  getAllmedical(): Observable<any[]> {

    return this.http.get<any[]>("http://DEV-1261.nicdev.local.gov:8080/JAVAPROJECT/webapi/MEDICAL_REPORTS/all_MEDICAL_REPORTS")
  }
}
