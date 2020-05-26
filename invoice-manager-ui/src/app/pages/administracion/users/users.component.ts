import { Component, OnInit } from '@angular/core';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {


  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  public filterParams: any = {email: '', status: '*', alias: ''};

  constructor(private userService: UsersData,
    private router: Router) { }

  ngOnInit() {
    this.updateDataTable(0, 10);
  }

  public updateDataTable(currentPage?: number, pageSize?: number, filterParams?: any) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.userService.getUsers(pageValue, sizeValue, this.filterParams).subscribe(data => this.page = data);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public redirectToUser(id: string) {
    this.router.navigate([`./pages/administracion/usuarios/${id}`]);
  }

  public Editar(id:number){
    this.router.navigate([`./pages/administracion/usuarios/${id}`]);
  }

}
