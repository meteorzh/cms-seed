import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/common/base-http.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { ServeCtx } from 'src/app/common/base.service';
import { from } from 'rxjs';

@Injectable()
export class PermissionService extends BaseHttpService {

    constructor(protected http: HttpClient, protected router: Router, protected messageService: NzMessageService) {
        super(http, router, messageService);
    }

    save(value: any, ctx: ServeCtx) {
        super.post({url: "/api/sys/pms/save", body: value}, ctx);
    }

    del(id: number, ctx: ServeCtx) {
        super.delete({ url: "/api/sys/pms/del/" + id }, ctx);
    }

    page(pageNo: number, pageSize: number, key: string, category: number, ctx: ServeCtx) {
        let fromObj: any = {pageNo: pageNo.toString(), pageSize: pageSize.toString()};
        if(key != null) {
            fromObj.key = key;
        }
        if(category != null) {
            fromObj.category = category;
        }
        let querys: HttpParams = new HttpParams({ fromObject: fromObj });
        super.get({ url: "/api/sys/pms/page", params: querys }, ctx);
    }

}