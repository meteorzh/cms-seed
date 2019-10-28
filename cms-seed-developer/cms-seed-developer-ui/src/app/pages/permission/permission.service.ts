import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/common/base-http.service';
import { HttpClient, HttpParams, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommonResponse } from 'src/app/common/common';

@Injectable()
export class PermissionService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    save(value: any): Observable<HttpEvent<CommonResponse>> {
        return super.post({url: "/api/sys/pms/save", body: value});
    }

    del(id: number): Observable<HttpEvent<CommonResponse>> {
        return super.delete({ url: "/api/sys/pms/del/" + id });
    }

    page(pageNo: number, pageSize: number, key: string, category: number): Observable<HttpEvent<CommonResponse>> {
        let fromObj: any = {pageNo: pageNo.toString(), pageSize: pageSize.toString()};
        if(key != null) {
            fromObj.key = key;
        }
        if(category != null) {
            fromObj.category = category;
        }
        let querys: HttpParams = new HttpParams({ fromObject: fromObj });
        return super.get({ url: "/api/sys/pms/page", params: querys });
    }

}