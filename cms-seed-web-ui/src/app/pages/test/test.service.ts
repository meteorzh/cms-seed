import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/base/base-http.service';
import { HttpClient, HttpParams, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { ServeCtx } from 'src/app/base/base.service';
import { Observable } from 'rxjs';
import { CommonResponse } from 'src/app/common/common';

/**
 * @description
 * 测试接口
 */
@Injectable()
export class TestService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    test(): Observable<HttpEvent<any>> {
        let headers: HttpHeaders = new HttpHeaders({"Content-Type": "application/x-www-form-urlencoded"});
        let body: HttpParams = new HttpParams({ fromObject: {method: "finance.report.customerSale", v: "V1.0.0", appKey: "00001", 
            params: '{"dimensionOrder":[1,2,3],"orderColumns":[],"saleChannel":-1,"regionProvince":-1,"primaryCustomerType":-1,"dataRange":2,"timeDimension":2,"startDate":"20191212","endDate":"20200311"}'} });
        return super.post({ url: "http://192.168.1.123/mars-dc-dm/router", headers: headers, body: body });
    }

}