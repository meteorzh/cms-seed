import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule) },
  { path: 'dict', loadChildren: () => import('./pages/dict/dict.module').then(m => m.DictModule) },
  { path: 'group', loadChildren: () => import('./pages/group/group.module').then(m => m.GroupModule) },
  { path: 'role', loadChildren: () => import('./pages/role/role.module').then(m => m.RoleModule) },
  { path: 'permission', loadChildren: () => import('./pages/permission/permission.module').then(m => m.PermissionModule) },
  { path: 'generator', loadChildren: () => import('./pages/generator/generator.module').then(m => m.GeneratorModule) },
  { path: 'test', loadChildren: () => import('./pages/test/test.module').then(m => m.TestModule) },
  { path: 'region', loadChildren: () => import('./pages/region/region.module').then(m => m.RegionModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
