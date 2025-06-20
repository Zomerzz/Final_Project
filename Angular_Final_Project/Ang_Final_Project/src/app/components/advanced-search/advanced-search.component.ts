import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { SearchModel } from '../../model/SearchModel';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UpperCasePipe } from '@angular/common';
import { Tag } from '../../model/Tag';
import { TagService } from '../../services/searchService';

@Component({
  selector: 'app-advanced-search',
  imports: [ReactiveFormsModule, UpperCasePipe],
  templateUrl: './advanced-search.component.html',
  styleUrl: './advanced-search.component.css'
})
export class AdvancedSearchComponent implements OnInit{
  @Output('search') search = new EventEmitter<Partial<SearchModel>>();
  currentTags: Tag[] = [];
  currentGeneri: Tag[] = [];
  form!: FormGroup;
  mediaTypes = ['tutti', 'libri', 'film', 'videogiochi'];
  currentMedia = 'tutti';
  fb: FormBuilder = inject(FormBuilder);
  searchService: TagService = inject(TagService);
  ngOnInit(): void {
    this.form = this.fb.group({
      tipoMedia: [this.currentMedia],
      tags: new FormControl<number[]>([]),
      generi: new FormControl<number[]>([])
    });
    this.form.get('tipoMedia')!.valueChanges.subscribe(value => {
      this.currentMedia = value;
      this.configureFormControls(value);
      this.loadTagsByGenere(true);
      this.loadTagsByGenere(false);
    });
    this.configureFormControls(this.currentMedia);
    console.log("ciao")
    this.loadTagsByGenere(true);
    this.loadTagsByGenere(false);
  }
  configureFormControls(tipoMedia: string) {
    const keepAlways = ['tipoMedia', 'genres', 'tags'];
    const allControls = {
      titolo: new FormControl(''),
      numeroPagine: new FormControl(null),
      autoreNome: new FormControl(''),
      casaEditriceNome: new FormControl(''),
      casaDiProduzione: new FormControl(''),
      casaDiPubblicazione: new FormControl(''),
      minData: new FormControl(''),
      maxData: new FormControl(''),
      minVoto: new FormControl(null),
      maxVoto: new FormControl(null),
      minOreDiGiocoStoriaPrincipale: new FormControl(null),
      maxOreDiGiocoStoriaPrincipale: new FormControl(null),
      generi: new FormControl(null),
      tags: new FormControl(null),
      sort: new FormControl('')
    };
    const all: (keyof typeof allControls) [] = ['titolo', 'minData', 'maxData', 'minVoto', 'maxVoto', 'generi', 'tags', 'sort'];
    const allowed: { [key: string]: (keyof typeof allControls) [] } = {
      tutti: all,
      libri: [...all, 'numeroPagine', 'casaEditriceNome', 'autoreNome'],
      film: [...all, 'casaDiProduzione', 'casaDiPubblicazione','autoreNome'],
      videogiochi: [...all, 'casaDiProduzione', 'casaDiPubblicazione', 'minOreDiGiocoStoriaPrincipale', 'maxOreDiGiocoStoriaPrincipale']
    };
    Object.keys(allControls).forEach(key => {
      if(!keepAlways.includes(key) && this.form.contains(key)) {
        this.form.removeControl(key);
      }
    });
    const currentControls = allowed[tipoMedia];
    currentControls.forEach(key => {
      this.form.addControl(key, allControls[key]);
    });
  }
  loadTagsByGenere(isGenere:boolean) {
    console.log("inizio metodo");
    
    this.searchService.loadByIsGenere(isGenere).subscribe({
      next: tags => {
        if(!isGenere){
          this.currentTags = tags;
          console.log(this.currentTags);
          
        }else {
          this.currentGeneri = tags;
          console.log(this.currentGeneri);
        }
      },

      error: er => alert(er)
    });
  }
  

  onSubmit() {
    console.log(this.form.value)
    this.search.emit(this.form.value);
  }
  onTagCheckboxChange(event: Event) {
    const checkbox = event.target as HTMLInputElement;
    const selectedTags = this.tags?.value as number[] || [];
    if(checkbox.checked) {
      this.tags?.setValue([...selectedTags, +checkbox.value]);
    }
    else {
      const valore = +checkbox.value;
      const idx = selectedTags.indexOf(valore);
      if (idx > -1) {
        selectedTags.splice(idx, 1)
      ;
}

    }
  }

  get titolo() {
    return this.form.get("titolo");
  }
  get numeroPagine() {
    return this.form.get("numeroPagine");
  }
  get autoreNome() {
    return this.form.get("autoreNome");
  }
  get casaEditriceNome() {
    return this.form.get("casaEditriceNome");
  }
  get casaDiProduzione() {
    return this.form.get("casaDiProduzione");
  }
  get casaDiPubblicazione() {
    return this.form.get("casaDiPubblicazione");
  }
  get minData() {
    return this.form.get("minData");
  }
  get maxData() {
    return this.form.get("maxData");
  }
  get minVoto() {
    return this.form.get("minVoto");
  }
  get maxVoto() {
    return this.form.get("maxVoto");
  }
  get minOreDiGiocoStoriaPrincipale() {
    return this.form.get("minOreDiGiocoStoriaPrincipale");
  }
  get maxOreDiGiocoStoriaPrincipale() {
    return this.form.get("maxOreDiGiocoStoriaPrincipale");
  }
  get tags() {
    return this.form.get("tags");
  }
  get generi(){
    return this.form.get("generi");
  }

}
