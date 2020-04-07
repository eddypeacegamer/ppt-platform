import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NbActionsModule, NbButtonModule, NbCardModule, NbCheckboxModule, NbDatepickerModule, NbInputModule, NbRadioModule, NbSelectModule, NbUserModule, NbStepperModule, NbDialogModule, NbIconModule, NbSpinnerModule, } from '@nebular/theme';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { PagosComponent } from './pagos/pagos.component';
import { ConceptosComponent } from './conceptos/conceptos.component';
let CommonsModule = class CommonsModule {
};
CommonsModule = tslib_1.__decorate([
    NgModule({
        declarations: [
            ClientesComponent,
            ClienteComponent,
            EmpresaComponent,
            EmpresasComponent,
            PagosComponent,
            ConceptosComponent
        ],
        imports: [
            CommonModule,
            FormsModule,
            NbActionsModule,
            NbButtonModule,
            NbCardModule,
            NbCheckboxModule,
            NbDatepickerModule,
            NbInputModule,
            NbRadioModule,
            NbSelectModule,
            NbUserModule,
            NbStepperModule,
            NbDialogModule,
            NbIconModule,
            NbSpinnerModule,
        ],
        exports: [
            ClientesComponent,
            ClienteComponent,
            EmpresaComponent,
            EmpresasComponent,
            PagosComponent,
            ConceptosComponent,
            FormsModule,
            CommonModule,
            NbActionsModule,
            NbButtonModule,
            NbCardModule,
            NbCheckboxModule,
            NbDatepickerModule,
            NbInputModule,
            NbRadioModule,
            NbSelectModule,
            NbUserModule,
            NbStepperModule,
            NbDialogModule,
            NbIconModule,
            NbSpinnerModule,
        ]
    })
], CommonsModule);
export { CommonsModule };
//# sourceMappingURL=commons.module.js.map