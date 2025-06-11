export interface JwtPayload{
    userId: string,
    sub: string,
    iat: number, // data di creazione
    exp: number // expiration
}