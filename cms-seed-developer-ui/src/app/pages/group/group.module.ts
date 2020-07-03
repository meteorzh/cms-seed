import { NgModule } from '@angular/core';

import { GroupRoutingModule } from './group-routing.module';

import { GroupComponent } from './group.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GroupService } from './group.service';
import { CommonModule } from '@angular/common';


@NgModule({
    imports: [CommonModule, GroupRoutingModule, ReactiveFormsModule, FormsModule, NgZorroAntdModule],
    declarations: [GroupComponent],
    exports: [GroupComponent],
    providers: [ GroupService ]
})
export class GroupModule { }
