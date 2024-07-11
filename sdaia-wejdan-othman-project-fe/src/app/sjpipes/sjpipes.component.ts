import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sjpipes',
  standalone: true,
  templateUrl: './sjpipes.component.html',
  styleUrls: ['./sjpipes.component.css']
})
export class SjpipesComponent {

    dateToday: string | undefined;
    name: string | undefined;

    constructor () {}

    ngOnInit(): void {

        this.dateToday = new Date().toDateString();
        //this.dateToday?.substring(4.this.dateToday?.length);
        this.name = "Wejdan ANGULAR COURSE";

        }

}
