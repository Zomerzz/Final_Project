import { Component, Input } from '@angular/core';
import { Recensione } from '../../../model/Recensione';

@Component({
  selector: 'app-recensione-card',
  imports: [],
  templateUrl: './recensione-card.component.html',
  styleUrl: './recensione-card.component.css'
})
export class RecensioneCardComponent {
  @Input('recensione') rec!: Partial<Recensione>;
}
