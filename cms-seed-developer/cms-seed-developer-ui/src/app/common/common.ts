// 一些组件中通用的数据
export enum ComponentMode { CREATE, EDIT, VIEW }

// 后台返回数据结构
export interface CommonResponse {
    code: string,
    message: string,
    data: any
}

export const CommonErrorCode = {
    
    SUCCESS           : "ST0000",
    
    AT_NOT_AUTHC      : "AT0002"

}
