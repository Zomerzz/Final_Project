import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';

@Component({
    selector: 'app-header-component',
    imports: [RouterModule],
    templateUrl: './header-component.html',
    styleUrl: './header-component.css'
})
export class HeaderComponent {
    private _router = inject(Router);
    private _authService = inject(AuthService);

    get isAlreadylogged(){
        return this._authService.isLogged();
    }
    get isinLoginPage (){
        return this._router.url.includes('/login') ||this._router.url.includes('/register');
    }
}
