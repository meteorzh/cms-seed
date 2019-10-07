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
export class GroupService extends BaseHttpService {

    constructor(protected http: HttpClient, protected router: Router, protected messageService: NzMessageService) {
        super(http, router, messageService);
    }

    save(group: any, ctx: ServeCtx) {
        super.post({ url: "/api/sys/group/save", body: group }, ctx);
    }

    del(id: number, ctx: ServeCtx) {
        super.delete({ url: "/api/sys/group/del/" + id.toString() }, ctx);
    }

    page(pageNo: number, pageSize: number, ctx: ServeCtx) {
        let query = new HttpParams({ fromObject: {pageNo: pageNo.toString(), pageSize: pageSize.toString()} });
        super.get({ url: "/api/sys/group/page", params: query }, ctx);
    }

}