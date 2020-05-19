import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddPassengerComponent} from './add-passenger/add-passenger.component';
import {ListPassengerComponent} from'./list-passenger/list-passenger.component';
import { from } from 'rxjs';

const routes: Routes = [{
  path:'add-passenger',
  component: AddPassengerComponent
}
,
{
  path:'list-passenger',
  component:ListPassengerComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
