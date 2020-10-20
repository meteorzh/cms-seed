import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/base/base-http.service';
import { CommonResponse } from 'src/app/common/common';
import { HttpClient, HttpParams, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * 字典条目服务
 */
@Injectable()
export class DictService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    save(dict: any): Observable<HttpEvent<CommonResponse>> {
        return this.post({ url: "/api/sys/dict/save", body: dict });
    }

    del(id: number): Observable<HttpEvent<CommonResponse>> {
        return super.delete({ url: "/api/sys/dict/del/" + id });
    }

    reorder(id: number, order: number): Observable<HttpEvent<CommonResponse>> {
        let headers: HttpHeaders = new HttpHeaders({ "Content-type": "application/x-www-form-urlencoded" });
        let body: HttpParams = new HttpParams({ fromObject: {"id": id.toString(), "order": order.toString()} });
        return super.post({ url: "/api/sys/dict/reorder", headers: headers, body: body });
    }

    page(params: any): Observable<HttpEvent<CommonResponse>> {
        let querys: HttpParams = new HttpParams({ fromObject: params });
        return this.get({ url: "/api/sys/dict/page", params: querys });
    }

    queryByType(type: string): Observable<HttpEvent<CommonResponse>> {
        return this.get({ url: "/api/sys/dict/bytype/" + type });
    }

    // 查询所有字典类型
    queryTypes(key: string): Observable<HttpEvent<CommonResponse>> {
        let querys: HttpParams = null;
        if(key == null) {
            querys = new HttpParams();
        } else {
            new HttpParams({fromObject: {"key": key}})
        }
        
        return this.get({ url: "/api/sys/dict/types", params: querys });
    }

}