import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { SearchByProductNameComponent } from './components/search-by-product-name_component/search-by-product-name.component';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserPageComponent},
  { path:'searchByNameTest', component:SearchByProductNameComponent}

];
