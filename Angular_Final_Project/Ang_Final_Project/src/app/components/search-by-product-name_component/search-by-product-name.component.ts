import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { SearchModel } from '../../model/SearchModel';
@Component({
  selector: 'app-search-by-product-name',
  imports: [FormsModule],
  templateUrl: './search-by-product-name.component.html',
  styleUrl: './search-by-product-name.component.css'
})
export class SearchByProductNameComponent {
  searchModel: Partial<SearchModel> = {titolo:''};
  send(){
    
  }
}
