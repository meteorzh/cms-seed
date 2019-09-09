import { NgModule } from '@angular/core';

import { RoleRoutingModule } from "./role-routing.module";
import { RoleComponent } from "./role.component";

@NgModule({
  imports: [RoleRoutingModule],
  declarations: [RoleComponent],
  exports: [RoleComponent]
})
export class RoleModule { }
