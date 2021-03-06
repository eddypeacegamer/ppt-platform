import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbAuthModule, NbDummyAuthStrategy } from '@nebular/auth';
import { NbSecurityModule, NbRoleProvider } from '@nebular/security';
import { of as observableOf } from 'rxjs';

import { throwIfAlreadyLoaded } from './module-import-guard';
import {LayoutService} from './utils/layout.service';


import { UsersData } from './data/users-data';
import { UsersService } from './back-services/users.service';


import { CatalogsData } from './data/catalogs-data';
import { CompaniesData } from './data/companies-data';
import { InvoicesData } from './data/invoices-data';
import { CfdiData } from './data/cfdi-data';
import { DevolutionData } from './data/devolution-data';
import { ClientsData } from './data/clients-data';
import { PaymentsData } from './data/payments-data';

import { CatalogsService } from './back-services/catalogs.service';
import { ClientsService } from './back-services/clients.service';
import { CompaniesService } from './back-services/companies.service';
import { InvoicesService } from './back-services/invoices.service';
import { CfdiService } from './back-services/cfdi.service';
import { DevolutionService } from './back-services/devolution.service';
import { PaymentsService } from './back-services/payments.service';
import { FilesData } from './data/files-data';
import { FilesService } from './back-services/files.service';
import { TransferData } from './data/transfers-data';
import { TransferService } from './back-services/transfer.service';
import { CuentasData } from './data/cuentas-data';
import { CuentasService } from './back-services/cuentas.service';


const DATA_SERVICES = [
  {provide: CatalogsData, useClass: CatalogsService},
  {provide: ClientsData, useClass: ClientsService},
  {provide: CompaniesData, useClass: CompaniesService},
  {provide: CuentasData, useClass: CuentasService},
  {provide: InvoicesData, useClass: InvoicesService},
  {provide: CfdiData, useClass: CfdiService},
  {provide: PaymentsData, useClass : PaymentsService},
  {provide: DevolutionData, useClass: DevolutionService},
  {provide: TransferData , useClass: TransferService},
  {provide: FilesData, useClass : FilesService},
  {provide: UsersData, useClass: UsersService }
];

export class NbSimpleRoleProvider extends NbRoleProvider {
  getRole() {
    // here you could provide any role based on any auth flow
    return observableOf('guest');
  }
}

export const NB_CORE_PROVIDERS = [
  //...MockDataModule.forRoot().providers,
  ...DATA_SERVICES,
  ...NbAuthModule.forRoot({

    strategies: [
      NbDummyAuthStrategy.setup({
        name: 'email',
        delay: 3000,
      }),
    ],
    forms: {
    },
  }).providers,

  NbSecurityModule.forRoot({
    accessControl: {
      guest: {
        view: '*',
      },
      user: {
        parent: 'guest',
        create: '*',
        edit: '*',
        remove: '*',
      },
    },
  }).providers,

  {
    provide: NbRoleProvider, useClass: NbSimpleRoleProvider,
  },
  LayoutService,
];

@NgModule({
  imports: [
    CommonModule,
  ],
  exports: [
    NbAuthModule,
  ],
  declarations: [],
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    throwIfAlreadyLoaded(parentModule, 'CoreModule');
  }

  static forRoot(): ModuleWithProviders {
    return <ModuleWithProviders>{
      ngModule: CoreModule,
      providers: [
        ...NB_CORE_PROVIDERS,
      ],
    };
  }
}
