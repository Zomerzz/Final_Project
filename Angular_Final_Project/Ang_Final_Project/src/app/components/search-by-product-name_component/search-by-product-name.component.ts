import { Component, inject, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { SearchModel } from '../../model/SearchModel';
import { Videogioco } from '../../model/Videogioco';
import { Libro } from '../../model/Libro';
import { Film } from '../../model/Film';
import { LibroService } from '../../services/LibroService';
import { ActivatedRoute } from '@angular/router';
import { VideogiocoService } from '../../services/VideogiocoService';
import { FilmService } from '../../services/FilmService';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-search-by-product-name',
  imports: [FormsModule, CommonModule],
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


  private _libroService :LibroService = inject(LibroService);
  private _videogiocoService :VideogiocoService = inject(VideogiocoService);
  private _filmService: FilmService = inject(FilmService);

  private _route = inject(ActivatedRoute);



  send(){
    this._libroService.findByName(this.libro.titolo).subscribe({
      next: listaLibroDb =>{
        this.listaLibro = listaLibroDb;
        console.log(this.listaLibro);
      },
      error: e => {
        console.log("====================================================");
        console.log("la ricerca findByName libro non ha trovato risultati");
        console.log("====================================================");

      }
    });
    this._videogiocoService.getByName(this.film.titolo).subscribe({
      next: listaVideogiocoDb =>{
        this.listaVideogioco = listaVideogiocoDb;
        console.log(this.listaVideogioco);
      },
      error: e => {
        console.log("=========================================================");
        console.log("la ricerca findByName videogioco non ha trovato risultati");
        console.log("=========================================================");

      }
    });
    this._filmService.findByName(this.film.titolo).subscribe({
      next: listaFilmDb => {
        this.listaFilm= listaFilmDb;
        console.log(this.listaFilm);
      },
      error: e => {
        console.log("===================================================");
        console.log("la ricerca findByName film non ha trovato risultati");
        console.log("===================================================");

      }
    })
    // console.log(this.videogioco.titolo);
    // console.log(this.libro.titolo);
    // console.log(this.film.titolo);
  }
}
