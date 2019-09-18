import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ngx-tesoreria',
  template: `
    <h1>TESORERIA IS WORKING</h1>
    <router-outlet></router-outlet>
  `,
})
export class TesoreriaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
