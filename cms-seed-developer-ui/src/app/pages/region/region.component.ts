import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { RegionService } from './region.service';
import { CommonObserver } from 'src/app/common/base-http.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
    selector: 'app-region',
    templateUrl: './region.component.html',
    styleUrls: ['./region.component.less']
})
export class RegionComponent implements OnInit {

    regions: any[] = [];

    loading: boolean = false;

    selectedRegion: any;

    regionForm: FormGroup;

    editVisible: boolean = false;

    editRegion: any;

    modalTitle: string;

    constructor(private fb: FormBuilder, private regionService: RegionService, private router: Router,
                private messageService: NzMessageService) { }

    ngOnInit() {
        this.refresh();

        this.regionForm = this.fb.group({
            id: [null],
            code: [null, [Validators.required]],
            name: [null, [Validators.required]],
            sname: [null, [Validators.required]],
            citycode: [null, []],
            yzcode: [null, []],
            lng: [null, []],
            lat: [null, []],
            pinyin: [null, []],
            pcode: [],
            level: []
        });
        let mernameControl: FormControl = new FormControl();
        mernameControl.disable();
        this.regionForm.addControl("mername", mernameControl);
    }

    refresh() {
        this.queryChildren(0, resp => {
            for (const n of resp.data) n.dlv = 0;
            this.regions = resp.data;
        });
    }

    onSelectChange(node: any, $event: boolean): void {
        this.selectedRegion = $event ? node : null;
    }

    collapse(index: number, node: any, $event: boolean) {
        // event: true - 展开, false - 收起
        if ($event) {
            if (node.children) {
                this.regions.splice(index + 1, 0, ...node.children);
            } else {
                this.queryChildren(node.code, resp => {
                    for (const n of resp.data) {
                        n.parent = node;
                        n.dlv = node.dlv + 1;
                    }
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

    nameControlChange($event: any): void {
        let baseMer;
        if (this.editRegion == null) {
            // 新增
            baseMer = this.selectedRegion ? `${this.selectedRegion.mername},` : "";
        } else {
            // 更新
            baseMer = this.editRegion.parent ? `${this.editRegion.parent.mername},` : "";
        }
        this.regionForm.patchValue({ mername: `${baseMer}${$event}` });
    }

    onAddClick($event: any): void {
        this.modalTitle = "新增地区";
        this.editRegion = null;
        this.regionForm.reset();
        if (this.selectedRegion) {
            this.regionForm.patchValue({
                mername: `${this.selectedRegion.mername},`, 
                level: this.selectedRegion.level + 1,
                pcode: this.selectedRegion.code
            });
        } else {
            this.regionForm.patchValue({ level: 0, pcode: 0 });
        }
        this.editVisible = true;
    }

    onEditClick(node: any): void {
        this.modalTitle = "编辑地区";
        this.editRegion = node;
        let {dlv, expand, children, parent, ...formObj} = node;
        this.regionForm.setValue(formObj);
        this.editVisible = true;
    }

    onModalCancelClick(): void {
        this.editVisible = false;
    }

    onModalSaveClick(): void {
        let forSave = this.regionForm.getRawValue();
        this.regionService.save(forSave).subscribe(new CommonObserver(this.router, this.messageService, resp => {
            this.editVisible = false;
            this.regionForm.reset();
            this.refresh();
        }, true));
    }

    remove(node: any): void {
        this.regionService.del(node.id).subscribe(new CommonObserver(this.router, this.messageService, resp => {
            this.refresh();
        }, true));
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
        this.loading = true;
        this.regionService.children(pcode).subscribe(new CommonObserver(this.router, this.messageService, resp => {
            callback(resp);
            this.loading = false;
        }));
    }
}
