import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { SearchByProductNameComponent } from './components/search-by-product-name_component/search-by-product-name.component';
import { SearchComponent } from './components/search-component/search-component';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'search-by-product-name', component: SearchByProductNameComponent },
  { path: 'search', component: SearchComponent },
  { path: 'user', component: UserPageComponent}];
