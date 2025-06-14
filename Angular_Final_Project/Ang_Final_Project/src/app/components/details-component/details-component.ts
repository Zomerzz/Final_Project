import { Component, inject, Input, OnInit } from '@angular/core';
import { Videogioco } from '../../model/Videogioco';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { VideogiocoService } from '../../services/videogioco.service';
import { Recensione } from '../../model/Recensione';
import { RecensioneService } from '../../services/RecensioneService';
import { Product } from '../../model/Product';
import { RecensioniListComponent } from '../recensioni/recensioni-list/recensioni-list.component';

@Component({
  selector: 'app-details-components',
  imports: [CommonModule, RecensioniListComponent],
  templateUrl: './details-component.html',
  styleUrl: './details-component.css'
})
export class DetailsComponent implements OnInit {
  private _recensioniService = inject(RecensioneService);
  id!: number;
  //product!: Product;
  videogioco!: Videogioco;
  recensioni!: Recensione[];

  constructor(
    private route: ActivatedRoute,
    private videoGiocoService: VideogiocoService
  ) {}

  ngOnInit(): void {
      this.id = Number(this.route.snapshot.paramMap.get('id'));
      this.videoGiocoService.getById(this.id).subscribe({
        next: data => this.videogioco = data,
        error: err => console.error('Errore nel caricamento dei dettagli', err)
      })
  }

  loadRecensioni() {
    // this._recensioniService.getRecensioni(this.product.tipo, this.id).subscribe({
    //   next: list => this.recensioni = list,
    //   error: e => alert('errore nel caricamento delle recensioni ' + e)
    // })
  }

}
