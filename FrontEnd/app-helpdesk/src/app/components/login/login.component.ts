import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Credenciais } from '../../models/credenciais';
import {
  FormControl,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  creds: Credenciais = { email: '', senha: '' };
  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.maxLength(3));
  constructor(private toast: ToastrService, private auth: AuthService, private router: Router) {}

  login() {
    this.auth.authenticate(this.creds).subscribe((resposta) => {
      this.auth.loginSucesso(resposta.headers.get('Authorization').substring(7));
      this.router.navigate(["home"]);
    }, () => {
      this.toast.error("Usuário ou Senha Inválida");
    });
  }

  validaCampos(): boolean {
    return this.email.valid && this.senha.valid;
  }
}
