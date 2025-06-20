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
import { Tag } from '../../../model/Tag';
import { TagService } from '../../../services/searchService';

@Component({
    selector: 'app-book-details',
    imports: [CommonModule, RecensioniListComponent, AddRecensioneComponent, RecensioneCardComponent],
    templateUrl: './book-details.component.html',
    styleUrl: './book-details.component.css'
})
export class BookDetailsComponent implements OnInit {
    type = 'libro';
    libroId !:number;
    libro!: Libro;
    libroLetto!: LibroLetto | null;
    recensioni: Recensione[] = [];
    tags:Tag[] = [];

    private _activatedRoute = inject(ActivatedRoute);
    private _recensioneService = inject(RecensioneService);
    private _authService = inject(AuthService);
    private _libroService = inject(LibroService);
    private _mediaRegistratoService = inject(MediaRegistratoService);
    private _tagService = inject(TagService);

    ngOnInit(): void {
        if (history.state && history.state.libro) {
            this.libro = history.state.libro;
            this.libroId = this.libro.id;
            this.loadRecensioni(this.libroId);
            this.getLibroLetto();
            this.loadTags();
            console.log(this.libro.autori);
            
        } else {
            const id = this._activatedRoute.snapshot.paramMap.get("id");
            if (id != null) {
                this.libroId = Number(id);
                if (this.libroId > 0 && !isNaN(this.libroId)) {
                    this._libroService.getById(this.libroId).subscribe({
                        next: l => {
                            this.libro = l;
                            this.loadRecensioni(this.libroId);
                            this.getLibroLetto();
                            this.loadTags();
                            console.log(this.libro.autori);   
                        },
                        error: e => alert('errore nel caricamento del libro')
                    });
                }
            }
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
                next: () => {
                    if(this.libroLetto?.recensione){
                        this.recensioni = this.recensioni.
                        filter(recensione => recensione.recensioneId != this.libroLetto?.recensione.recensioneId);
                    }
                    this.libroLetto = null;
                },
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

    addNewRecensione(rec:Recensione){
        this.recensioni.push(rec);
        if(this.libroLetto){
            this.libroLetto.recensione = rec;
        }
    }

    getBarColor(): string {
        const voto = this.libro.voto;
        if (voto >= 75) return '#30D158';
        if (voto >= 50) return '#FFD60A';
        if (voto >= 25) return '#FF9F0A';
        return '#FF453A';
    }

    getLibroLetto(){
        if (this.isAlreadylogged) {
            this._mediaRegistratoService.getLibroLettoByLibroIdAndUtenteId(this.libro.id, Number(this._authService.getUserId()))
                .subscribe({
                    next: ll => this.libroLetto = ll,
                    error: e => this.libroLetto = null
                });
        }
    }

    loadTags(){
        this._tagService.loadTagsByOperaId(this.libroId, this.type).subscribe({   
            next: tagDb => this.tags = tagDb,
            error:e => alert("errore caricamento tags")
        });
    }
}
