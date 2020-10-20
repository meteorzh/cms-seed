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
 * 地区信息接口
 */
@Injectable()
export class RegionService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    save(region: any): Observable<HttpEvent<CommonResponse>> {
        return super.post({ url: "/api/sys/region/save", body: region });
    }

    del(id: number): Observable<HttpEvent<CommonResponse>> {
        return super.delete({ url: `/api/sys/region/del/${id}` });
    }

    search(name: string): Observable<HttpEvent<CommonResponse>> {
        return super.get({ url: `/api/sys/region/querylist?name=${name}` });
    }

    children(pcode: number): Observable<HttpEvent<CommonResponse>> {
        return super.get({ url: `/api/sys/region/children/${pcode}` });
    }

}