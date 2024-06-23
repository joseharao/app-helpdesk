import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormField } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterModule } from '@angular/router';
import { NgxMaskDirective } from 'ngx-mask';
import { TecnicoService } from '../../../services/tecnico.service';
import { ToastrService } from 'ngx-toastr';
import { Tecnico } from '../../../models/tecnico';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-tecnico-create',
  standalone: true,
  imports: [
    MatCheckboxModule,
    MatFormField,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    RouterModule,
    ReactiveFormsModule,
    NgxMaskDirective,
  ],
  templateUrl: './tecnico-create.component.html',
  styleUrl: './tecnico-create.component.scss',
})
export class TecnicoCreateComponent {
  nome: FormControl = new FormControl(null, Validators.minLength(3));
  cpf: FormControl = new FormControl(null, Validators.required);
  email: FormControl = new FormControl(null, Validators.email);
  senha: FormControl = new FormControl(null, Validators.minLength(3));
  tecnico: Tecnico = {
    id: '',
    nome: '',
    cpf: '',
    email: '',
    senha: '',
    perfis: [],
    dataCriacao: '',
  };

  constructor(private service: TecnicoService, private toast: ToastrService, private router:Router) {}

  create(): void {
    this.service.create(this.tecnico).subscribe({
      next: () => {
        this.toast.success('Técnico cadastradado com sucesso!!!');
        this.router.navigate(['tecnicos']);
      },
      error: (ex) => {
        if (ex.error) {
          if (ex.error.errors && Array.isArray(ex.error.errors) && ex.error.errors.length > 0) {
            ex.error.errors.forEach((element: any) => {
              this.toast.error(element.message);
            });
          } else if (ex.error.message) {
            this.toast.error(ex.error.message);
          } else {
            this.toast.error('Ocorreu um erro desconhecido.');
          }
        } else {
          this.toast.error('Ocorreu um erro desconhecido.');
        }
      },
    });
  }

  addPerfil(perfil: any): void {
    if (this.tecnico.perfis.includes(perfil)) {
      this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfil));
    } else {
      this.tecnico.perfis.push(perfil);
    }
  }

  validaCampos() {
    return (
      this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid
    );
  }
}
