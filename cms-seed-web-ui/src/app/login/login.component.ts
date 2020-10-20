import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../service/server/user.service';
import { RuntimeVar } from '../../common/runtimevar.data';

@Component({
	selector: 'login-panel',
	templateUrl: 'login.template.html'
})
export class LoginComponent implements OnInit {

	private authcToken: any = {};

	constructor(private userService: UserService, private router: Router) {}
	
	ngOnInit() {
		// this.router.navigate(['/user-ctrl']);
		console.log("应用初始化...");
	}

	login(): void {
		if(!this.authcToken.username || !this.authcToken.password) return;
		this.userService.login(this.authcToken).subscribe(data => this.loginSuccess(data));
	}

	loginSuccess(data): void {
		RuntimeVar.isLoggedIn = true;
		this.router.navigate(['my']);
	}
	
}
