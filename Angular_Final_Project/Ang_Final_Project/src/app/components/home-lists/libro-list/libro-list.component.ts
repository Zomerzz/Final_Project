import { Component, inject, Input} from '@angular/core';
import { Libro } from '../../../model/Libro';
import { Router, RouterLink, RouterModule } from '@angular/router';

@Component({
    selector: 'app-libro-list',
    imports: [RouterLink],
    templateUrl: './libro-list.component.html',
    styleUrl: './libro-list.component.css'
})
export class LibroListComponent {
    private _router = inject(Router);

    @Input('libri')
    libri:Libro[]= [];
    libro!: Libro;

      isLibriVuoti(): boolean {
    return this.libri.length === 0;
  }
  getBarColor(libro:Libro): string {
      const voto = libro.voto;
      if (voto >= 75) return 'rgba(0, 255, 149, 0.5)';
      if (voto >= 50) return 'rgba(255, 238, 0, 0.5)';
      if (voto >= 25) return 'rgba(255, 0, 0, 0.5)';
      return '#FF453A';
  }
}

