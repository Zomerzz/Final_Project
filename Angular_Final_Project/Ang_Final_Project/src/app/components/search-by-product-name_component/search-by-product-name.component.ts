import { Component, inject, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { SearchModel } from '../../model/SearchModel';
import { Videogioco } from '../../model/Videogioco';
import { Libro } from '../../model/Libro';
import { Film } from '../../model/Film';
import { LibroService } from '../../services/LibroService';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-search-by-product-name',
  imports: [FormsModule],
  templateUrl: './search-by-product-name.component.html',
  styleUrl: './search-by-product-name.component.css'
})
export class SearchByProductNameComponent {
  videogioco: Partial<Videogioco> = {titolo:''};
  libro: Partial<Libro> = {titolo:''};
  film: Partial<Film> = {titolo:''};
  listaLibro: Libro[] = [];
  listaFilm: Film[] = [];
  listaVideogioco: Videogioco[] = [];


  private _service :LibroService = inject(LibroService);
  private _route = inject(ActivatedRoute);
  

  
  send(){
    this._service.findByName(this.libro.titolo).subscribe({
      next: listaLibroDb =>{
        this.listaLibro = listaLibroDb;
        console.log(this.listaLibro);
      },
      error: e => alert("la ricerca findByName andata male")
    });
    // console.log(this.videogioco.titolo);
    // console.log(this.libro.titolo);
    // console.log(this.film.titolo);
  }
}
