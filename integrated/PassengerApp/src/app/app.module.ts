import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddPassengerComponent } from './add-passenger/add-passenger.component';
import { ListPassengerComponent } from './list-passenger/list-passenger.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { PassengerService } from './services/passengerservices';


@NgModule({
  declarations: [
    AppComponent,
    AddPassengerComponent,
    ListPassengerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [PassengerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
