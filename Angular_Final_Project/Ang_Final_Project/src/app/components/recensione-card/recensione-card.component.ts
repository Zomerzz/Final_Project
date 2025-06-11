import { Component, Input } from '@angular/core';
import { Recensione } from '../../model/Recensione';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-recensione-card',
  imports: [RouterLink],
  templateUrl: './recensione-card.component.html',
  styleUrl: './recensione-card.component.css'
})
export class RecensioneCardComponent {
  @Input('recensione') rec!: Partial<Recensione>;
}
