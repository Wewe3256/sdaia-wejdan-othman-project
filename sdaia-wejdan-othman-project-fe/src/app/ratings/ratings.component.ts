import { Component, OnInit  } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {NgIf,NgForOf} from "@angular/common";
import { RatingsService } from '../ratings.service';

@Component({
  selector: 'app-ratings',
  template: `
    <table border="1" *ngIf="RatingsList">
      <thead>
        <tr>
          <th>Id</th>
          <th>PatientId</th>
          <th>DoctorId</th>
          <th>Rating</th>
          
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let Ratings of RatingsList">
          <td>{{ Ratings.id }}</td>
          <td>{{ Ratings.patient_id }}</td>
          <td>{{ Ratings.doctor_id }}</td>
          <td>{{ Ratings.rating }}</td>
        </tr>
      </tbody>
    </table>
  `,
  standalone: true,
  imports:[HttpClientModule, NgIf,NgForOf],
  styleUrls: ['./ratings.component.css']
})
export class RatingsComponent {
  protected RatingsList: any[] | undefined;

  constructor(private ratingsService: RatingsService) {
    }

  ngOnInit(): void {
    this.ratingsService.getAllRatings().subscribe(Ratings => {
      this.RatingsList = Ratings;
      console.log(this.RatingsList);
    })

  }
}
