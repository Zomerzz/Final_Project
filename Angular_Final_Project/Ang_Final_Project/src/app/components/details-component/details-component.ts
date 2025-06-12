import { Component, OnInit } from '@angular/core';
import { Videogioco } from '../../model/Videogioco';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { VideogiocoService } from '../../services/videogioco.service';

@Component({
  selector: 'app-details-components',
  imports: [CommonModule],
  templateUrl: './details-component.html',
  styleUrl: './details-component.css'
})
export class DetailsComponent implements OnInit {
  videogioco!: Videogioco;

  constructor(
    private route: ActivatedRoute,
    private videoGiocoService: VideogiocoService
  ) {}

  ngOnInit(): void {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      this.videoGiocoService.getById(id).subscribe({
        next: data => this.videogioco = data,
        error: err => console.error('Errore nel caricamento dei dettagli', err)

      })
  }

}
