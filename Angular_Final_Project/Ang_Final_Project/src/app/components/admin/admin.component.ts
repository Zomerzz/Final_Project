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
    private _showUserTable:boolean = false;
    ngOnInit(): void {
        if(!(this._authService.isLogged())){
            this._router.navigate(['/home'])
        }
    }

    showUsertab(){
        (this._showUserTable)?this._showUserTable=false:this._showUserTable=true;
        
    }

    get showuserTable(){
        return this._showUserTable;
    }


}
