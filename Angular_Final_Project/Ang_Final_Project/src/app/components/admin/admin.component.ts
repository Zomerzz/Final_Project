import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import { Router, RouterModule } from '@angular/router';

@Component({
    selector: 'app-admin',
    imports: [RouterModule],
    templateUrl: './admin.component.html',
    styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
    
    private _authService = inject(AuthService);
    private _router = inject(Router)
    ngOnInit(): void {
        if(!(this._authService.isLogged())){
            this._router.navigate(['/home'])
        }
    }

    showUsertable(){
        alert("a")
    }


}
