import { Component } from '@angular/core';
import { ProductListComponent } from '../product-list-component/product-list-components';
import { CommonModule } from '@angular/common';
import { FooterComponent } from '../footer-component/footer-component';
import { HeaderComponent } from '../header-component/header-component';
import { SearchByProductNameComponent } from "../search-by-product-name_component/search-by-product-name.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ProductListComponent, FooterComponent, HeaderComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {}

