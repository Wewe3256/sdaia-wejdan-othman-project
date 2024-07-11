import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoctoridService {
  private apiUrl = 'http://DEV-1261.nicdev.local.gov:8080/JAVAPROJECT/webapi/doctors';

  constructor(private http: HttpClient) { }

  getDoctorById(doctorId: number): Observable<any> {
    const url = `${this.apiUrl}/SELECT_doctorby_id/${doctorId}`;
    return this.http.get<any>(url);
  }
}
