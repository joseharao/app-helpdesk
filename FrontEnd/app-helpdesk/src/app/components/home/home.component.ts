import { Component } from '@angular/core';
import { MatCard, MatCardContent, MatCardHeader, MatCardSubtitle, MatCardTitle } from '@angular/material/card';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatCard, MatCardHeader, MatCardContent, MatCardTitle, MatCardSubtitle],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
