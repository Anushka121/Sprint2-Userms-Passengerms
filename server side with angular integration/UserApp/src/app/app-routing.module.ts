import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddUserComponent} from '../app/add-user/add-user.component';
import { ListUserComponent} from '../app/list-user/list-user.component';
import { ModifyUserComponent} from '../app/modify-user/modify-user.component';
import { SearchUserComponent } from './search-user/search-user.component';
import { UserOperationComponent } from './user-operation/user-operation.component';

const routes: Routes = [

  {
    path:'add-user',
    component:AddUserComponent
  },
  
  {
   path:'list-user',
   component:ListUserComponent
  },
  {
    path:'modify-user',
    component:ModifyUserComponent

  },
  {
    path:'search-user',
    component:SearchUserComponent

  },
  {
    path:'user-operation',
    component:UserOperationComponent

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
