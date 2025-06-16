import { Component, Input } from '@angular/core';
import { FilmVisto } from '../../../model/FilmVisto';
import { LibroLetto } from '../../../model/LibroLetto';
import { VideogiocoGiocato } from '../../../model/VideogiocoGiocato';
import { Videogioco } from '../../../model/Videogioco';

@Component({
  selector: 'app-user-recensioni-list',
  imports: [],
  templateUrl: './user-recensioni-list.component.html',
  styleUrl: './user-recensioni-list.component.css'
})
export class UserRecensioniListComponent {
  @Input("filmRecensiti") filmRecensiti!: FilmVisto[];
  @Input("libriRecensiti") libriRecensiti!: LibroLetto[];
  @Input("videogiochiRecensiti") videogiochiRecensiti!: VideogiocoGiocato[];


  getBarColor(id:number): string {
        const voto = id;
        if (voto >= 75) return 'rgba(0, 255, 149, 0.5)';
        if (voto >= 50) return 'rgba(255, 238, 0, 0.5)';
        if (voto >= 25) return 'rgba(255, 0, 0, 0.5)';
        return '#FF453A';
    }
}
