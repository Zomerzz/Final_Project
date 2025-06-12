import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import { Router, RouterModule } from '@angular/router';

@Component({
    selector: 'app-log-out',
    imports: [RouterModule],
    templateUrl: './log-out.component.html',
    styleUrl: './log-out.component.css'
})
export class LogOutComponent implements OnInit{
    
    private _authService = inject(AuthService);
    private _router = inject(Router)

    ngOnInit(): void {
        this._authService.logout();
        this._router.navigate(['/home'])
    }
}
