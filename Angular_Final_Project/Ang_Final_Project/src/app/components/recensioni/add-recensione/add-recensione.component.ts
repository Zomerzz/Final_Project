import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { RecensioneService } from '../../../services/RecensioneService';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, MaxValidator, MinValidator, ReactiveFormsModule, Validators } from '@angular/forms';
import { RecensioneRequest } from '../../../model/RecensioneRequest';
import { AuthService } from '../../../services/AuthService';
import { Recensione } from '../../../model/Recensione';

@Component({
  selector: 'app-add-recensione',
  imports: [ReactiveFormsModule],
  templateUrl: './add-recensione.component.html',
  styleUrl: './add-recensione.component.css'
})
export class AddRecensioneComponent{
  @Input('operaId') operaId!: number;
  @Input('type') type!: string;
  @Output('addedRecensione') addedRecensione = new EventEmitter<Recensione>();
  formBuilder = inject(FormBuilder);
  recensioneForm: FormGroup;
  private _authService = inject(AuthService);
  private _recensioneService = inject(RecensioneService);
  private _router = inject(Router);
  private _route = inject(ActivatedRoute);

  constructor(){
    this.recensioneForm = this.formBuilder.group({
      voto:["",[Validators.required]],
      recensione:[""]
    });
  }

  onSubmit(){
    const recensioneRequest: Partial<RecensioneRequest> = {
      "voto": this.recensioneForm.value.voto,
      "recensione": this.recensioneForm.value.recensione,
      "type": this.type,
      "operaId": this.operaId,
      "utenteId": Number(this._authService.getUserId())
    };
    this._recensioneService.addRecensione(recensioneRequest).subscribe({
      next: recensione => this.addedRecensione.emit(recensione),
      error: e => alert('Errore nella creazione della recensione')
    })
  }

  get voto(){
    return this.recensioneForm.get("voto");
  }
}
