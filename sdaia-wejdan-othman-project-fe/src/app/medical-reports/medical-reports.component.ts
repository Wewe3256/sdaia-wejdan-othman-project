import { Component, OnInit } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {NgIf,NgForOf} from "@angular/common";
import { MedicalReportsService } from '../medical-reports.service';

@Component({
  selector: 'app-medical-reports',
  imports:[HttpClientModule, NgIf,NgForOf],
  template: `
    <table border="1" *ngIf="MedicalList">
      <thead>
        <tr>
          <th>Id</th>
          <th>PatientId</th>
          <th>Details</th>
          <th>ReportDate</th>
          <th>Rating_Id</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let MEDICAL_REPORTS of MedicalList">
          <td>{{ MEDICAL_REPORTS.id }}</td>
          <td>{{ MEDICAL_REPORTS.patient_id }}</td>
          <td>{{ MEDICAL_REPORTS.details }}</td>
          <td>{{ MEDICAL_REPORTS.report_date }}</td>
          <td>{{ MEDICAL_REPORTS.rating_id }}</td>
        </tr>
      </tbody>
    </table>
  `,
  standalone: true,
  styleUrls: ['./medical-reports.component.css']
})
export class MedicalReportsComponent {
  protected MedicalList: any[] | undefined;

   constructor(private medicalReportsService: MedicalReportsService) {
    }

  ngOnInit(): void {
    this.medicalReportsService.getAllmedical().subscribe(medical => {
      this.MedicalList = medical;
      console.log(this.MedicalList);
    })

  }
}
