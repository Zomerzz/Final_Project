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


}
