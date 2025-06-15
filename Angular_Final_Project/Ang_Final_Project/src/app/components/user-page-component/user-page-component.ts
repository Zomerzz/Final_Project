import { Component, inject, OnInit } from '@angular/core';
import { FilmListComponent } from '../home-lists/film-list/film-list.component';
import { LibroListComponent } from '../home-lists/libro-list/libro-list.component';
import { VideogiocoListComponent } from '../home-lists/videogioco-list/videogioco-list.component';
import { Libro } from '../../model/Libro';
import { Videogioco } from '../../model/Videogioco';
import { Film } from '../../model/Film';
import { FilmVisto } from '../../model/FilmVisto';
import { LibroLetto } from '../../model/LibroLetto';
import { VideogiocoGiocato } from '../../model/VideogiocoGiocato';
import { MediaRegistratoService } from '../../services/MediaRegistratoService';
import { AuthService } from '../../services/AuthService';

@Component({
  selector: 'app-user-page-component',
  imports: [FilmListComponent, LibroListComponent, VideogiocoListComponent],
  templateUrl: './user-page-component.html',
  styleUrl: './user-page-component.css'
})
export class UserPageComponent implements OnInit{
  utenteId!: number;
  //filmVisti ha dentro i film e le recensioni se ci sono
  filmVisti: FilmVisto[] = [];
  //films ha dentro solo i film visti
  films: Film[] = [];

  libriLetti: LibroLetto[] = [];
  libri: Libro[] = [];

  videogiochiGiocati: VideogiocoGiocato[] = [];
  videogiochi: Videogioco[] = [];

  private _authService = inject(AuthService);
  private _mediaRegistratoService = inject(MediaRegistratoService);

  ngOnInit(): void {
    this.utenteId = Number(this._authService.getUserId());
    this._mediaRegistratoService.getFilmVisti(this.utenteId).subscribe({
      next: list => {
        this.filmVisti = list;
        if(this.filmVisti.length){
        this.filmVisti.forEach(filmVisto => {
          this.films.push(filmVisto.film);
      });
    }
      },
      error: e => alert('errore nel caricamento dei film visti :' + e)
    });
    this._mediaRegistratoService.getLibriLetti(this.utenteId).subscribe({
      next: list => {
        this.libriLetti = list;
        if(this.libriLetti.length){
          this.libriLetti.forEach(libroLetto => {
            this.libri.push(libroLetto.libro);
          });
        }
      },
      error: e => alert('errore nel caricamento dei libri letti :' + e)
    });
  
    this._mediaRegistratoService.getVideogiochiGiocati(this.utenteId).subscribe({
      next: list => {
        this.videogiochiGiocati = list;
        if(this.videogiochiGiocati.length){
          this.videogiochiGiocati.forEach(videogiocoGiocato => {
            this.videogiochi.push(videogiocoGiocato.videogioco);
          });
        }
      },
      error: e => alert('errore nel caricamento dei videogiochi giocati :' + e)
    });
  }
}