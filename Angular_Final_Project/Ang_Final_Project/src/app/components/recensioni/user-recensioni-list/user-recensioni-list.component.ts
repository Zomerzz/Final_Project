import { Component, inject, Input, OnInit } from '@angular/core';
import { FilmVisto } from '../../../model/FilmVisto';
import { LibroLetto } from '../../../model/LibroLetto';
import { VideogiocoGiocato } from '../../../model/VideogiocoGiocato';
import { Videogioco } from '../../../model/Videogioco';
import { Utente } from '../../../model/Utente';
import { UtenteNoPass } from '../../../model/UtenteNoPass';
import { AuthService } from '../../../services/AuthService';

@Component({
  selector: 'app-user-recensioni-list',
  imports: [],
  templateUrl: './user-recensioni-list.component.html',
  styleUrl: './user-recensioni-list.component.css'
})
export class UserRecensioniListComponent implements OnInit {
  
  @Input("filmRecensiti") filmRecensiti!: FilmVisto[];
  @Input("libriRecensiti") libriRecensiti!: LibroLetto[];
  @Input("videogiochiRecensiti") videogiochiRecensiti!: VideogiocoGiocato[];
  @Input("utente") utente!: UtenteNoPass;
  utenteLoggatoId!: number;

  private _authService = inject(AuthService);

  ngOnInit(): void {
    this.utenteLoggatoId = Number(this._authService.getUserId);
  }


  getBarColor(id:number): string {
        const voto = id;
        if (voto >= 75) return 'rgba(0, 255, 149, 0.5)';
        if (voto >= 50) return 'rgba(255, 238, 0, 0.5)';
        if (voto >= 25) return 'rgba(255, 0, 0, 0.5)';
        return '#FF453A';
    }
}
