import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';
import { HeaderComponent } from './header/header.component';
import { LoginNRegistrationComponent } from './login-nregistration.component';

const routes: Routes = [
  {path: '', component: LoginNRegistrationComponent,
  children: [
    { path: '', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'logout', component: HeaderComponent },
    // { path: 'login', component: LoginComponent },
    // { path: 'register', component: RegisterComponent },
    { path: 'profile/:id', loadChildren: '../profile/profile.module#ProfileModule'},
    { path: 'challenge', loadChildren: '../create-challenge/create-challenge.module#CreateChallengeModule'},
    { path: 'attempt/:id', loadChildren: '../attempting-challenge/attempting-challenge.module#AttemptingChallengeModule',runGuardsAndResolvers:"paramsOrQueryParamsChange"},
    { path: '**', redirectTo: 'logout' }
  ]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginNRegistrationRoutingModule { }
