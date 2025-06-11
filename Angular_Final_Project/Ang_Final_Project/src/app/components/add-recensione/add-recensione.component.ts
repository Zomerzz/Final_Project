import { Component, inject } from '@angular/core';
import { RecensioneService } from '../../services/RecensioneService';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Recensione } from '../../model/Recensione';

@Component({
  selector: 'app-add-recensione',
  imports: [FormsModule],
  templateUrl: './add-recensione.component.html',
  styleUrl: './add-recensione.component.css'
})
export class AddRecensioneComponent {
  recensione: Partial<Recensione> = {voto: 0, recensione: ''}
  private _route = inject(ActivatedRoute);
  private _recensioneService = inject(RecensioneService);
  private _router = inject(Router);

}
