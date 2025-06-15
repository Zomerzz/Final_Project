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
    films:Film[]=[];


    isfilmVuoti(): boolean {
        return this.films.length === 0;
    }
}
