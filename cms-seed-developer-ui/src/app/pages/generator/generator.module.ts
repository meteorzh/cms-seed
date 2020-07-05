import { NgModule } from '@angular/core';

import { GeneratorRoutingModule } from './generator-routing.module';

import { GeneratorComponent } from './generator.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NzTabsModule, NzCollapseModule } from 'ng-zorro-antd';
import { StrReplacePipe } from 'src/app/common/pipe';
import { GeneratorService } from './generator.service';
import { SharedModule } from 'src/app/common/shared.module';


@NgModule({
    imports: [ 
        SharedModule,
        ReactiveFormsModule, 
        GeneratorRoutingModule, 
        NzTabsModule,
        NzCollapseModule,
    ],
    declarations: [ GeneratorComponent, StrReplacePipe ],
    exports: [ GeneratorComponent ],
    providers: [ GeneratorService ]
})
export class GeneratorModule { }
