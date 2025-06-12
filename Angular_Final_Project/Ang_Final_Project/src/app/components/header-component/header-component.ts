import { Component, inject } from '@angular/core';
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

    get isAlreadylogged() {
        return this._authService.isLogged();
    }
    get isinLoginPage() {
        return this._router.url.includes('/login') || this._router.url.includes('/register');
    }

    send() {
        if(this.titolo.trim()){
            this._router.navigate(['/home'],{ queryParams: { q: this.titolo.trim()}});
        }
    }
}
