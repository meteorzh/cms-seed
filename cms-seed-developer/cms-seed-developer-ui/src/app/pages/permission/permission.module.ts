import { NgModule } from '@angular/core';

import { PermissionRoutingModule } from './permission-routing.module';

import { PermissionComponent } from './permission.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { CommonModule } from '@angular/common';
import { DictService } from '../dict/dict.service';
import { PermissionService } from './permission.service';
import { PermissionEditComponent } from './permission-edit.component';


@NgModule({
    imports: [CommonModule, FormsModule, PermissionRoutingModule, ReactiveFormsModule, NgZorroAntdModule],
    declarations: [PermissionComponent, PermissionEditComponent],
    exports: [PermissionComponent],
    providers: [ DictService, PermissionService ]
})
export class PermissionModule { }
