import { Component, inject, OnInit } from '@angular/core';
import { RecensioneService } from '../../services/RecensioneService';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, MaxValidator, MinValidator, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-recensione',
  imports: [ReactiveFormsModule],
  templateUrl: './add-recensione.component.html',
  styleUrl: './add-recensione.component.css'
})
export class AddRecensioneComponent{
  formBuilder = inject(FormBuilder);
  recensioneForm: FormGroup;
  private _recensioneService = inject(RecensioneService);
  private _router = inject(Router);

  constructor(){
    this.recensioneForm = this.formBuilder.group({
      voto:["",[Validators.required]],
      recensione:[""]
    });
  }

  onSubmit(){
    this._recensioneService.addRecensione(this.recensioneForm.value).subscribe({
      next: (recensione) => {
        // TODO bisogna trovare un modo piu bello dell'alert
        alert('Recensione creata con id ' + recensione.recensioneId);
        // dove mi porta il router ora??
      },
      error: e => alert('Errore nella creazione della recensione')
    })
  }

  get voto(){
    return this.recensioneForm.get("voto");
  }
}
