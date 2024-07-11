import {Component} from '@angular/core'
import {HttpClientModule} from "@angular/common/http";
import {NgFor, NgIf} from "@angular/common";
import {DoctorService} from "../doctor.service";

@Component({
    selector: 'app-doctor',
    standalone: true,
    imports: [HttpClientModule, NgIf, NgFor],
    styleUrls: ['./doctor.component.css'],
    templateUrl: 'doctor.component.html',
})


export class DoctorComponent {
    protected doctorsList: any[] | undefined;

    constructor(private doctorService: DoctorService) {
    }

    ngOnInit(): void {
        this.doctorService.getAllDoctors().subscribe(doctors => {
            this.doctorsList = doctors;
            console.log(this.doctorsList);
        })

    }

}
