import { Component, inject, OnInit } from '@angular/core';
import { Libro } from '../../../model/Libro';
import { ActivatedRoute } from '@angular/router';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { CommonModule } from '@angular/common';
import { MediaRegistratoService } from '../../../services/MediaRegistratoService';
import { AuthService } from '../../../services/AuthService';
import { LibroLetto } from '../../../model/LibroLetto';

@Component({
  selector: 'app-book-details',
  imports: [CommonModule, RecensioniListComponent],
  templateUrl: './book-details.component.html',
  styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit{
  libro!: Libro;
  libroLetto!: LibroLetto | null;
  recensioni!: Recensione[];
  private _recensioneService = inject(RecensioneService);
  private _authService = inject(AuthService);
  private _mediaRegistratoService = inject(MediaRegistratoService);

  ngOnInit(): void {
    // Recupera i dati direttamente da history.state
    if (history.state && history.state.libro) {
      this.libro = history.state.libro;
    } else {
      // fallback in caso di errore, come se la pagina venisse caricata senza stato
      console.log('Nessun dato disponibile per il libro');
    }

    this._recensioneService.getRecensioni('libro', this.libro.id).subscribe({
      next: list => this.recensioni = list,
      error: e => alert('Errore nel caricamento delle recensioni')
    });
    
    if(this.isAlreadylogged){
      this._mediaRegistratoService.getLibroLettoByLibroIdAndUtenteId(this.libro.id, Number(this._authService.getUserId()))
          .subscribe({
            next: libroLetto => this.libroLetto = libroLetto,
            error: e => this.libroLetto = null
      });
    }
  }

  get isAlreadylogged() {
    return this._authService.isLogged();
  }

  registerLibroLetto() { 
    this._mediaRegistratoService.addLibroLetto({
      libro: this.libro,
      utenteId: Number(this._authService.getUserId())
    }).subscribe({
      next: libroLetto => this.libroLetto = libroLetto,
      error: e => alert('errore nella registrazione del libro letto: '+ e)
    });
  }

  deleteLibroLetto() {
    if(this.libroLetto){
      this._mediaRegistratoService.deleteLibroLetto(this.libroLetto.libroLettoId).subscribe({
      next: () => this.libroLetto = null,
      error: e => alert('errore nella registrazione del libro letto: '+ e)
      });
    }
 }
}
