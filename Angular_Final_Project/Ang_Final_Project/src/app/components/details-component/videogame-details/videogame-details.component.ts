import { Component, inject, OnInit } from '@angular/core';
import { Videogioco } from '../../../model/Videogioco';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";

@Component({
  selector: 'app-videogame-details',
  imports: [RecensioniListComponent],
  templateUrl: './videogame-details.component.html',
  styleUrl: './videogame-details.component.css'
})
export class VideogameDetailsComponent implements OnInit{

  videogioco!: Videogioco;
  recensioni!: Recensione[];
  recensioneService = inject(RecensioneService);

  ngOnInit(): void {
    if(history.state && history.state.videogioco){
      this.videogioco = history.state.videogioco;
    } else {
      console.log('Nessun dato disponibile per il videogioco');
    }
    this.recensioneService.getRecensioni('videogioco', this.videogioco.id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });
    console.log(this.recensioni);
  }


}
