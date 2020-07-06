import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NzButtonModule, NzInputModule, NzMessageModule, NzFormModule, NzGridModule } from 'ng-zorro-antd';

@NgModule({
    imports: [],
    declarations: [],
    exports: [
        CommonModule,
        FormsModule,
        NzButtonModule,
        NzInputModule,
        NzFormModule,
        NzGridModule,
    ]
})
export class SharedModule { }