import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Tecnico } from '../../../models/tecnico';

@Component({
  selector: 'app-tecnico-list',
  standalone: true,
  imports: [MatTableModule, MatPaginator],
  templateUrl: './tecnico-list.component.html',
  styleUrl: './tecnico-list.component.scss',
})
export class TecnicoListComponent {
  ELEMENT_DATA: Tecnico[] = [
    {
      id:1,
      nome:'JOSE HARAO',
      cpf:'018.367.883-47',
      email:'josseharao@icloud.com',
      senha:'1234',
      perfis:['0'],
      dataCriacao:'18/05/2024'
    }
  ];
  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'acoes'];
  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}
