import { Component, inject, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { LibroService } from '../../services/LibroService';
import { VideogiocoService } from '../../services/videogioco.service';
import { FilmService } from '../../services/FilmService';
import { Libro } from '../../model/Libro';
import { Film } from '../../model/Film';
import { Videogioco } from '../../model/Videogioco';
import { LibroListComponent } from '../home-lists/libro-list/libro-list.component';
import { FilmListComponent } from "../home-lists/film-list/film-list.component";
import { VideogiocoListComponent } from '../home-lists/videogioco-list/videogioco-list.component';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [CommonModule, LibroListComponent, FilmListComponent,VideogiocoListComponent],
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    searchTerm!: string;
    listaLibri!: Libro[];
    listaFilm!: Film[];
    listaVideogiochi!: Videogioco[];

    private _activatedRoute = inject(ActivatedRoute);
    private _libroService: LibroService = inject(LibroService);
    private _videogiocoService: VideogiocoService = inject(VideogiocoService);
    private _filmService: FilmService = inject(FilmService);

    ngOnInit(): void {
        this._activatedRoute.queryParams.subscribe(params => {
            const titolo = params['q'];
            if (titolo) {
                this._libroService.findByName(titolo).subscribe({
                    next: listaLibroDb => {
                        this.listaLibri = listaLibroDb;
                    },
                    error: e => {
                        console.log("|====================================================|");
                        console.log("|la ricerca findByName libro non ha trovato risultati|");
                        console.log("|====================================================|");

                    }
                });
                this._videogiocoService.getByName(titolo).subscribe({
                    next: listaVideogiocoDb => {
                        this.listaVideogiochi = listaVideogiocoDb;
                    },
                    error: e => {
                        console.log("|=========================================================|");
                        console.log("|la ricerca findByName videogioco non ha trovato risultati|");
                        console.log("|=========================================================|");

                    }
                });
                this._filmService.findByName(titolo).subscribe({
                    next: listaFilmDb => {
                        this.listaFilm = listaFilmDb;
                    },
                    error: e => {
                        console.log("|===================================================|");
                        console.log("|la ricerca findByName film non ha trovato risultati|");
                        console.log("|===================================================|");

                    }
                })
            }
        });

    }
}

