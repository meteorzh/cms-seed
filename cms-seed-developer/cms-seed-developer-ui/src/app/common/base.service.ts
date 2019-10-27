import { Observer } from 'rxjs';
import { NzMessageService } from 'ng-zorro-antd';

// BaseService 基础服务接口
export interface BaseService {

}

// ServeCtx 服务Context
export interface ServeCtx {
    success?: (result: any) => void;
    showSuccessMsg?: boolean;
    failed?: (result: any) => void;
    showFailedMsg?: boolean;
}

/**
 * 服务观察者类
 */
export class ServeObserver<T> implements Observer<T> {
    
    closed?: boolean;    
    
    next: (value: T) => void;
    
    error: (err: any) => void;
    
    complete: () => void;

    constructor(protected messageService: NzMessageService, next?: (value: T) => void, error?: (err: any) => void, complete?: () => void) {
        this.next = next;
        if (error != null) {
            this.error = error;
        } else {
            this.error = this.defaultError;
        }
        this.complete = complete;
    }

    private defaultError(err: any) {
        console.log("请求失败", err);
        this.messageService.error(err);
    }

}