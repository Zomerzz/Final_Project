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
}
