import { HttpInterceptorFn } from '@angular/common/http';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  
  // 1. Injeta o identificador da plataforma (Navegador ou Servidor?)
  const platformId = inject(PLATFORM_ID);

  // 2. Verifica: "Estou rodando no navegador?"
  if (isPlatformBrowser(platformId)) {
    
    // AGORA É SEGURO USAR LOCALSTORAGE
    const token = localStorage.getItem('token');

    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next(authReq);
    }
  }

  // 3. Se estiver no servidor OU não tiver token, segue normal
  return next(req);
};