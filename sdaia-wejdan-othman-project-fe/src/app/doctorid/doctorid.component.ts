import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../doctor.service';
import { HttpClientModule } from '@angular/common/http';
import { NgIf, NgForOf,} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {DoctoridService} from "../doctorid.service";

@Component({
  imports: [HttpClientModule, NgIf, NgForOf, FormsModule],
  selector: 'app-doctorid',
  standalone: true,
  styleUrls: ['./doctorid.component.css'],
  template: `
    <div class="form-container" >
      <br><br>
      <br><br>
      <p> To Get Doctor Information </p>
      <label for="doctorId">Doctor ID:  <br>  </label>
      <input type="text" id="doctorId" [(ngModel)]="doctorId" />
      <br>
      <button (click)="fetchDoctor()">Fetch Doctor <br>  </button>
    </div>
    <table border="1" *ngIf="doctor" class="table-container">
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Specialty</th>
      </tr>
      </thead>
      <tbody>
      <tr *ngFor="let doctor of doctor">
        <td>{{ doctor.id }}</td>
        <td>{{ doctor.name }}</td>
        <td>{{ doctor.specialty }}</td>
      </tr>
      </tbody>
    </table>
  `,
})
export class DoctoridComponent {
  protected doctor: any | undefined;
  doctorId: number | undefined;

  constructor(private DoctoridService: DoctoridService) {
  }

  ngOnInit(): void {
    // يمكن تركها فارغة أو استخدامها لأغراض أخرى
  }

  fetchDoctor(): void {
    if (this.doctorId) {
      this.DoctoridService.getDoctorById(this.doctorId).subscribe(
          doctor => {
            this.doctor = doctor;
            console.log(this.doctor);
          },
          error => {
            console.error('Error fetching doctor:', error);
          });
    }
  }
}
