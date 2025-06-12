import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { LoginAreaComponent } from './components/login-area-component/login-area-component';
import { RegisterAreaComponent } from './components/register-area/register-area.component';
import { DetailsComponent } from './components/details-component/details-component';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserPageComponent},
  { path: 'auth/login', component: LoginAreaComponent},
  { path: 'auth/register', component: RegisterAreaComponent},
  { path: 'game-detail/:id', component: DetailsComponent}

];
