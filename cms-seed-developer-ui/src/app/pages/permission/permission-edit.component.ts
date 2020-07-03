import { Component, OnInit } from '@angular/core';
import { MultiModeComponent, CURRENT_DEFAULT } from 'src/app/common/multi-mode.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PermissionService } from './permission.service';
import { DictService } from '../dict/dict.service';
import { Router } from '@angular/router';
import { CommonObserver } from 'src/app/common/base-http.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
    selector: 'app-permission-edit',
    templateUrl: './permission-edit.component.html',
    styleUrls: []
})
export class PermissionEditComponent extends MultiModeComponent implements OnInit {

    pms: any;

    pmsForm: FormGroup;

    categorys: Array<any> = [];

    constructor(private fb: FormBuilder, private pmsService: PermissionService, 
            private dictService: DictService, private router: Router, private messageService: NzMessageService) {
        super(CURRENT_DEFAULT.mode);
        if(!this.isCreate) {
            this.pms = CURRENT_DEFAULT.data;
        }
    }

    ngOnInit(): void {
        this.dictService.queryByType("PMS_CATEGORY").subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.categorys = resp.data;
            }
        ));
        this.pmsForm = this.fb.group({
            code: [{value: this.isCreate ? '' : this.pms.code, disabled: !this.isCreate}, this.isCreate ? [Validators.required] : []],
            label: [{value: this.isCreate ? '' : this.pms.label, disabled: this.isView}, this.isView ? [] : [Validators.required]],
            description: [{value: this.isCreate ? '' : this.pms.description, disabled: this.isView}],
            category: [{value: this.isCreate ? 1 : this.pms.category, disabled: this.isView}, this.isView ? [] : [Validators.required]],
        });
    }

    submitForm(event: any, value: any) {
        if(this.isEdit) {
            value.id = this.pms.id;
            value.code = this.pms.code;
        }
        this.pmsService.save(value).subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.pmsForm.reset();
                if(this.isEdit) {
                    this.router.navigate(['/permission'])
                }
            }
        ));
    }

    back(event: Event) {
        console.log(event);
        this.router.navigate(['/permission']);
        event.preventDefault();
    }

}
