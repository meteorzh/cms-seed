import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DictComponent } from './dict.component';
import { DictEditComponent } from './dict-edit.component';
import { CommonCreateModeCAGuard, CommonEditOrViewModeCAGuard, EditOrViewGuardConfig, EDIT_VIEW_CFG } from 'src/app/common/multi-mode.guard';

const routes: Routes = [
    { path: '', component: DictComponent },
    { path: 'create', component: DictEditComponent, canActivate: [CommonCreateModeCAGuard] },
    { path: 'edit', component: DictEditComponent, canActivate: [CommonEditOrViewModeCAGuard] },
];

const editOrViewConfig: EditOrViewGuardConfig = {
    redirectWhenFailed: "/dict"
};

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: [
        { provide: EDIT_VIEW_CFG, useValue: editOrViewConfig },
        CommonEditOrViewModeCAGuard,
        CommonCreateModeCAGuard
    ]
})
export class DictRoutingModule { }
