import { Component, inject, OnInit } from '@angular/core';
import { Videogioco } from '../../../model/Videogioco';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { VideogiocoGiocato } from '../../../model/VideogiocoGiocato';
import { AuthService } from '../../../services/AuthService';
import { MediaRegistratoService } from '../../../services/MediaRegistratoService';
import { ActivatedRoute } from '@angular/router';
import { VideogiocoService } from '../../../services/VideogiocoService';
import { AddRecensioneComponent } from '../../recensioni/add-recensione/add-recensione.component';
import { RecensioneCardComponent } from '../../recensioni/recensione-card/recensione-card.component';

@Component({
  selector: 'app-videogame-details',
  imports: [RecensioniListComponent, AddRecensioneComponent, RecensioneCardComponent],
  templateUrl: './videogame-details.component.html',
  styleUrl: './videogame-details.component.css'
})
export class VideogameDetailsComponent implements OnInit{
  videogioco!: Videogioco;
  videogiocoGiocato!: VideogiocoGiocato | null;
  type = 'videogiochi';
  recensioni: Recensione[] = [];

  private _activatedRoute = inject(ActivatedRoute);
  private _recensioneService = inject(RecensioneService);
  private _authService = inject(AuthService);
  private _videogiocoService = inject(VideogiocoService);
  private _mediaRegistratoService = inject(MediaRegistratoService);

  ngOnInit(): void {
    if(history.state && history.state.videogioco){
      this.videogioco = history.state.videogioco;
      this.loadRecensioni(this.videogioco.id);
    } else {
      const id = this._activatedRoute.snapshot.paramMap.get("id");
        if(id != null){
          const videogiocoId = Number(id);
          if (videogiocoId > 0 && !isNaN(videogiocoId)){
            this._videogiocoService.getById(videogiocoId).subscribe({
              next: v => {
                this.videogioco = v;
                this.loadRecensioni(videogiocoId);
              },
              error: e => alert('errore nel caricamento del film')
            });
          }
        }
    }

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
      this._mediaRegistratoService.deleteVideogiocoGiocato(this.videogiocoGiocato.videogiocoGiocatoId).subscribe({
      next: () =>{
        this.recensioni = this.recensioni.
            filter(recensione => recensione.recensioneId != this.videogiocoGiocato?.recensione.recensioneId);
        this.videogiocoGiocato = null;
      },
      error: e => alert('errore nella registrazione del videogioco giocato: '+ e)
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
        if(this.videogiocoGiocato){
            this.videogiocoGiocato.recensione = rec;
        }
    }

 getBarColor(): string {
    const voto = this.videogioco.voto;
    if (voto >= 75) return '#30D158';
    if (voto >= 50) return '#FFD60A';
    if (voto >= 25) return '#FF9F0A';
    return '#FF453A';
}

}
