import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Videogioco } from '../../../model/Videogioco';

@Component({
  selector: 'app-videogioco-list',
  imports: [RouterLink],
  templateUrl: './videogioco-list.component.html',
  styleUrl: './videogioco-list.component.css'
})
export class VideogiocoListComponent {
  @Input('videogiochi')
  videogiochi:Videogioco[] = [];


  isVideogiochiVuoti(): boolean {
    return this.videogiochi.length === 0;
  }
  getBarColor(gioco:Videogioco): string {
        const voto = gioco.voto;
        if (voto >= 75) return 'rgba(0, 255, 149, 0.5)';
        if (voto >= 50) return 'rgba(255, 238, 0, 0.5)';
        if (voto >= 25) return 'rgba(255, 0, 0, 0.5)';
        return '#FF453A';
    }
}
