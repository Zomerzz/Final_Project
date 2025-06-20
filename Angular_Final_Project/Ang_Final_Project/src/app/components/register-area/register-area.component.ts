import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { LoginRequest } from '../../model/LoginRequest';

@Component({
    selector: 'app-register-area',
    imports: [ReactiveFormsModule,RouterModule],
    templateUrl: './register-area.component.html',
    styleUrl: './register-area.component.css'
})
export class RegisterAreaComponent {
    formBuilder = inject(FormBuilder);
    registerForm: FormGroup;
    private _authService = inject(AuthService);
    private _router = inject(Router)

    constructor() {
        this.registerForm = this.formBuilder.group({
            name:["",[Validators.required]],
            email: ["", [Validators.required]],
            password: ["", [Validators.required]]
        })
    }

    onSubmit(){
        this._authService.register(this.registerForm.value).subscribe({
            next: (authToken) =>{
                console.log(authToken.token);
                
                
                localStorage.setItem("jwt",authToken.token );
                this._router.navigate(['/home']);
            },error: e => alert('Errore nella registrazione')
        });
    }
}
