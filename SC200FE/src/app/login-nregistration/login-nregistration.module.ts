import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import { LayoutModule } from '@angular/cdk/layout';

import { AlertComponent } from './_directives';
import { AuthGuard } from './_guards';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AlertService, AuthenticationService, UserService } from './_services';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { HeaderComponent } from './header/header.component';

import { RecommedationCardComponent } from './home/recommedation-card/recommedation-card.component';

import { LoginNRegistrationRoutingModule } from './login-nregistration-routing.module';
import { LoginNRegistrationComponent } from './login-nregistration.component';

@NgModule({
  imports: [
    CommonModule,
    LoginNRegistrationRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatCardModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule

  ],
  declarations: [
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    LoginNRegistrationComponent,
    RecommedationCardComponent
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

    // provider used to create fake backend
  ]
})
export class LoginNRegistrationModule { }
