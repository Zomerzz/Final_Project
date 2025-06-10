import { Component } from '@angular/core';
import { SearchByProductNameComponent } from '../search-by-product-name_component/search-by-product-name.component';

@Component({
  selector: 'app-home',
  imports: [SearchByProductNameComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
