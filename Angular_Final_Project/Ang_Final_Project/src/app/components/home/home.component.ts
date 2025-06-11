import { Component } from '@angular/core';
import { RecensioniListComponent } from '../recensioni-list/recensioni-list.component';
import { AddRecensioneComponent } from "../add-recensione/add-recensione.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RecensioniListComponent, AddRecensioneComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
