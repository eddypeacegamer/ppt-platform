import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';

@Component({
  selector: 'ngx-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss' ]
})
export class UserComponent implements OnInit {


  public user: any;


  constructor(private route: ActivatedRoute,
              private userService: UsersData) { }

  ngOnInit() {
    this.route.paramMap.subscribe(route => {
      const email = route.get('email');
      if (email === '*') {
        console.log('Crea nuevo usuario');
      } else {
        console.log('Prersentar informacion de:', email);
        // cambiar este metodo por el que regresa la informacion del usuario
        this.userService.getUserInfo().then(user => this.user = user);
      }
    });
  }

}
