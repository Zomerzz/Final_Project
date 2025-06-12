import { Component, OnInit } from '@angular/core';
import { VideogiocoService } from '../../../services/videogioco.service';
import { Videogioco } from '../../../model/Videogioco';
import { ProductCardComponent } from '../../cards/product-card-component/product-card-component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-list-components',
  standalone: true,
  imports: [CommonModule , ProductCardComponent],
  templateUrl: './product-list-component.html',
  styleUrl: './product-list-component.css'
})
export class ProductListComponent implements OnInit {
  videogiochi: Videogioco[] = [];
  errorMessage: string = '';  // Aggiungi variabile per errori

  constructor(private videoGiocoService: VideogiocoService) {}

  ngOnInit(): void {
    this.getAllVideogiochi();
  }

  getAllVideogiochi(): void {
    this.videoGiocoService.getAll().subscribe({
      next: (data) => {
        this.videogiochi = data;
      },
      error: (error) => {
        console.error('C\'è stato un errore durante il recupero dei videogiochi:', error);
        this.errorMessage = 'Impossibile caricare i dati dei videogiochi. Riprova più tardi.';  // Imposta un messaggio di errore
      }
    });
  }
}

