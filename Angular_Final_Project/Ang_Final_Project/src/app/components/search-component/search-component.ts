import { Component, NgModule } from '@angular/core';
import { SearchModel } from '../../model/SearchModel';
import { FormsModule, NgForm } from '@angular/forms';


@Component({
  selector: 'app-search-component',
  imports: [FormsModule],
  templateUrl: './search-component.html',
  styleUrl: './search-component.css'
})
export class SearchComponent {
  searchModel:Partial<SearchModel> = {};

  sendForm(f:NgForm){
    console.log(f.value);
    console.log(this.searchModel);
  }
}
