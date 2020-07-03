import { NgModule } from '@angular/core';

import { RegionRoutingModule } from "./region-routing.module";
import { RegionComponent } from "./region.component";
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { RegionService } from './region.service';

@NgModule({
    imports: [ CommonModule, ReactiveFormsModule, FormsModule, RegionRoutingModule, NgZorroAntdModule],
    declarations: [ RegionComponent ],
    exports: [ RegionComponent ],
    providers: [ RegionService ]
})
export class RegionModule { }
