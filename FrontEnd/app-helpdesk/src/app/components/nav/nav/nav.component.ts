import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatLabel } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatListItem, MatNavList } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../../header/header.component';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [MatSidenavModule, MatButtonModule, MatNavList, MatListItem, MatIcon, MatLabel, RouterOutlet, HeaderComponent, RouterModule],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss'
})
export class NavComponent {
    constructor(private router:Router){

    }
    ngOnInit(): void{
        //this.router.navigate(["login"])
    }
}
