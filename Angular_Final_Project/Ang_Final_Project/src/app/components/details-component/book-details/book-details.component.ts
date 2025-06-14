import { Component, inject, OnInit } from '@angular/core';
import { Libro } from '../../../model/Libro';
import { ActivatedRoute } from '@angular/router';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-book-details',
  imports: [CommonModule, RecensioniListComponent],
  templateUrl: './book-details.component.html',
  styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit{
  libro!: Libro;
  recensioni!: Recensione[];
  recensioneService = inject(RecensioneService);

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Recupera i dati direttamente da history.state
    if (history.state && history.state.libro) {
      this.libro = history.state.libro;
    } else {
      // fallback in caso di errore, come se la pagina venisse caricata senza stato
      console.log('Nessun dato disponibile per il libro');
    }

    this.recensioneService.getRecensioni('libro', this.libro.id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });
    console.log(this.recensioni);
  }

}
