import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const service = inject(AuthService);
  const router = inject(Router);
  let isAuthenticated = service.autenticado();
    if (isAuthenticated) {
      return true;
    } else {
      router.navigate(["login"]);
      return false;
    }
};
