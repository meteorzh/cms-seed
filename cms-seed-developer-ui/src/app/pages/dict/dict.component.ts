import { Component, OnInit } from '@angular/core';
import { CdkDragDrop } from "@angular/cdk/drag-drop";
import { ComponentMode } from 'src/app/common/common';
import { DictService } from './dict.service';
import { Router } from '@angular/router';
import { CURRENT_DEFAULT } from 'src/app/common/multi-mode.component';
import { CommonObserver } from 'src/app/common/base-http.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
    selector: 'app-dict',
    templateUrl: './dict.component.html',
    styleUrls: ['./dict.component.css']
})
export class DictComponent implements OnInit {

    dictTypes: Array<any>;

    selectedType: any;

    dicts: Array<any> = [];

    page: any = {};

    isOrderModalVisible: boolean = false;

    orderModalList: Array<any> = [];

    constructor(private dictService: DictService, private router: Router, private messageService: NzMessageService) { }

    ngOnInit() {
        this.dictService.queryTypes(null).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.dictTypes = resp.data;
                if(this.dictTypes.length > 0) {
                    this.onTypeClick(this.dictTypes[0]);
                }
            }
        ));
    }

    onTypeClick(type: string) {
        if(type == this.selectedType) {
            this.selectedType = null;
            this.dicts = [];
            return;
        }
        this.selectedType = type;
        this.dictService.queryByType(type).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.dicts = resp.data;
            }
        ));
    }

    onEditClick(dict: any) {
        console.log(dict);
        CURRENT_DEFAULT.data = dict;
        CURRENT_DEFAULT.mode = ComponentMode.EDIT;
    }

    del(dict: any) {
        console.log(dict);
        if(dict.id == undefined || dict.id == null) {
            console.error("数据没有ID，不能删除");
            return;
        }
        this.dictService.del(dict.id).subscribe(new CommonObserver(
            this.router, this.messageService, null, true
        ));
    }

    onAddClick(event: any): void {
        CURRENT_DEFAULT.mode = ComponentMode.CREATE;
        this.router.navigate(['/dict/create'], {queryParams: {type: this.selectedType}});
    }

    onDragDropped(cdd: CdkDragDrop<string[]>) {
        console.log(event);
        let target: any = this.dicts[cdd.previousIndex];
        this.dictService.reorder(target.id, this.dicts[cdd.currentIndex].order).subscribe(new CommonObserver(
            this.router, this.messageService, null, true
        ));
    }

}
