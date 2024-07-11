import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class DoctorService {

    constructor(private http: HttpClient) {
    }

    getAllDoctors(): Observable<any[]> {

        return this.http.get<any[]>("http://DEV-1261.nicdev.local.gov:8080/JAVAPROJECT/webapi/doctors/SELECT_doctor_infoall")
    }

}
