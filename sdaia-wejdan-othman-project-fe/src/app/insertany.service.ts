import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class InsertanyService {
    private apiUrl = '/JAVAPROJECT/webapi/RATINGS/insert_into_Ratings';

    constructor(private http: HttpClient) {
    }

    createDoctor(conData: any): void {

        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
                Authorization: 'my-auth-token'
            })
        };

        this.http.post<any>(this.apiUrl, conData, httpOptions).subscribe(responseData => {
            console.log(responseData);
        });
    }
}
