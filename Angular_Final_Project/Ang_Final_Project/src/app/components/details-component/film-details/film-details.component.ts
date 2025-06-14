import { Component, inject, OnInit } from '@angular/core';
import { Film } from '../../../model/Film';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";

@Component({
  selector: 'app-film-details',
  imports: [RecensioniListComponent],
  templateUrl: './film-details.component.html',
  styleUrl: './film-details.component.css'
})
export class FilmDetailsComponent implements OnInit{

  film!: Film;
  recensioni!: Recensione[];
  recensioneService = inject(RecensioneService);

  ngOnInit(): void {
    if(history.state && history.state.film){
      this.film = history.state.film;
    } else {
      console.log('Nessun dato disponibile per il film');
    }
    this.recensioneService.getRecensioni('film', this.film.id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });
    console.log(this.recensioni);
  }


}

