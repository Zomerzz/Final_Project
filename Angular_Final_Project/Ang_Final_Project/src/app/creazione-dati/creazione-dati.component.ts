import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
    selector: 'app-creazione-dati',
    imports: [],
    templateUrl: './creazione-dati.component.html',
    styleUrl: './creazione-dati.component.css'
})
export class CreazioneDatiComponent {
    formBuilder = inject(FormBuilder);
    autori: FormGroup;
    film: FormGroup;
    libri: FormGroup;
    giochi: FormGroup;
    private _router = inject(Router)

    constructor() {
        this.autori = this.formBuilder.group({
            nome: ["", [Validators.required]],
            secondonome: [],
            cognome: [""],
            dataNascita: [""],
            nazionalita: [""]
        });
        this.film = this.formBuilder.group({
            nome: ["", [Validators.required]],
            secondonome: [],
            cognome: [""],
            dataNascita: [""],
            nazionalita: [""]
        })
        this.libri = this.formBuilder.group({
            nome: ["", [Validators.required]],
            secondonome: [],
            cognome: [""],
            dataNascita: [""],
            nazionalita: [""]
        })
        this.giochi = this.formBuilder.group({
            nome: ["", [Validators.required]],
            secondonome: [],
            cognome: [""],
            dataNascita: [""],
            nazionalita: [""]
        })
    }

    onAutoriSubmit() {

    }
}
