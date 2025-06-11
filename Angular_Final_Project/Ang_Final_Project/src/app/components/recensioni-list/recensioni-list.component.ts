import { Component, Inject, inject, OnInit} from '@angular/core';
import { RecensioneCardComponent } from '../recensione-card/recensione-card.component';
import { Recensione } from '../../model/Recensione';
import { RecensioneService } from '../../services/RecensioneService';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-recensioni-list',
  imports: [CommonModule, RecensioneCardComponent],
  templateUrl: './recensioni-list.component.html',
  styleUrl: './recensioni-list.component.css'
})
export class RecensioniListComponent implements OnInit {
  list: Recensione[] = [];
  private _route = inject(ActivatedRoute);
  private _recensioneService: RecensioneService = inject(RecensioneService);
  private _router = inject(Router);

  ngOnInit(): void {
    const urlId = this._route.snapshot.paramMap.get('id');
    const type = this._route.snapshot.paramMap.get('type');
    if(urlId != null){
      const id = Number(urlId);
      if(id > 0 && !isNaN(id)){
        if(type == 'film'){
          this.loadFilmRecensioni(id);
        } else if (type == 'libro'){
          this.loadLibroRecensioni(id);
        } else if (type == 'videogioco'){
          this.loadVideogiocoRecensioni(id);
        }
      }
    }
  }

  loadFilmRecensioni(id:number){
    this._recensioneService.getRecensioniByFilm(id).subscribe({
      next: r => this.list = r,
      error: e => alert("errore nel caricamento delle recensioni")
    });
  }

  loadLibroRecensioni(id:number){
    this._recensioneService.getRecensioniByLibro(id).subscribe({
      next: r => this.list = r,
      error: e => alert("errore nel caricamento delle recensioni")
    });
  }

  loadVideogiocoRecensioni(id:number){
    this._recensioneService.getRecensioniByVideogioco(id).subscribe({
      next: r => this.list = r,
      error: e => alert("errore nel caricamento delle recensioni")
    });
  }
}
