import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpRequest, HttpParams, HttpResponse, HttpEventType, HttpEvent } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, map } from "rxjs/operators";
import { CommonResponse, CommonErrorCode } from './common';
import { NzMessageService } from 'ng-zorro-antd';
import { BaseService, ServeCtx, ServeObserver } from './base.service';
import { Router } from '@angular/router';

/**
 * Http 服务基类
 */
export abstract class BaseHttpService implements BaseService {

    constructor(protected http: HttpClient) { }

    /**
     * GET 请求
     * @param url 
     * @param params 
     * @param headers 
     * @param success 
     * @param failed 
     */
    get(params: GetParam): Observable<HttpEvent<CommonResponse>> {
        let init: HttpRequestInit = { headers: params.headers, params: params.params };
        let req: HttpRequest<any> = new HttpRequest("GET", params.url, init);
        return this.request(req);
    }

    /**
     * POST 请求
     * @param url 
     * @param body 
     * @param headers 
     * @param success 
     * @param failed 
     */
    post(params: PostParam): Observable<HttpEvent<CommonResponse>> {
        let init: HttpRequestInit = { headers: params.headers };
        let req: HttpRequest<any> = new HttpRequest("POST", params.url, params.body, init);
        return this.request(req);
    }

    /**
     * delete 请求
     * @param url 
     * @param params 
     * @param headers 
     * @param success 
     * @param failed 
     */
    delete(params: DeleteParam): Observable<HttpEvent<CommonResponse>> {
        let init: HttpRequestInit = { headers: params.headers, params: params.params };
        let req: HttpRequest<any> = new HttpRequest("DELETE", params.url, init);
        return this.request(req);
    }

    /**
     * 执行请求
     */
    private request(req: HttpRequest<any>): Observable<HttpEvent<CommonResponse>> {
        return this.http.request<CommonResponse>(req).pipe(
            catchError(this.handleNetError)
        );
    }

    /**
     * 处理错误
     * 本方法仅对错误进行了一次封装
     * @param error 
     */
    private handleNetError(error: HttpErrorResponse): Observable<never> {
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

/**
 * HTTP 服务观察者
 * 如果此类构造参数指定了customNext, 那么本类的 defaultNext 方法将不会被执行
 */
export class HttpRequestObserver<T> extends ServeObserver<HttpEvent<T>> {

    constructor(next?: (value: HttpEvent<T>) => void, error?: (err: any) => void, complete?: () => void) {
        super(next, error, complete);
        if (this.next == null) {
            this.next = this.defaultNext;
        }
    }

    private defaultNext(value: HttpEvent<T>) {
        console.log(value);
        // 此处 resp 为整个 HttpResponse
        // 根据 resp.type 判断当前请求处于什么阶段
        if(value.type == HttpEventType.Response) {
            // 请求已到达响应阶段, 处理后台响应
            this.handleResponse(value);
        }
    }

    protected handleResponse(resp: HttpResponse<T>): void {
        console.warn("default implement of HttpRequestObserver");
    }

}

/**
 * 通用 Http 请求观察者对象
 */
export class CommonObserver extends HttpRequestObserver<CommonResponse> {

    constructor(private router: Router, private messageService: NzMessageService,
                private success?: (result: CommonResponse) => void, private showSuccessMsg?: boolean,
                private failed?: (result: CommonResponse) => void, private showFailedMsg?: boolean) {
        super();
        this.error = this.commonError;
        if (this.showFailedMsg == null) {
            this.showFailedMsg = true;
        }
    }

    private commonError(err: any): void {
        console.error(err);
        this.messageService.error(err);
    }

    protected handleResponse(resp: HttpResponse<CommonResponse>): void {
        let body = resp.body;
        if(body.code == CommonErrorCode.SUCCESS) {
            // 执行自定义处理
            if(this.success != null) {
                this.success(body);
            }
            if(this.showSuccessMsg) {
                this.messageService.success("操作成功");
            }
        } else {
            // 业务失败
            this.handleFailedResponse(body);
        }
    }

    private handleFailedResponse(resp: CommonResponse) {
        // 失败类型，比如未登录，跳转到登录等
        if(resp.code == CommonErrorCode.AT_NOT_AUTHC) {
            // 跳转登录
            this.router.navigate(['/login']);
        }
        // 执行自定义处理
        if(this.failed != null) {
            this.failed(resp);
        }
        if(this.showFailedMsg) {
            this.messageService.error(resp.message);
        }
    }

}
