import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { LoginAreaComponent } from './components/login-area-component/login-area-component';
import { RegisterAreaComponent } from './components/register-area/register-area.component';
import { AdminComponent } from './components/admin/admin.component';
import { LogOutComponent } from './components/log-out/log-out.component';
import { FilmDetailsComponent } from './components/details-component/film-details/film-details.component';
import { VideogameDetailsComponent } from './components/details-component/videogame-details/videogame-details.component';
import { BookDetailsComponent } from './components/details-component/book-details/book-details.component';
import { CreazioneDatiComponent } from './creazione-dati/creazione-dati.component';
import { CarminxComponent } from './compoenents/carminx/carminx.component';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full' },
{ path: 'home', component: HomeComponent },
{ path: 'user', component: UserPageComponent },
{ path: 'user/:id', component: UserPageComponent},
{ path: 'auth/login', component: LoginAreaComponent },
{ path: 'auth/logout', component: LogOutComponent },
{ path: 'auth/register', component: RegisterAreaComponent },
{ path: 'admin', component: AdminComponent },

//PATH PER LE DETAILS PAGE
{ path: 'films-dettagli/:id', component: FilmDetailsComponent },
{ path: 'libro-dettagli/:id', component: BookDetailsComponent },
{ path: 'videogiochi-dettagli/:id', component: VideogameDetailsComponent },


{ path: 'creazioneDati', component: CreazioneDatiComponent },

{ path: 'Carmine', component: CarminxComponent }
];

