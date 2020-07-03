import { NgModule } from '@angular/core';

import { DictRoutingModule } from './dict-routing.module';

import { DictComponent } from './dict.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { CommonModule } from '@angular/common';
import { DictEditComponent } from './dict-edit.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DictService } from './dict.service';
import { DragDropModule } from '@angular/cdk/drag-drop';

@NgModule({
    imports: [CommonModule, ReactiveFormsModule, FormsModule, DictRoutingModule, NgZorroAntdModule, DragDropModule],
    declarations: [DictComponent, DictEditComponent],
    exports: [DictComponent],
    providers: [DictService]
})
export class DictModule { }
