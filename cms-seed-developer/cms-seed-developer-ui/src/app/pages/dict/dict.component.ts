import { Component, OnInit } from '@angular/core';
import { CdkDragDrop } from "@angular/cdk/drag-drop";
import { ComponentMode } from 'src/app/common/common';
import { DictService } from './dict.service';
import { Router } from '@angular/router';
import { CURRENT_DEFAULT } from 'src/app/common/multi-mode.component';

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

    constructor(private dictService: DictService, private router: Router) { }

    ngOnInit() {
        this.dictService.queryTypes(null, {
            success: resp => {
                this.dictTypes = resp.data;
                if(this.dictTypes.length > 0) {
                    this.onTypeClick(this.dictTypes[0]);
                }
            }
        });
    }

    onTypeClick(type: string) {
        if(type == this.selectedType) {
            this.selectedType = null;
            this.dicts = [];
            return;
        }
        this.selectedType = type;
        this.dictService.queryByType(type, {
            success: resp => {
                this.dicts = resp.data;
            }
        });
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
        this.dictService.del(dict.id, {
            showSuccessMsg: true
        });
    }

    onAddClick(event: any): void {
        CURRENT_DEFAULT.mode = ComponentMode.CREATE;
        this.router.navigate(['/dict/create'], {queryParams: {type: this.selectedType}});
    }

    onDragDropped(cdd: CdkDragDrop<string[]>) {
        console.log(event);
        let target: any = this.dicts[cdd.previousIndex];
        this.dictService.reorder(target.id, this.dicts[cdd.currentIndex].order, {
            success: resp => {
                this.dictService.queryByType(this.selectedType, {
                    success: resp => {
                        this.dicts = resp.data;
                    }
                });
            },
            showSuccessMsg: true
        });
    }

}
