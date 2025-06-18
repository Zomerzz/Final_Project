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
import { forkJoin } from 'rxjs';
import { UtenteNoPass } from '../../model/UtenteNoPass';
import { UtenteService } from '../../services/UtenteService';
import { UsersListComponent } from '../users-list/users-list.component';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [CommonModule, LibroListComponent, 
        FilmListComponent, VideogiocoListComponent, 
        AdvancedSearchComponent, UsersListComponent],
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    Raccomandation!:boolean;
    searchTerm!: string;
    listaLibri!: Libro[];
    listaFilm!: Film[];
    listaVideogiochi!: Videogioco[];
    listaUtenti!: UtenteNoPass[];

    private _activatedRoute = inject(ActivatedRoute);
    private _libroService: LibroService = inject(LibroService);
    private _videogiocoService: VideogiocoService = inject(VideogiocoService);
    private _filmService: FilmService = inject(FilmService);
    private _utenteService: UtenteService = inject(UtenteService);
    private _authService: AuthService = inject(AuthService);
    private _router = inject(Router);


    ngOnInit(): void {

        this._activatedRoute.queryParams.subscribe({
            next: params => {
                const titolo = params['q'];
                const utente = params['utente'];
                const isLogged = this._authService.isLogged();
                this.clearLists();
                this.Raccomandation = false;
                if (titolo) {
                    this.fetchByName(titolo);
                } else if (utente) {
                    this.clearLists();
                    this.fetchUtentiByName(utente);
                } else {
                    if (!isLogged) {
                        this.fetchALl();
                    } else {
                        this.fetchRecommendedForLoggedUsers();
                    }
                }
            }
        })
        

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
        this.Raccomandation = false;
        this.listaFilm = [];
        this.listaLibri = [];
        this.listaVideogiochi = [];
        console.log("Advanced");
        // this._router.navigate(['/home']); //se la scommento funziona
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

    fetchUtentiByName(utente: string){
        this._utenteService.getUtentiByUsername(utente).subscribe({
            next: list => this.listaUtenti = list,
            error: e => console.log('------------ errore nel cariamento utenti ---------------')
        });
    }

    createQueryString(filters: Partial<SearchModel>): string {
        this.Raccomandation = false;
        let queryString: string = '?';
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
    fetchALl() {
        console.log("All");
        const tipoMedia = 'tutti';
        const sort = 'orderByDataPubblicazioneDesc';
        const filters: Partial<SearchModel> = { tipoMedia, sort };
        this.fetchPreSearchResults(filters);
        return;
    }
    
fetchRecommendedForLoggedUsers() {
    const id = +this._authService.getUserId()!;
    forkJoin({
        film: this._filmService.getConsigliati(id),
        videogiochi: this._videogiocoService.getConsigliati(id),
        libri: this._libroService.getConsigliati(id)
    }).subscribe({
        next: ({ film, videogiochi, libri }) => {
            this.listaFilm = film;
            this.listaVideogiochi = videogiochi;
            this.listaLibri = libri;
            if (film.length === 0 && videogiochi.length === 0 && libri.length === 0) {
                this.fetchALl();
            }else{
                this.Raccomandation = true;
            }

        },
        error: e => console.log("Errore in consigliati", e)
    });
}
    fetchByName(titolo: string) {

        console.log("byName");
        this._libroService.findByName(titolo).subscribe({
            next: listaLibroDb => {
                this.listaLibri = listaLibroDb;
                
            },
            error: e => {
                console.log("====================================================");
                console.log("la ricerca findByName libro non ha trovato risultati");
                console.log("====================================================");

            }
        });
        this._videogiocoService.getByName(titolo).subscribe({
            next: listaVideogiocoDb => {
                this.listaVideogiochi = listaVideogiocoDb;
                
            },
            error: e => {
                console.log("=========================================================");
                console.log("la ricerca findByName videogioco non ha trovato risultati");
                console.log("=========================================================");
            }
        });
        this._filmService.findByName(titolo).subscribe({
            next: listaFilmDb => {
                this.listaFilm = listaFilmDb;
                
            },
            error: e => {
                console.log("===================================================");
                console.log("la ricerca findByName film non ha trovato risultati");
                console.log("===================================================");
            }
        })
    }
    clearLists() {
        this.listaLibri = [];
        this.listaFilm = [];
        this.listaVideogiochi = [];
        this.listaUtenti = [];
    }
}

