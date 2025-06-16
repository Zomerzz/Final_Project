import { Component, Inject, inject, Input, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RecensioneCardComponent } from '../recensione-card/recensione-card.component';
import { Recensione } from '../../../model/Recensione';
import { RecensioneService } from '../../../services/RecensioneService';

@Component({
  selector: 'app-recensioni-list',
  imports: [CommonModule, RecensioneCardComponent],
  templateUrl: './recensioni-list.component.html',
  styleUrl: './recensioni-list.component.css'
})
export class RecensioniListComponent {
  @Input('recensioni') list: Recensione[] = [];
  private _route = inject(ActivatedRoute);
  private _recensioneService: RecensioneService = inject(RecensioneService);
  private _router = inject(Router);

}
