import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { TestComponent } from './test.component';
import { TestRoutingModule } from './test-routing.module';
import { TestService } from './test.service';
import { SharedModule } from 'src/app/common/shared.module';

@NgModule({
    imports: [
        SharedModule, 
        TestRoutingModule
    ],
    declarations: [ TestComponent ],
    exports: [ TestComponent ],
    providers: [ TestService ]
})
export class TestModule { }
