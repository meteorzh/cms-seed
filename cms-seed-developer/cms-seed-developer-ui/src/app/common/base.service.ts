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