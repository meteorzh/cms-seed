import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { TestComponent } from './test.component';
import { TestRoutingModule } from './test-routing.module';
import { TestService } from './test.service';
import { NzButtonModule } from 'ng-zorro-antd';

@NgModule({
    imports: [ CommonModule, TestRoutingModule, NzButtonModule ],
    declarations: [ TestComponent ],
    exports: [ TestComponent ],
    providers: [ TestService ]
})
export class TestModule { }
