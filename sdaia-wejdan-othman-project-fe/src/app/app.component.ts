import { Component, OnInit } from '@angular/core'
import {RouterOutlet} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, HttpClientModule],
  styleUrls: ['./app.component.css'],
  templateUrl: './app.component.html',

})
export class AppComponent {

  currentComponent: string = '';

  showComponent(component: string) {
    this.currentComponent = component;
  }


}





