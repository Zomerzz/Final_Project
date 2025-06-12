export interface RecensioneRequest {
    recensioneId: number;
    voto: number;
    recensione: string|null;
    type: string|null;
    operaId: number;
    utenteId: number;
}
