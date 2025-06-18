import { Component, Input } from '@angular/core';
import { Utente } from '../../model/Utente';
import { UtenteNoPass } from '../../model/UtenteNoPass';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-users-list',
  imports: [RouterLink],
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.css'
})
export class UsersListComponent {
  @Input('utenti') utenti: UtenteNoPass[] = [];

}
