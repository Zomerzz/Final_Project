import { Component, Input} from '@angular/core';
import { Libro } from '../../../model/Libro';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
    selector: 'app-libro-list',
    imports: [RouterLink],
    templateUrl: './libro-list.component.html',
    styleUrl: './libro-list.component.css'
})
export class LibroListComponent {
    @Input('libri')
    libri:Libro[]= [];
}
