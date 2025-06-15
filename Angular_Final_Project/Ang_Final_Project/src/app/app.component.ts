import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { HeaderComponent } from './components/header-component/header-component';
import { SearchByProductNameComponent } from './components/search-by-product-name_component/search-by-product-name.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Ang_Final_Project';
}
