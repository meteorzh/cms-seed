import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad, Route } from '@angular/router';

import { UserService } from '../../service/server/user.service';
import { RuntimeVar } from '../../common/runtimevar.data';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {

    constructor(private userService: UserService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        RuntimeVar.hangUrl = state.url;
        return this.checkLogin();
    }

    canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        RuntimeVar.hangUrl = state.url;
        return this.canActivate(route, state);
    }

    canLoad(route: Route): boolean {
        return true;
    }

    checkLogin(): boolean {
        if(!RuntimeVar.isLoggedIn) {
            this.userService.checkLogin().subscribe(
                (data) => {
                    if(data) {
                        RuntimeVar.isLoggedIn = true;
                        if(RuntimeVar.hangUrl) this.router.navigate([RuntimeVar.hangUrl]);
                    } else {
                        this.router.navigate(['/login']);
                    }
                }
            );
        }
        return RuntimeVar.isLoggedIn;
    }

}