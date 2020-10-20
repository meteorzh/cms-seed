import { Component, OnInit } from '@angular/core';
import { DictService } from '../dict/dict.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { PermissionService } from './permission.service';
import { Router } from '@angular/router';
import { CURRENT_DEFAULT } from 'src/app/common/multi-mode.component';
import { ComponentMode } from 'src/app/common/common';
import { CommonObserver } from 'src/app/base/base-http.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
    selector: 'app-permission',
    templateUrl: './permission.component.html',
    styleUrls: ['./permission.component.css']
})
export class PermissionComponent implements OnInit {

    searchForm: FormGroup;

    categorys: Array<any> = [];

    loading: boolean = true;

    page: any = {};

    pageNo: number = 1;

    pageSize: number = 10;

    constructor(private dictService: DictService, private fb: FormBuilder, private pmsService: PermissionService, 
                private router: Router, private messageService: NzMessageService) { }

    ngOnInit() {
        this.dictService.queryByType("PMS_CATEGORY").subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.categorys = resp.data;
            }
        ));
        this.searchForm = this.fb.group({
            key: [],
            category: []
        });
        this.pmsService.page(this.pageNo, this.pageSize, null, null).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.page = resp.data;
                this.loading = false;
            }
        ));
    }

    submitForm(event: any, value: any) {
        console.log(value);
        this.loading = true;
        this.pmsService.page(this.pageNo, this.pageSize, value.key, value.category).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.page = resp.data;
                this.loading = false;
            }
        ));
    }

    onAddClick(event: Event) {
        event.preventDefault();
        this.router.navigate(['/permission/create']);
    }

    onPageChange(event: any, reset?: boolean) {
        if(reset) {
            this.pageNo = 1;
        }
        this.loading = true;
        this.pmsService.page(this.pageNo, this.pageSize, null, null).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.page = resp.data;
                this.loading = false;
            }
        ));
    }

    onEditClick(pms: any) {
        CURRENT_DEFAULT.data = pms;
        CURRENT_DEFAULT.mode = ComponentMode.EDIT;
        this.router.navigate(["/permission/edit"]);
    }

    del(pms: any) {
        this.pmsService.del(pms.id).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.onPageChange(null, true);
            }
        ));
    }

}
