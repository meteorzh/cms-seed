// 一些组件中通用的数据
export enum ComponentMode { CREATE, EDIT, VIEW }

// 后台返回数据结构
export interface CommonResponse {
    code: string,
    message: string,
    data: any
}

// 通用错误码
export const CommonErrorCode = {
    
    SUCCESS           : "ST0000",
    
    AT_NOT_AUTHC      : "AT0002"

}

// 地区等级
export const RegionLevel: {[key: number]: string} = {
    0: "国",
    1: "省",
    2: "市",
    3: "区"
}
