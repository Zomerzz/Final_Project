import { Component } from '@angular/core';
import { RecensioniListComponent } from '../recensioni-list/recensioni-list.component';

@Component({
  selector: 'app-home',
  imports: [RecensioniListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
