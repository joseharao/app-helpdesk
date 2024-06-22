import { HTTP_INTERCEPTORS, HttpInterceptorFn } from '@angular/common/http';

export const interceptorInterceptor: HttpInterceptorFn = (req, next) => {
  let token = localStorage.getItem('token');
  if (token) {
    const reqClone = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });
    return next(reqClone);
  } else {
    return next(req);
  }
};

export const AutenticadorProvider = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: interceptorInterceptor,
    multi: true,
  },
];
