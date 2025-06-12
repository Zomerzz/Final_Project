import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPageComponent } from './components/user-page-component/user-page-component';
import { Component } from '@angular/core';
import { DetailsComponent } from './components/details-component/details-component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserPageComponent},
  { path: 'game-detail/:id', component: DetailsComponent}
  ];

