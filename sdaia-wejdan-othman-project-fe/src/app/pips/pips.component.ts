import { Component,OnI} from '@angular/core';

@Component({
  selector: 'app-pips',
  templateUrl: './pips.component.html',
  styleUrls: ['./pips.component.css']
  standalone:true,
})
export class PipsComponent {

    datetoday: String;
     name: String;

  constructor(){
      }

  noOnInit(): void{
      this.datetoday=new Date().toDateString();
      this.name="sdaia"

      }
}
