import { Component, inject, OnInit } from '@angular/core';
import { Videogioco } from '../../../model/Videogioco';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { VideogiocoGiocato } from '../../../model/VideogiocoGiocato';
import { AuthService } from '../../../services/AuthService';
import { MediaRegistratoService } from '../../../services/MediaRegistratoService';

@Component({
  selector: 'app-videogame-details',
  imports: [RecensioniListComponent],
  templateUrl: './videogame-details.component.html',
  styleUrl: './videogame-details.component.css'
})
export class VideogameDetailsComponent implements OnInit{
  videogioco!: Videogioco;
  videogiocoGiocato!: VideogiocoGiocato | null;
  recensioni: Recensione[] = [];
  private _recensioneService = inject(RecensioneService);
  private _authService = inject(AuthService);
  private _mediaRegistratoService = inject(MediaRegistratoService);

  ngOnInit(): void {
    if(history.state && history.state.videogioco){
      this.videogioco = history.state.videogioco;
    } else {
      console.log('Nessun dato disponibile per il videogioco');
    }

    this._recensioneService.getRecensioni('videogioco', this.videogioco.id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });

    if(this.isAlreadylogged){
      this._mediaRegistratoService.getVideogiocoGiocatoByVideogiocoIdAndUtenteId(this.videogioco.id, Number(this._authService.getUserId()))
          .subscribe({
            next: vg => this.videogiocoGiocato = vg,
            error: e => this.videogiocoGiocato = null
      });
    }

  }
  
  get isAlreadylogged() {
    return this._authService.isLogged();
  }

   registerVideogiocoGiocato() {
    this._mediaRegistratoService.addVideogiocoGiocato({
      videogioco: this.videogioco,
      utenteId: Number(this._authService.getUserId())
    }).subscribe({
      next: vg => this.videogiocoGiocato = vg,
      error: e => alert('errore nella registrazione del videogioco giocato: '+ e)
    });
  }

  deleteVideogiocoGiocato() {
    if(this.videogiocoGiocato){
      this._mediaRegistratoService.deleteLibroLetto(this.videogiocoGiocato.videogiocoGiocatoId).subscribe({
      next: () => this.videogiocoGiocato = null,
      error: e => alert('errore nella registrazione del videogioco giocato: '+ e)
      });
    }
 }

}
