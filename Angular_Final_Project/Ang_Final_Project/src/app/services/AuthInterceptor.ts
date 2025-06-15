import { HttpErrorResponse, HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";
import { AuthService } from "./AuthService";
import { Router } from "@angular/router";
import { catchError, throwError } from "rxjs";

export const authInterceptorFn: HttpInterceptorFn = (req, next) =>{
 const authService = inject(AuthService);
  const token = authService.getToken();
  const router = inject(Router);

  if (req.url.includes('/api/auth/login') || req.url.includes('/api/auth/register')) {
    return next(req);
  }

  const authReq = token ? req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    }) : req;

    return next(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if(error.status === 401 || error.status === 403){
          authService.logout();
          router.navigate(['/login']);
          return throwError(() => "errore nell'autenticazione. Ruolo invalido o sessione scaduta")
        }
        return throwError(() => error);
      })
    );
}