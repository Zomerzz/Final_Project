import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { SearchByProductNameComponent } from "../search-by-product-name_component/search-by-product-name.component";
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-header-component',
    imports: [RouterModule, FormsModule],
    templateUrl: './header-component.html',
    styleUrl: './header-component.css'
})
export class HeaderComponent {
    private _router = inject(Router);
    private _authService = inject(AuthService);
    titolo!: string;
    utente!: string;
    selectedSearch: string = 'titoli';

    get isAlreadylogged() {
        return this._authService.isLogged();
    }
    get isinLoginPage() {
        return this._router.url.includes('/login') || this._router.url.includes('/register');
    }

    send() {
        if (this.selectedSearch == 'titoli') {
            this._router.navigate(['/home'], { queryParams: { q: this.titolo } });
        } else if (this.selectedSearch == 'utenti') {
            this._router.navigate(['/home'], { queryParams: { utente: this.utente } });
        }
        this.selectedSearch = 'titoli';
        this.titolo = '';
        this.utente = '';
    }

    navToHome(){
        this._router.navigate(['/home'],{queryParams: {typeofmedia: "tutti"} })
    }
}
