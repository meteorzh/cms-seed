import { NgModule } from '@angular/core';

import { GroupRoutingModule } from './group-routing.module';

import { GroupComponent } from './group.component';


@NgModule({
  imports: [GroupRoutingModule],
  declarations: [GroupComponent],
  exports: [GroupComponent]
})
export class GroupModule { }
