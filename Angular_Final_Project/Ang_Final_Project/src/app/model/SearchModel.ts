export interface SearchModel{
    tipoMedia:String;
    titolo:String;
    numeroPagine: number;
    autoreNome:String;
    casaEditriceNome:String;
    casaDiProduzione:String;
    casaDiPubblicazione:String;
    minData:String;
    maxData:String;
    minVoto:number;
    maxVoto:number;
    minOreDiGiocoStoriaPrincipale:number;
    maxOreDiGiocoStoriaPrincipale:number;
    tags:number[];
    sort: string;
}

