import { Component, OnInit } from '@angular/core';
import { UsersData } from '../@core/data/users-data';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent implements OnInit {

  constructor(private userService: UsersData ) { }

  public menu = [];

  public ngOnInit() {
    this.userService.getUserInfo().then(user => {this.menu = user.menu; });
  }
}
