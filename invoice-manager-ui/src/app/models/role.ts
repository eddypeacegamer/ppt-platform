export class Role {

    public id: number;
    public role: string;
    public description: string;

    constructor(role?: string) {
      this.role = role;
    }
}
