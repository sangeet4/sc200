import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CoreModule } from './core/core.module';
import { LoginRegisterModule } from '../../../PlayGround/blur/src/app/login-register/login-register.module'
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    LoginRegisterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
