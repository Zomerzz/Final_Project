import { Component, inject, OnInit } from '@angular/core';
import { Film } from '../../../model/Film';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { FilmVisto } from '../../../model/FilmVisto';
import { AuthService } from '../../../services/AuthService';
import { MediaRegistratoService } from '../../../services/MediaRegistratoService';
import { ActivatedRoute } from '@angular/router';
import { FilmService } from '../../../services/FilmService';
import { AddRecensioneComponent } from '../../recensioni/add-recensione/add-recensione.component';
import { RecensioneCardComponent } from '../../recensioni/recensione-card/recensione-card.component';

@Component({
  selector: 'app-film-details',
  imports: [RecensioniListComponent, AddRecensioneComponent, RecensioneCardComponent],
  templateUrl: './film-details.component.html',
  styleUrl: './film-details.component.css'
})
export class FilmDetailsComponent implements OnInit{
  film!: Film;
  filmVisto!: FilmVisto | null;
  type = 'films';
  recensioni: Recensione[] = [];

  private _activatedRoute = inject(ActivatedRoute);
  private _recensioneService = inject(RecensioneService);
  private _authService = inject(AuthService);
  private _filmService = inject(FilmService);
  private _mediaRegistratoService = inject(MediaRegistratoService);

  ngOnInit(): void {
    if(history.state && history.state.film){
      this.film = history.state.film;
      this.loadRecensioni(this.film.id);
    } else {
      const id = this._activatedRoute.snapshot.paramMap.get("id");
        if(id != null){
          const filmId = Number(id);
          if (filmId > 0 && !isNaN(filmId)){
            this._filmService.getById(filmId).subscribe({
              next: f => {
                this.film = f;
                this.loadRecensioni(filmId);
              },
              error: e => alert('errore nel caricamento del film')
            });
          }
        }
    }

    if(this.isAlreadylogged){
      this._mediaRegistratoService.getFilmVistoByFilmIdAndUtenteId(this.film.id, Number(this._authService.getUserId()))
          .subscribe({
            next: fv => this.filmVisto = fv,
            error: e => this.filmVisto = null
      });
    }
  }

  get isAlreadylogged() {
    return this._authService.isLogged();
  }

  registerFilmVisto() {
    this._mediaRegistratoService.addFilmVisto({
      film: this.film,
      utenteId: Number(this._authService.getUserId())
    }).subscribe({
      next: fv => this.filmVisto = fv,
      error: e => alert('errore nella registrazione del film visto: '+ e)
    });
  }

  deleteFilmVisto() {
    if(this.filmVisto){
      this._mediaRegistratoService.deleteFilmVisto(this.filmVisto.filmVistoId).subscribe({
      next: () =>{
        this.recensioni = this.recensioni
            .filter(recensione => recensione.recensioneId != this.filmVisto?.recensione.recensioneId);
        this.filmVisto = null;
      },
      error: e => alert('errore nella registrazione del film visto: '+ e)
      });
    }
  }

  loadRecensioni(id: number){
    this._recensioneService.getRecensioni(this.type, id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });
  }

  addNewRecensione(rec:Recensione){
        this.recensioni.push(rec);
        if(this.filmVisto){
            this.filmVisto.recensione = rec;
        }
    }

  getBarColor(): string {
    const voto = this.film.voto;
    if (voto >= 75) return '#30D158';
    if (voto >= 50) return '#FFD60A';
    if (voto >= 25) return '#FF9F0A';
    return '#FF453A';
}
}

