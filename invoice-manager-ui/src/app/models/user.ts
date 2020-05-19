import { NbMenuItem } from '@nebular/theme';
import { Role } from './role';

export class  User {
    public email: string;
    public activo: boolean;
    public name: string;
    public urlPicture: string;
    public roles: Role[];
    public menu: NbMenuItem[];
    public id: number;
}
