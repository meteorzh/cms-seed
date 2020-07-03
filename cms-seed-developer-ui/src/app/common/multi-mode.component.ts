import { ComponentMode } from './common';

// 具有多个模式的组件
export abstract class MultiModeComponent {

    // 当前模式
    mode: ComponentMode;

    // 是否是创建模式
    isCreate: boolean;

    // 是否是编辑模式
    isEdit: boolean;

    // 是否是查看模式
    isView: boolean;

    constructor(mode: ComponentMode) {
        this.mode = mode;
        this.isCreate = mode == ComponentMode.CREATE;
        this.isEdit = mode == ComponentMode.EDIT;
        this.isView = mode == ComponentMode.VIEW;
    }

}

// 多模式组件当前状态对象
export interface CurrentState {
    mode: ComponentMode;
    data: any;
}

// CURRENT_DEFAULT 默认多状态组件全局变量
export const CURRENT_DEFAULT: CurrentState = {
    mode: null,
    data: null,
}