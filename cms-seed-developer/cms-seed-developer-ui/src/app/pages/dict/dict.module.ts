import { NgModule } from '@angular/core';

import { DictRoutingModule } from './dict-routing.module';

import { DictComponent } from './dict.component';
import { NzFormModule, NzSelectModule, NzTableModule, NzDividerModule } from 'ng-zorro-antd';

@NgModule({
  imports: [DictRoutingModule, NzFormModule],
  declarations: [DictComponent],
  exports: [DictComponent]
})
export class DictModule { }
