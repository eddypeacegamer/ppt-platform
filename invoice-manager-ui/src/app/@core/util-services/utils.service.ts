import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }



  public parseFilterParms(filterParams: any): any {

    const params: any = {};
    /* Parsing logic */
    for (const key in filterParams) {
      if (filterParams[key] !== undefined) {
        let value: string = filterParams[key];
        if (filterParams[key] instanceof Date) {
          const date: Date = filterParams[key] as Date;
          value = `${date.getFullYear()}-${this.zeroPad(date.getMonth() + 1, 2)}-${this.zeroPad(date.getDate(), 2)}`;
        }
        if (value !== null && value.length > 0) {
          params[key] = value;
        }
      }
    }
    return params;
  }

  /**
   * Compares if  url query params and filterParams contains same filter search, in case it has same
   * parameters the  method will return true otherwise return false
   * @param params Query url params
   * @param filterParams Internal component filter params
   */
  public compareParams(params: any, filterParams: any): boolean {

    const filterparams = this.parseFilterParms(filterParams);

    if (JSON.stringify(params) !== '{}') { // not empty object
      for (const key in params) {
        if (params[key] !== filterparams[key]) {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }

  private zeroPad(num: number, places: number) {
    const zero = places - num.toString().length + 1;
    return Array(+(zero > 0 && zero)).join('0') + num;
  }
}
