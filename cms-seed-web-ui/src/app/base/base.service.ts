import { Observer } from 'rxjs';

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

    constructor(next?: (value: T) => void, error?: (err: any) => void, complete?: () => void) {
        this.next = next;
        this.error = error;
        this.complete = complete;
    }

}