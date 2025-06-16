import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Film } from '../../../model/Film';

@Component({
    selector: 'app-film-list',
    imports: [RouterLink],
    templateUrl: './film-list.component.html',
    styleUrl: './film-list.component.css'
})
export class FilmListComponent {
    @Input('films')
    films: Film[] = [];


    isfilmVuoti(): boolean {
        return this.films.length === 0;
    }

    getBarColor(film: Film): string {
        const voto = film.voto;
        if (voto >= 75) return 'rgba(0, 255, 149, 0.5)';
        if (voto >= 50) return 'rgba(255, 238, 0, 0.5)';
        if (voto >= 25) return 'rgba(255, 0, 0, 0.5)';
        return '#FF453A';
    }
}
