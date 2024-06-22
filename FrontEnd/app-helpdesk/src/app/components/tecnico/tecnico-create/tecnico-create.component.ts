import { MatButtonModule } from '@angular/material/button';
import { Component } from '@angular/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormField } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-tecnico-create',
  standalone: true,
  imports: [MatCheckboxModule, MatFormField, MatInputModule, MatIconModule, MatButtonModule],
  templateUrl: './tecnico-create.component.html',
  styleUrl: './tecnico-create.component.scss'
})
export class TecnicoCreateComponent {

}
