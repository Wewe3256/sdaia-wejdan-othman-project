import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {DoctorComponent} from "../doctor/doctor.component";
import {PatientsComponent} from "../patients/patients.component";
import {ConsultationsComponent} from "../consultations/consultations.component";
import {SchedulesComponent} from "../schedules/schedules.component";
import {MedicalReportsComponent} from "../medical-reports/medical-reports.component";
import {RatingsComponent} from "../ratings/ratings.component";
import {DoctoridComponent} from "../doctorid/doctorid.component";
import {InsertComponent} from "../insert/insert.component";
import {NgIf} from "@angular/common";

@Component({
    selector: 'app-layout',
    standalone: true,
    imports: [RouterOutlet, DoctorComponent, PatientsComponent, ConsultationsComponent, SchedulesComponent, MedicalReportsComponent, RatingsComponent, DoctoridComponent, InsertComponent, NgIf],
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
    currentComponent: string = '';

    constructor() {
    }

    showComponent(component: string) {
        this.currentComponent = component;
    }
}

