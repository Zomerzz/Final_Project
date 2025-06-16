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

//Metodi di sort attualmente disponibili
//Videogiochi: orderByVotoDesc, orderByVotoAsc, orderByTitoloDesc, orderByTitoloAsc, orderByDataPubblicazioneDesc, orderByDataDiPubblicazioneAsc
//Libri: orderByVotoDesc, orderByVotoAsc, orderByTitoloDesc, orderByTitoloAsc, orderByDataPubblicazioneDesc, orderByDataDiPubblicazioneAsc, orderByNumeroDiPagineDesc, orderByNumeroDiPagineAsc
//Film orderByVotoDesc, orderByVotoAsc, orderByTitoloDesc, orderByTitoloAsc, orderByDataPubblicazioneDesc, orderByDataDiPubblicazioneAsc, orderByDurataFilmDesc, orderByDurataFilmAsc