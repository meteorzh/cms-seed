import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/common/base-http.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { ServeCtx } from 'src/app/common/base.service';

/**
 * @description
 * 用户组接口
 */
@Injectable()
export class GeneratorService extends BaseHttpService {

    constructor(protected http: HttpClient, protected router: Router, protected messageService: NzMessageService) {
        super(http, router, messageService);
    }

    defaultGenCfg(ctx: ServeCtx) {
        super.get({ url: "/api/sys/codegen/defaultgencfg" }, ctx);
    }

    generate(param: any, ctx: ServeCtx) {
        super.post({ url: "/api/sys/codegen/gen", body: param }, ctx);
    }

}