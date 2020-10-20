import { NgModule } from '@angular/core';

import { DictRoutingModule } from './dict-routing.module';

import { DictComponent } from './dict.component';
import { NzListModule, NzSelectModule, NzTableModule, NzDividerModule, NzInputNumberModule, NzPopconfirmModule } from 'ng-zorro-antd';
import { DictEditComponent } from './dict-edit.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DictService } from './dict.service';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { SharedModule } from 'src/app/common/shared.module';

@NgModule({
    imports: [
        SharedModule,
        ReactiveFormsModule, 
        DictRoutingModule,
        NzSelectModule,
        NzListModule, 
        NzTableModule,
        NzDividerModule,
        NzInputNumberModule,
        NzPopconfirmModule,
        DragDropModule,
    ],
    declarations: [DictComponent, DictEditComponent],
    exports: [DictComponent],
    providers: [DictService]
})
export class DictModule { }
