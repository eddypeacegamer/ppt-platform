import { Component, OnInit } from '@angular/core';
import { UsersData } from '../../../@core/data/users-data';
import { GenericPage } from '../../../models/generic-page';

@Component({
  selector: 'ngx-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {


  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(private userService: UsersData) { }

  ngOnInit() {
    this.updateDataTable(0,10);
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.userService.getUsers(pageValue,sizeValue).subscribe(data => this.page = data);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

}
