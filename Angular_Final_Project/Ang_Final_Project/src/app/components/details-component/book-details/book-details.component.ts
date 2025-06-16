import { Component, inject, OnInit } from '@angular/core';
import { Libro } from '../../../model/Libro';
import { ActivatedRoute, Router } from '@angular/router';
import { RecensioniListComponent } from "../../recensioni/recensioni-list/recensioni-list.component";
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';
import { CommonModule } from '@angular/common';
import { MediaRegistratoService } from '../../../services/MediaRegistratoService';
import { AuthService } from '../../../services/AuthService';
import { LibroLetto } from '../../../model/LibroLetto';
import { AddRecensioneComponent } from '../../recensioni/add-recensione/add-recensione.component';
import { RecensioneCardComponent } from "../../recensioni/recensione-card/recensione-card.component";
import { LibroService } from '../../../services/LibroService';

@Component({
    selector: 'app-book-details',
    imports: [CommonModule, RecensioniListComponent, AddRecensioneComponent, RecensioneCardComponent],
    templateUrl: './book-details.component.html',
    styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit {
    type = 'libro';
    libro!: Libro;
    libroLetto!: LibroLetto | null;
    recensioni!: Recensione[];

    private _activatedRoute = inject(ActivatedRoute);
    private _recensioneService = inject(RecensioneService);
    private _authService = inject(AuthService);
    private _libroService = inject(LibroService);
    private _mediaRegistratoService = inject(MediaRegistratoService);

    ngOnInit(): void {
        // Recupera i dati direttamente da history.state
        if (history.state && history.state.libro) {
            this.libro = history.state.libro;
            this.loadRecensioni(this.libro.id);
        } else {
            const id = this._activatedRoute.snapshot.paramMap.get("id");
            if (id != null) {
                const libroId = Number(id);
                if (libroId > 0 && !isNaN(libroId)) {
                    this._libroService.getById(libroId).subscribe({
                        next: l => {
                            this.libro = l;
                            this.loadRecensioni(libroId);
                        },
                        error: e => alert('errore nel caricamento del libro')
                    });
                }
            }
        }

        if (this.isAlreadylogged) {
            this._mediaRegistratoService.getLibroLettoByLibroIdAndUtenteId(this.libro.id, Number(this._authService.getUserId()))
                .subscribe({
                    next: ll => this.libroLetto = ll,
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
            next: ll => this.libroLetto = ll,
            error: e => alert('errore nella registrazione del libro letto: ' + e)
        });
    }

    deleteLibroLetto() {
        if (this.libroLetto) {
            this._mediaRegistratoService.deleteLibroLetto(this.libroLetto.libroLettoId).subscribe({
                next: () => this.libroLetto = null,
                error: e => alert('errore nella registrazione del libro letto: ' + e)
            });
        }
    }

    loadRecensioni(id: number) {
        this._recensioneService.getRecensioni(this.type, id).subscribe({
            next: list => this.recensioni = list,
            error: e => alert('Errore nel caricamento delle recensioni')
        });
    }

    getBarColor(): string {
        const voto = this.libro.voto;
        if (voto >= 75) return '#30D158';
        if (voto >= 50) return '#FFD60A';
        if (voto >= 25) return '#FF9F0A';
        return '#FF453A';
    }
}
