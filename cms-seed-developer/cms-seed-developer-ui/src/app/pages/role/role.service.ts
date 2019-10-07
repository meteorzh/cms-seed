import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/common/base-http.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { ServeCtx } from 'src/app/common/base.service';

/**
 * @description
 * 角色接口
 */
@Injectable()
export class RoleService extends BaseHttpService {

    constructor(protected http: HttpClient, protected router: Router, protected messageService: NzMessageService) {
        super(http, router, messageService);
    }

    save(role: any, ctx: ServeCtx) {
        super.post({ url: "/api/sys/role/save", body: role }, ctx);
    }

    del(id: number, ctx: ServeCtx) {
        super.delete({ url: "/api/sys/role/del/" + id.toString() }, ctx);
    }

    page(key: string, pageNo: number, pageSize: number, ctx: ServeCtx) {
        let query = new HttpParams({ fromObject: {pageNo: pageNo.toString(), pageSize: pageSize.toString(), key: key} });
        super.get({ url: "/api/sys/role/page", params: query }, ctx);
    }

}