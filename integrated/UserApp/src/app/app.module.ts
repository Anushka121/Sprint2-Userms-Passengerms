import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from  '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddUserComponent } from './add-user/add-user.component';
import { ListUserComponent } from './list-user/list-user.component';
import { ModifyUserComponent } from './modify-user/modify-user.component';
import {UserService} from './service/userservice';
import { HttpClientModule} from '@angular/common/http';
import { SearchUserComponent } from './search-user/search-user.component';
@NgModule({
  declarations: [
    AppComponent,
    AddUserComponent,
    ListUserComponent,
    ModifyUserComponent,
    SearchUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

    
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
