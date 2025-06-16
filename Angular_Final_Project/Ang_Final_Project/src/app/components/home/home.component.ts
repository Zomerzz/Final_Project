import { Component, Inject, inject, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { LibroService } from '../../services/LibroService';
import { VideogiocoService } from '../../services/VideogiocoService';
import { FilmService } from '../../services/FilmService';
import { Libro } from '../../model/Libro';
import { Film } from '../../model/Film';
import { Videogioco } from '../../model/Videogioco';
import { LibroListComponent } from '../home-lists/libro-list/libro-list.component';
import { FilmListComponent } from "../home-lists/film-list/film-list.component";
import { VideogiocoListComponent } from '../home-lists/videogioco-list/videogioco-list.component';
import { AuthService } from '../../services/AuthService';
import { AdvancedSearchComponent } from "../advanced-search/advanced-search.component";
import { SearchModel } from '../../model/SearchModel';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [CommonModule, LibroListComponent, FilmListComponent, VideogiocoListComponent, AdvancedSearchComponent],
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
    private _authService: AuthService = inject(AuthService);
    private _router = inject(Router);

    ngOnInit(): void {
        const isLogged = this._authService.isLogged();

        this._activatedRoute.queryParams.subscribe(params => {
            const hasParams = Object.keys(params).length > 0

            const tipoMedia = params['tipoMedia'] || 'tutti';
            const sort = params['sort'] || 'orderByDataPubblicazioneDesc';

            const filters: Partial<SearchModel> = {tipoMedia, sort}

            if(!isLogged && !hasParams) {
                this._router.navigate(['/home'], { queryParams: filters });
            }

            this.fetchPreSearchResults(filters);
        })

        

        this._activatedRoute.queryParams.subscribe(params => {
            const titolo = params['q'];
            if (titolo) {
                this._libroService.findByName(titolo).subscribe({
                    next: listaLibroDb => {
                        this.listaLibri = listaLibroDb;
                    },
                    error: e => {
                        console.log("|====================================================|");
                        console.log("|la ricerca findByName ha dato degli errori          |");
                        console.log("|====================================================|");

                    }
                });
                this._videogiocoService.getByName(titolo).subscribe({
                    next: listaVideogiocoDb => {
                        this.listaVideogiochi = listaVideogiocoDb;
                    },
                    error: e => {
                        console.log("|=========================================================|");
                        console.log("|la ricerca findByName ha dato degli errori|");
                        console.log("|=========================================================|");

                    }
                });
                this._filmService.findByName(titolo).subscribe({
                    next: listaFilmDb => {
                        this.listaFilm = listaFilmDb;
                    },
                    error: e => {
                        console.log("|===================================================|");
                        console.log("|la ricerca findByName ha dato degli errori|");
                        console.log("|===================================================|");

                    }
                })
            }
        });
    }
    fetchPreSearchResults(filters: Partial<SearchModel>): void {
    const queryString = this.createQueryString(filters);

    this._libroService.findByFilters(queryString).subscribe({
        next: listaLibriDb => {
            this.listaLibri = listaLibriDb;
        },
        error: e => {
            console.log("Errore nella ricerca dei libri:", e);
        }
    });

    this._filmService.findByFilters(queryString).subscribe({
        next: listaFilmDb => {
            this.listaFilm = listaFilmDb;
        },
        error: e => {
            console.log("Errore nella ricerca dei film:", e);
        }
    });

    this._videogiocoService.getByFilters(queryString).subscribe({
        next: listaVideogiochiDb => {
            this.listaVideogiochi = listaVideogiochiDb;
        },
        error: e => {
            console.log("Errore nella ricerca dei videogiochi:", e);
        }
    });
}


    executeSearch(filters: Partial<SearchModel>) {
        this._router.navigate(['/home'], { queryParams: filters });
        if (filters.tipoMedia === 'tutti') {
            const queryString = this.createQueryString(filters);
            this._libroService.findByFilters(queryString).subscribe({
                next: listaLibriDb => {
                    this.listaLibri = listaLibriDb;
                },
                error: e => {
                    console.log("|==========================================================================|");
                    console.log("----" + filters.tipoMedia + "|la ricerca findWithFilters ha dato degli errori|");
                    console.log("|==========================================================================|");
                }
            });
            this._filmService.findByFilters(queryString).subscribe({
                next: listaFilmDb => {
                    this.listaFilm = listaFilmDb;
                },
                error: e => {
                    console.log("|==========================================================================|");
                    console.log("----" + filters.tipoMedia + "|la ricerca findWithFilters ha dato degli errori|");
                    console.log("|==========================================================================|");
                }
            });
            this._videogiocoService.getByFilters(queryString).subscribe({
                next: listaVideogiochiDb => {
                    this.listaVideogiochi = listaVideogiochiDb;
                },
                error: e => {
                    console.log("|==========================================================================|");
                    console.log("----" + filters.tipoMedia + "|la ricerca findWithFilters ha dato degli errori|");
                    console.log("|==========================================================================|");
                }
            });
        } else {
            if (filters.tipoMedia === 'libri') {
                this.listaFilm = [];
                this.listaVideogiochi = [];
                const queryString = this.createQueryString(filters);
                this._libroService.findByFilters(queryString).subscribe({
                    next: listaLibriDb => {
                        this.listaLibri = listaLibriDb;
                    },
                    error: e => {
                        console.log("|==========================================================================|");
                        console.log("----" + filters.tipoMedia + "|la ricerca findWithFilters ha dato degli errori|");
                        console.log("|==========================================================================|");
                    }
                });
            }
            if (filters.tipoMedia === 'film') {
                this.listaLibri = [];
                this.listaVideogiochi = [];
                const queryString = this.createQueryString(filters);
                this._filmService.findByFilters(queryString).subscribe({
                    next: listaFilmDb => {
                        this.listaFilm = listaFilmDb;
                    },
                    error: e => {
                        console.log("|==========================================================================|");
                        console.log("----" + filters.tipoMedia + "|la ricerca findWithFilters ha dato degli errori|");
                        console.log("|==========================================================================|");
                    }
                });
            }
            if (filters.tipoMedia === 'videogiochi') {
                this.listaFilm = [];
                this.listaLibri = [];
                const queryString = this.createQueryString(filters);
                this._videogiocoService.getByFilters(queryString).subscribe({
                    next: listaVideogiochiDb => {
                        this.listaVideogiochi = listaVideogiochiDb;
                    },
                    error: e => {
                        console.log("|==========================================================================|");
                        console.log("----" + filters.tipoMedia + "|la ricerca findWithFilters ha dato degli errori|");
                        console.log("|==========================================================================|");
                    }
                });
            }
        }

    }
    createQueryString(filters: Partial<SearchModel>): string {
        let queryString: string = '?';
        filters.tipoMedia = '';
        if (filters.tags?.length === 0) {
            filters.tags = undefined;
        }
        for (const key in filters) {
            const value = filters[key as keyof SearchModel];

            if (value !== undefined && value !== null && value !== '') {
                queryString += key + "=" + value + "&";
            }
        }
        return queryString;
    }


}

