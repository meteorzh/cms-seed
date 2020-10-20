import { NgModule } from '@angular/core';

import { PermissionRoutingModule } from './permission-routing.module';

import { PermissionComponent } from './permission.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NzFormModule, NzGridModule, NzInputModule, NzSelectModule, NzButtonModule, NzTableModule, NzDividerModule, NzPopconfirmModule, NzMessageModule } from 'ng-zorro-antd';
import { CommonModule } from '@angular/common';
import { DictService } from '../dict/dict.service';
import { PermissionService } from './permission.service';
import { PermissionEditComponent } from './permission-edit.component';
import { SharedModule } from 'src/app/common/shared.module';


@NgModule({
    imports: [
        SharedModule,
        PermissionRoutingModule, 
        ReactiveFormsModule, 
        NzSelectModule,
        NzTableModule,
        NzDividerModule,
        NzPopconfirmModule,
    ],
    declarations: [PermissionComponent, PermissionEditComponent],
    exports: [PermissionComponent],
    providers: [ DictService, PermissionService ]
})
export class PermissionModule { }
