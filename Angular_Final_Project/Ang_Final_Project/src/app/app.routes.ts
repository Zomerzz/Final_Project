import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { RecensioniListComponent } from './components/recensioni-list/recensioni-list.component';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserPageComponent},
  // prova per le liste di recensioni{ path: 'recensioni-list/:type/:id', component: RecensioniListComponent}
];
