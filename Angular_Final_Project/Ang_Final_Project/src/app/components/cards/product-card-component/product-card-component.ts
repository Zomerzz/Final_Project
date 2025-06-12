import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Videogioco } from '../../../model/Videogioco';

@Component({
  selector: 'app-product-card-component',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-card-component.html',
  styleUrls: ['./product-card-component.css']
})
export class ProductCardComponent {
  @Input() videogioco!: Videogioco;
}
