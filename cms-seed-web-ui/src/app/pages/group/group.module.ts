import { NgModule } from '@angular/core';

import { GroupRoutingModule } from './group-routing.module';

import { GroupComponent } from './group.component';
import { NzAlertModule, NzFormModule, NzGridModule, NzInputModule, NzButtonModule, NzTableModule, NzDividerModule, NzPopconfirmModule, NzMessageModule } from 'ng-zorro-antd';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GroupService } from './group.service';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/common/shared.module';


@NgModule({
    imports: [
        SharedModule,
        GroupRoutingModule, 
        ReactiveFormsModule, 
        NzAlertModule,
        NzTableModule,
        NzDividerModule,
        NzPopconfirmModule,
    ],
    declarations: [GroupComponent],
    exports: [GroupComponent],
    providers: [ GroupService ]
})
export class GroupModule { }
