import { NgModule } from '@angular/core';

import { RegionRoutingModule } from "./region-routing.module";
import { RegionComponent } from "./region.component";
import { ReactiveFormsModule } from '@angular/forms';
import { NzTableModule, NzDividerModule, NzModalModule, NzPopconfirmModule, NzToolTipModule } from 'ng-zorro-antd';
import { RegionService } from './region.service';
import { SharedModule } from 'src/app/common/shared.module';

@NgModule({
    imports: [ 
        SharedModule,
        ReactiveFormsModule, 
        RegionRoutingModule, 
        NzTableModule,
        NzDividerModule,
        NzModalModule,
        NzPopconfirmModule,
        NzToolTipModule,
    ],
    declarations: [ RegionComponent ],
    exports: [ RegionComponent ],
    providers: [ RegionService ]
})
export class RegionModule { }
