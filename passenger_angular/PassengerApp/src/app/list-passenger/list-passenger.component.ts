import { Component, OnInit } from '@angular/core';
import {Passenger} from '../model/passenger';


@Component({
  
  selector: 'app-list-passenger',
  templateUrl: './list-passenger.component.html',
  styleUrls: ['./list-passenger.component.css']
})
export class ListPassengerComponent implements OnInit {
passengers:Passenger[]=[];

  constructor() {
    let passenger1=new Passenger(1234567890,987654567891,"ashku",21,10,"female");
    let passenger2=new Passenger(1234567891,987654567890,"anu",22,10,"female");
this.passengers.push(passenger1);
this.passengers.push(passenger2);

   }

  ngOnInit(): void {
  }

  found:Passenger=null;

  findPassenger(form:any)
  {
    let details=form.value;
    let uin= details.uin;
    for(let passenger of this.passengers){
      if(passenger.uin===uin){

        this.found=passenger;
      }
    }
  }





  
}
