import { Component, OnInit  } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {NgIf,NgForOf} from "@angular/common";
import { ConsultationsService } from '../consultations.service';

@Component({
  selector: 'app-consultations',
  template: `
    <table border="1" *ngIf="ConsultationsList">
      <thead>
        <tr>
          <th>DoctorId</th>
          <th>PatientId</th>
          <th>RequestTime</th>
          <th>ConsultationTime</th>
          <th>Status</th>
          <th>Diagnosis</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let CONSULTATIONS of ConsultationsList">
          <td>{{ CONSULTATIONS.doctor_id }}</td>
          <td>{{ CONSULTATIONS.patient_id }}</td>
          <td>{{ CONSULTATIONS.request_time }}</td>
          <td>{{ CONSULTATIONS.consultation_time }}</td>
          <td>{{ CONSULTATIONS.status }}</td>
          <td>{{ CONSULTATIONS.diagnosis }}</td>
        </tr>
      </tbody>
    </table>
  `,
  standalone: true,
  imports:[HttpClientModule, NgIf,NgForOf],
  styleUrls: ['./consultations.component.css']
})
export class ConsultationsComponent {

  protected ConsultationsList: any[] | undefined;

  constructor(private ConsultationService: ConsultationsService) {
    }

  ngOnInit(): void {
    this.ConsultationService.getAllConsultations().subscribe(Consultations => {
      this.ConsultationsList = Consultations;
      console.log(this.ConsultationsList);
    })

  }


}
