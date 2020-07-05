import { NgModule } from '@angular/core';

import { RegionRoutingModule } from "./region-routing.module";
import { RegionComponent } from "./region.component";
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NzGridModule, NzTableModule, NzButtonModule, NzDividerModule, NzIconModule, NzModalModule, NzFormModule, NzInputModule, NzPopconfirmModule, NzToolTipModule, NzMessageModule } from 'ng-zorro-antd';
import { RegionService } from './region.service';
import { SharedModule } from 'src/app/common/shared.module';

@NgModule({
    imports: [ 
        SharedModule,
        ReactiveFormsModule, 
        RegionRoutingModule, 
        NzTableModule,
        NzDividerModule,
        NzIconModule,
        NzModalModule,
        NzPopconfirmModule,
        NzToolTipModule,
    ],
    declarations: [ RegionComponent ],
    exports: [ RegionComponent ],
    providers: [ RegionService ]
})
export class RegionModule { }
