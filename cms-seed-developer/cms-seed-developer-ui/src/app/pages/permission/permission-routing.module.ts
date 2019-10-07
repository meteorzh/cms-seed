import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PermissionComponent } from './permission.component';
import { PermissionEditComponent } from './permission-edit.component';
import { CommonCreateModeCAGuard, CommonEditOrViewModeCAGuard, EditOrViewGuardConfig, EDIT_VIEW_CFG } from 'src/app/common/multi-mode.guard';

const routes: Routes = [
    { path: '', component: PermissionComponent },
    { path: 'create', component: PermissionEditComponent, canActivate: [CommonCreateModeCAGuard] },
    { path: 'edit', component: PermissionEditComponent, canActivate: [CommonEditOrViewModeCAGuard] }
];

const editOrViewConfig: EditOrViewGuardConfig = {
    redirectWhenFailed: "/permission"
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
export class PermissionRoutingModule { }
