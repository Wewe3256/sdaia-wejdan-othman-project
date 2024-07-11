import { Component } from '@angular/core';
import { InsertanyService } from '../insertany.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-insert',
  template: `
    <div class="form-container">
      <label for="id">ID:</label>
      <input type="number" id="id" name="id" [(ngModel)]="id"/>
      <br>
      <label for="patient_id">Patient ID:</label>
      <input type="number" id="patient_id" name="patient_id" [(ngModel)]="patient_id"/>
      <br>
      <label for="doctor_id">Doctor ID:</label>
      <input type="number" id="doctor_id" name="doctor_id" [(ngModel)]="doctor_id"/>
      <br>
      <label for="rating">Rating:</label>
      <input type="text" id="rating" name="rating" [(ngModel)]="rating"/>
      <br>
      <button (click)="createDoctor()">Rate Doctor</button>
    </div>
  `,
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./insert.component.css']
})
export class InsertComponent {
  id: number = 0;
  patient_id: number = 0;
  doctor_id: number = 0;
  rating: string = '';

  constructor(private insertAnyService: InsertanyService) {}

  ngOnInit(): void {
    // يمكن تركها فارغة أو استخدامها لأغراض أخرى
  }

  createDoctor(): void {
    const conData = {
      id: this.id,
      patient_id: this.patient_id,
      doctor_id: this.doctor_id,
      rating: this.rating
    };

    this.insertAnyService.createDoctor(conData);
  }
}
