import { Component, Input } from '@angular/core';
import { FilmVisto } from '../../../model/FilmVisto';
import { LibroLetto } from '../../../model/LibroLetto';
import { VideogiocoGiocato } from '../../../model/VideogiocoGiocato';

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



}
