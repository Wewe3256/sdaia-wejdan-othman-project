import { Component , OnInit} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {NgIf,NgForOf} from "@angular/common";
import { PatientsService } from '../patients.service';


@Component({
  selector: 'app-patients',
  imports:[HttpClientModule, NgIf,NgForOf],
  template: `
    <table border="1" *ngIf="PatientsList">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>BirthDate</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let PATIENTS of PatientsList">
          <td>{{ PATIENTS.id }}</td>
          <td>{{ PATIENTS.name }}</td>
          <td>{{ PATIENTS.birth_date }}</td>
          <td>{{ PATIENTS.phone }}</td>
        </tr>
      </tbody>
    </table>

  `,
  standalone: true,
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent {
  protected PatientsList: any[] | undefined;

    constructor(private patientsService: PatientsService) {
    }

  ngOnInit(): void {
    this.patientsService.getAllPatients().subscribe(patients => {
      this.PatientsList = patients;
      console.log(this.PatientsList);
    })

  }
}
