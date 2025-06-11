import { Component } from '@angular/core';
import { RecensioniListComponent } from '../recensioni-list/recensioni-list.component';
import { SearchByProductNameComponent } from '../search-by-product-name_component/search-by-product-name.component';

@Component({
  selector: 'app-home',
  imports: [RecensioniListComponent,SearchByProductNameComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
