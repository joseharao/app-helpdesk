import { MatButtonModule } from '@angular/material/button';
import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Tecnico } from '../../../models/tecnico';
import { TecnicoService } from '../../../services/tecnico.service';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-tecnico-list',
  standalone: true,
  imports: [MatTableModule, MatPaginator, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, RouterModule],
  templateUrl: './tecnico-list.component.html',
  styleUrl: './tecnico-list.component.scss',
})
export class TecnicoListComponent {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  ELEMENT_DATA: Tecnico[] = [];
  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'acoes'];
  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  constructor(private service:TecnicoService){}

  ngOnInit(){
    this.findAll();
  }

  findAll(){
    this.service.findAll().subscribe(
       resposta => {
        this.dataSource = new MatTableDataSource<Tecnico>(resposta);
        this.dataSource.paginator = this.paginator;
       }
    );
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
