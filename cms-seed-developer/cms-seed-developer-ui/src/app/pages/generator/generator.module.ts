import { NgModule } from '@angular/core';

import { GeneratorRoutingModule } from './generator-routing.module';

import { GeneratorComponent } from './generator.component';


@NgModule({
  imports: [GeneratorRoutingModule],
  declarations: [GeneratorComponent],
  exports: [GeneratorComponent]
})
export class GeneratorModule { }
