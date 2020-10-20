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
 * 用户组接口
 */
@Injectable()
export class GroupService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    save(group: any): Observable<HttpEvent<CommonResponse>> {
        return super.post({ url: "/api/sys/group/save", body: group });
    }

    del(id: number): Observable<HttpEvent<CommonResponse>> {
        return super.delete({ url: "/api/sys/group/del/" + id.toString() });
    }

    page(pageNo: number, pageSize: number): Observable<HttpEvent<CommonResponse>> {
        let query = new HttpParams({ fromObject: {pageNo: pageNo.toString(), pageSize: pageSize.toString()} });
        return super.get({ url: "/api/sys/group/page", params: query });
    }

}