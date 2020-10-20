import { NgModule } from '@angular/core';

import { RoleRoutingModule } from "./role-routing.module";
import { RoleComponent } from "./role.component";
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NzAlertModule, NzFormModule, NzGridModule, NzInputModule, NzButtonModule, NzTableModule, NzDividerModule, NzPopconfirmModule, NzMessageModule } from 'ng-zorro-antd';
import { RoleService } from './role.service';
import { SharedModule } from 'src/app/common/shared.module';

@NgModule({
    imports: [ 
        SharedModule,
        ReactiveFormsModule, 
        RoleRoutingModule, 
        NzAlertModule,
        NzTableModule,
        NzDividerModule,
        NzPopconfirmModule,
    ],
    declarations: [ RoleComponent ],
    exports: [ RoleComponent ],
    providers: [ RoleService ]
})
export class RoleModule { }
