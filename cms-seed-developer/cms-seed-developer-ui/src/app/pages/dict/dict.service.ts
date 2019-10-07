import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/common/base-http.service';
import { CommonResponse } from 'src/app/common/common';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { NzMessageService } from 'ng-zorro-antd';
import { ServeCtx } from 'src/app/common/base.service';
import { Router } from '@angular/router';

/**
 * 字典条目服务
 */
@Injectable()
export class DictService extends BaseHttpService {

    constructor(protected http: HttpClient, protected router: Router, protected messageService: NzMessageService) {
        super(http, router, messageService);
    }

    save(dict: any, ctx: ServeCtx) {
        this.post({ url: "/api/sys/dict/save", body: dict }, ctx);
    }

    del(id: number, ctx: ServeCtx) {
        super.delete({ url: "/api/sys/dict/del/" + id }, ctx);
    }

    reorder(id: number, order: number, ctx: ServeCtx) {
        let headers: HttpHeaders = new HttpHeaders({ "Content-type": "application/x-www-form-urlencoded" });
        let body: HttpParams = new HttpParams({ fromObject: {"id": id.toString(), "order": order.toString()} });
        super.post({ url: "/api/sys/dict/reorder", headers: headers, body: body }, ctx);
    }

    page(params: any, ctx: ServeCtx) {
        let querys: HttpParams = new HttpParams({ fromObject: params });
        this.get({ url: "/api/sys/dict/page", params: querys }, ctx);
    }

    queryByType(type: string, ctx: ServeCtx) {
        this.get({ url: "/api/sys/dict/bytype/" + type }, ctx);
    }

    // 查询所有字典类型
    queryTypes(key: string, ctx: ServeCtx) {
        let querys: HttpParams = null;
        if(key == null) {
            querys = new HttpParams();
        } else {
            new HttpParams({fromObject: {"key": key}})
        }
        
        this.get({ url: "/api/sys/dict/types", params: querys }, ctx);
    }

}