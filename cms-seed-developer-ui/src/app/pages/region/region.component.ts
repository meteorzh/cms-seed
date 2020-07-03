import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegionService } from './region.service';
import { CommonObserver } from 'src/app/common/base-http.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { ComponentMode } from 'src/app/common/common';

@Component({
    selector: 'app-region',
    templateUrl: './region.component.html',
    styleUrls: ['./region.component.less']
})
export class RegionComponent implements OnInit {

    regions: any[] = [];

    regionForm: FormGroup;

    editVisible: boolean = false;

    modalTitle: string;

    constructor(private fb: FormBuilder, private regionService: RegionService, private router: Router,
                private messageService: NzMessageService) { }

    ngOnInit() {
        this.queryChildren(0, resp => {
            for (const n of resp.data) n.dlv = 0;
            this.regions = resp.data;
        });

        this.regionForm = this.fb.group({
            code: [null, [Validators.required]],
            name: [null, [Validators.required]],
            sname: [null, [Validators.required]],
            citycode: [null, []],
            yzcode: [null, []],
            lng: [null, []],
            lat: [null, []],
            pinyin: [null, []]
        });
    }

    collapse(index: number, node: any, $event: boolean) {
        // event: true - 展开, false - 收起
        if ($event) {
            if (node.children) {
                this.regions.splice(index + 1, 0, ...node.children);
            } else {
                this.queryChildren(node.code, resp => {
                    for (const n of resp.data) n.dlv = node.dlv + 1;
                    node.children = resp.data;
                    this.regions.splice(index + 1, 0, ...resp.data);
                });
            }
        }
        // 所有子节点全部收起
        if (!$event) {
            // 移出列表中的子节点, 长度为下一个同级或高级节点
            let size = 0;
            for (let i = index + 1; i < this.regions.length; i++) {
                const region = this.regions[i];
                if (node.dlv >= region.dlv) break;
                else ++size;
            }
            this.regions.splice(index + 1, size);
            this.doCollapse(node, false, true);
        }
    }

    onAddClick($event: any): void {

    }

    onEditClick(node: any): void {

    }

    onModalCancelClick(): void {

    }

    onModalSaveClick(): void {
        
    }

    remove(node: any): void {
        this.regionService.del(node.id).subscribe(new CommonObserver(this.router, this.messageService, null, true));
    }

    /**
     * 
     * @param node 节点
     * @param collapse true - 展开, false - 收起
     * @param hierarchy 是否层级操作
     */
    private doCollapse(node: any, collapse: boolean, hierarchy: boolean): void {
        if (hierarchy && node.children) {
            for (const c of node.children) c.expand = collapse;
        }
    }

    private queryChildren(pcode: number, callback: (any) => void) {
        this.regionService.children(pcode).subscribe(new CommonObserver(this.router, this.messageService, callback));
    }
}
