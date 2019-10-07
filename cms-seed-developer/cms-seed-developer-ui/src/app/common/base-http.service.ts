import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpRequest, HttpParams, HttpResponse, HttpEventType } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from "rxjs/operators";
import { CommonResponse, CommonErrorCode } from './common';
import { NzMessageService } from 'ng-zorro-antd';
import { BaseService, ServeCtx } from './base.service';
import { Router } from '@angular/router';

/**
 * Http 服务基类
 */
export abstract class BaseHttpService implements BaseService {

    constructor(protected http: HttpClient, protected router: Router, protected messageService: NzMessageService) { }

    /**
     * GET 请求
     * @param url 
     * @param params 
     * @param headers 
     * @param success 
     * @param failed 
     */
    get(params: GetParam, ctx: ServeCtx) {
        let init: HttpRequestInit = { headers: params.headers, params: params.params };
        let req: HttpRequest<any> = new HttpRequest("GET", params.url, init);
        this.request(req, ctx);
    }

    /**
     * POST 请求
     * @param url 
     * @param body 
     * @param headers 
     * @param success 
     * @param failed 
     */
    post(params: PostParam, ctx: ServeCtx) {
        let init: HttpRequestInit = { headers: params.headers };
        let req: HttpRequest<any> = new HttpRequest("POST", params.url, params.body, init);
        this.request(req, ctx);
    }

    /**
     * delete 请求
     * @param url 
     * @param params 
     * @param headers 
     * @param success 
     * @param failed 
     */
    delete(params: DeleteParam, ctx: ServeCtx) {
        let init: HttpRequestInit = { headers: params.headers, params: params.params };
        let req: HttpRequest<any> = new HttpRequest("DELETE", params.url, init);
        this.request(req, ctx);
    }

    /**
     * 执行请求
     */
    private request(req: HttpRequest<any>, ctx: ServeCtx) {
        this.http.request<CommonResponse>(req).pipe(
            catchError(this.handleNetError)
        ).subscribe((resp: HttpResponse<any>) => {
            // 此处 resp 为整个 HttpResponse
            // 根据 resp.type 判断当前请求处于什么阶段
            if(resp.type == HttpEventType.Response) {
                console.log(resp);
                // 请求已到达响应阶段, 处理后台响应
                this.handleResponseBody(resp.body, ctx);
            }
        }, error => {
            console.log("请求失败", error);
            this.messageService.error(error);
        }, () => {
            console.log("请求完成");
        });
    }

    // 处理网络错误
    private handleNetError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
        }
        return throwError('Something bad happened; please try again later.');
    }

    private handleResponseBody(resp: CommonResponse, ctx: ServeCtx) {
        if(resp.code == CommonErrorCode.SUCCESS) {
            // 执行自定义处理
            if(ctx.success) {
                ctx.success(resp);
            }
            if(ctx.showSuccessMsg) {
                this.messageService.success("操作成功");
            }
        } else {
            // 业务失败
            this.handleBusinessError(resp, ctx.showFailedMsg == undefined ? true : ctx.showFailedMsg, ctx.failed);
        }
    }

    private handleBusinessError(resp: CommonResponse, showFailedMsg: boolean, failed?: (result: CommonResponse) => void) {
        // 失败类型，比如未登录，跳转到登录等
        if(resp.code == CommonErrorCode.AT_NOT_AUTHC) {
            // 跳转登录
            this.router.navigate(['/login']);
        }
        // 执行自定义处理
        if(failed) {
            failed(resp);
        }
        if(showFailedMsg) {
            this.messageService.error(resp.message);
        }
    }

}

// HttpRequest 第三个构造参数命名
interface HttpRequestInit {
    headers?: HttpHeaders;
    reportProgress?: boolean;
    params?: HttpParams;
    responseType?: 'arraybuffer' | 'blob' | 'json' | 'text';
    withCredentials?: boolean;
}

// BaseHttpRequestParam 基础请求参数
interface BaseHttpRequestParam {
    url: string;
    params?: HttpParams;
    headers?: HttpHeaders;
}

// GetParam GET请求参数
export interface GetParam extends BaseHttpRequestParam {

}

// PostParam POST请求参数
export interface PostParam extends BaseHttpRequestParam {
    body: any;
}

// DeleteParam POST请求参数
export interface DeleteParam extends BaseHttpRequestParam {
    body?: any;
}
