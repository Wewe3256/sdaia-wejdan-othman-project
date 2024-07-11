import { Component, OnInit } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {NgIf,NgForOf} from "@angular/common";
import { SchedulesService } from '../schedules.service';

@Component({
  selector: 'app-schedules',
  imports:[HttpClientModule, NgIf,NgForOf],
  template: `
    <table border="1" *ngIf="SchedulesList">
      <thead>
        <tr>
          <th>Id</th>
          <th>DoctorId</th>
          <th>StartTime</th>
          <th>Endtime</th>
          <th>isAvailable</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let SCHEDULES of SchedulesList">
          <td>{{ SCHEDULES.id }}</td>
          <td>{{ SCHEDULES.doctor_id }}</td>
          <td>{{ SCHEDULES.start_time }}</td>
          <td>{{ SCHEDULES.end_time }}</td>
          <td>{{ SCHEDULES.is_available }}</td>
        </tr>
      </tbody>
    </table>
  `,
  standalone: true,
  styleUrls: ['./schedules.component.css']
})
export class SchedulesComponent {
  protected SchedulesList: any[] | undefined;

  constructor(private schedulesService: SchedulesService) {
  }

  ngOnInit(): void {
    this.schedulesService.getAllSchedules().subscribe(Schedules => {
      this.SchedulesList = Schedules;
      console.log(this.SchedulesList);
    })
  }
}
