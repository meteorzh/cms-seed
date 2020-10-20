import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/base/base-http.service';
import { HttpClient, HttpParams, HttpEvent } from '@angular/common/http';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { ServeCtx } from 'src/app/base/base.service';
import { Observable } from 'rxjs';
import { CommonResponse } from 'src/app/common/common';

/**
 * @description
 * 角色接口
 */
@Injectable()
export class RoleService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    save(role: any): Observable<HttpEvent<CommonResponse>> {
        return super.post({ url: "/api/sys/role/save", body: role });
    }

    del(id: number): Observable<HttpEvent<CommonResponse>> {
        return super.delete({ url: "/api/sys/role/del/" + id.toString() });
    }

    page(key: string, pageNo: number, pageSize: number): Observable<HttpEvent<CommonResponse>> {
        let query = new HttpParams({ fromObject: {pageNo: pageNo.toString(), pageSize: pageSize.toString(), key: key} });
        return super.get({ url: "/api/sys/role/page", params: query });
    }

}