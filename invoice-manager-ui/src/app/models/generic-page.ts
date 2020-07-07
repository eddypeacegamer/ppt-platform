export class GenericPage<T> {
    public content: T[];
    public totalElements: number;
    public last: boolean;
    public totalPages: number;
    public first: boolean;
    public numberOfElements: number;
    public size: number;
    public number: number;
    public empty: boolean;

    constructor() {
        this.totalElements = 0;
        this.last = false ;
        this.totalPages = 0;
        this.first = true;
        this.numberOfElements = 0;
        this.size = 10;
        this.number = 0;
        this.numberOfElements = 0;
        this.empty = true;
    }
}
