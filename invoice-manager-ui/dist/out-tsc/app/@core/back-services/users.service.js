import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
let UsersService = class UsersService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getUsers() {
        return this.httpClient.get('../api/users');
    }
    getUserInfo() {
        return this.httpClient.get('../api/users/myInfo');
    }
    logout() {
        return this.httpClient.get('../api/logout');
    }
};
UsersService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root',
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], UsersService);
export { UsersService };
//# sourceMappingURL=users.service.js.map