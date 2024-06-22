import { AuthService } from './../../../services/auth.service';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatLabel } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatListItem, MatNavList } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../../header/header.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [MatSidenavModule, MatButtonModule, MatNavList, MatListItem, MatIcon, MatLabel, RouterOutlet, HeaderComponent, RouterModule],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss'
})
export class NavComponent {
    constructor(private router:Router, private auth: AuthService, private toast: ToastrService){

    }
    ngOnInit(): void{

    }

    logout(){
      this.router.navigate(["login"]);
      this.auth.logout();
      this.toast.info("Logout efetuado com sucesso!!!", "Logout", { timeOut:7000 });
    }
}
