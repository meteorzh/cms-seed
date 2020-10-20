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
export class GeneratorService extends BaseHttpService {

    constructor(http: HttpClient) {
        super(http);
    }

    defaultGenCfg(): Observable<HttpEvent<CommonResponse>> {
        return super.get({ url: "/api/sys/codegen/defaultgencfg" });
    }

    generate(param: any): Observable<HttpEvent<CommonResponse>> {
        return super.post({ url: "/api/sys/codegen/gen", body: param });
    }

}