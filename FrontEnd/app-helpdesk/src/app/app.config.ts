import { provideHttpClient, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideRouter } from '@angular/router';
import { provideNgxMask } from 'ngx-mask';
import { provideToastr } from 'ngx-toastr';

import { routes } from './app.routes';
import { interceptorInterceptor } from './security/interceptor.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync(),
    provideHttpClient(
      withInterceptors([interceptorInterceptor]),
      withInterceptorsFromDi()
    ),
    provideToastr({
      timeOut: 4000,
      progressBar: true,
      closeButton: true,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    provideNgxMask(),
  ],
};
