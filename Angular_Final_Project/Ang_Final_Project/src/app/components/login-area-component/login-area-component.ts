import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';

@Component({
    selector: 'app-login-area-component',
    imports: [ReactiveFormsModule, RouterModule],
    templateUrl: './login-area-component.html',
    styleUrl: './login-area-component.css'
})
export class LoginAreaComponent {
    formBuilder = inject(FormBuilder);
    loginForm:FormGroup;
    private _authService = inject(AuthService);
    private _router = inject(Router)

    constructor(){
        this.loginForm = this.formBuilder.group({
            email:["",[Validators.required]],
            password:["",[Validators.required]],
        })
    }

    onSubmit(){
        this._authService.login(this.loginForm.value);
    }
}
