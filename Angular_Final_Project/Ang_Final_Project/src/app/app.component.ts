import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductListComponent } from './components/home-lists/videogioco-list/product-list-components';
import { FooterComponent } from './components/footer-component/footer-component';
import { HeaderComponent } from './components/header-component/header-component';
import { SearchByProductNameComponent } from './components/search-by-product-name_component/search-by-product-name.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FooterComponent, HeaderComponent, SearchByProductNameComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Ang_Final_Project';
}
