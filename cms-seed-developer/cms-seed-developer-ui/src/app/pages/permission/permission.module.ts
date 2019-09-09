import { NgModule } from '@angular/core';

import { PermissionRoutingModule } from './permission-routing.module';

import { PermissionComponent } from './permission.component';


@NgModule({
  imports: [PermissionRoutingModule],
  declarations: [PermissionComponent],
  exports: [PermissionComponent]
})
export class PermissionModule { }
