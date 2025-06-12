import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { LoginAreaComponent } from './components/login-area-component/login-area-component';
import { RegisterAreaComponent } from './components/register-area/register-area.component';
import { DetailsComponent } from './components/details-component/details-component';
import { AdminComponent } from './components/admin/admin.component';
import { LogOutComponent } from './components/log-out/log-out.component';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'user', component: UserPageComponent},
  { path: 'game-detail/:id', component: DetailsComponent},
  { path: 'auth/login', component:LoginAreaComponent},
  { path: 'auth/logout', component:LogOutComponent},
  { path: 'auth/register', component:RegisterAreaComponent},
  { path: 'admin', component:AdminComponent}
  ];

