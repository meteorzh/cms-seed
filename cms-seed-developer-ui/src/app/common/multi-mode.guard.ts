import { Injectable, InjectionToken, Inject, Injector } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { ComponentMode } from 'src/app/common/common';
import { CURRENT_DEFAULT } from './multi-mode.component';

/**
 * @description
 * 通用的多模式组件编辑/查看模式 CanActivate 守卫
 * 使用这个守卫时需要提供配置对象
 * 
 */
@Injectable()
export class CommonEditOrViewModeCAGuard implements CanActivate {

    private config: EditOrViewGuardConfig;

    constructor(private router: Router, private injector: Injector) {
        this.config = this.injector.get(EDIT_VIEW_CFG);
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        if(CURRENT_DEFAULT.mode != null && CURRENT_DEFAULT.data != null) {
            return true;
        }
        this.router.navigate([this.config.redirectWhenFailed]);
        return false;
    }

}

export const EDIT_VIEW_CFG = new InjectionToken<EditOrViewGuardConfig>("EditOrViewGuardConfigToken");

/**
 * @description
 * 编辑或查看 CanActivate 守卫配置
 */
export interface EditOrViewGuardConfig {

    /**
     * @description
     * 守卫不通过时的跳转路径
     */
    redirectWhenFailed: string
}


/**
 * @description
 * 通用的多模式组件创建模式 CanActivate 守卫
 */
@Injectable()
export class CommonCreateModeCAGuard implements CanActivate {

    constructor() { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        CURRENT_DEFAULT.mode = ComponentMode.CREATE;
        return true;
    }

}

