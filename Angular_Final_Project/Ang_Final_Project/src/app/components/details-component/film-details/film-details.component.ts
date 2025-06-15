import { Component, inject, OnInit } from '@angular/core';
import { Film } from '../../../model/Film';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { FilmVisto } from '../../../model/FilmVisto';
import { AuthService } from '../../../services/AuthService';
import { MediaRegistratoService } from '../../../services/MediaRegistratoService';

@Component({
  selector: 'app-film-details',
  imports: [RecensioniListComponent],
  templateUrl: './film-details.component.html',
  styleUrl: './film-details.component.css'
})
export class FilmDetailsComponent implements OnInit{
  film!: Film;
  filmVisto!: FilmVisto | null;
  recensioni: Recensione[] = [];
  private _recensioneService = inject(RecensioneService);
  private _authService = inject(AuthService);
  private _mediaRegistratoService = inject(MediaRegistratoService);
  
  ngOnInit(): void {
    if(history.state && history.state.film){
      this.film = history.state.film;
    } else {
      console.log('Nessun dato disponibile per il film');
    }

    this._recensioneService.getRecensioni('film', this.film.id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });

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
      next: () => this.filmVisto = null,
      error: e => alert('errore nella registrazione del film visto: '+ e)
      });
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

