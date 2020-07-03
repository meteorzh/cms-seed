import { NgModule } from '@angular/core';

import { RoleRoutingModule } from "./role-routing.module";
import { RoleComponent } from "./role.component";
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { RoleService } from './role.service';

@NgModule({
    imports: [ CommonModule, ReactiveFormsModule, FormsModule, RoleRoutingModule, NgZorroAntdModule],
    declarations: [ RoleComponent ],
    exports: [ RoleComponent ],
    providers: [ RoleService ]
})
export class RoleModule { }
