import { NgModule } from '@angular/core';

import { GeneratorRoutingModule } from './generator-routing.module';

import { GeneratorComponent } from './generator.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { StrReplacePipe } from 'src/app/common/pipe';
import { GeneratorService } from './generator.service';


@NgModule({
    imports: [ CommonModule, ReactiveFormsModule, FormsModule, GeneratorRoutingModule, NgZorroAntdModule ],
    declarations: [ GeneratorComponent, StrReplacePipe ],
    exports: [ GeneratorComponent ],
    providers: [ GeneratorService ]
})
export class GeneratorModule { }
