import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ComponentMode } from 'src/app/common/common';
import { MultiModeComponent, CurrentState, CURRENT_DEFAULT } from 'src/app/common/multi-mode.component';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DictService } from './dict.service';
import { switchMap } from 'rxjs/operators';

@Component({
    selector: 'app-dict-edit',
    templateUrl: './dict-edit.component.html'
})
export class DictEditComponent extends MultiModeComponent implements OnInit {

    dict: any = null;

    // 是否显示字典类型中的输入内容选项
    showInputTypeOption: boolean = false;

    inputDictType: string;

    dictTypes: Array<string> = [];

    dictEditForm: FormGroup;

    constructor(private fb: FormBuilder, private router: Router, private aroute: ActivatedRoute, private dictService: DictService) {
        super(CURRENT_DEFAULT.mode);
        this.dict = CURRENT_DEFAULT.data;
    }

    ngOnInit(): void {
        if(this.isCreate) {
            // 查询 dictTypes
            this.dictService.queryTypes(null, {
                success: resp => {
                    this.dictTypes = resp.data;
                }
            });
            this.aroute.queryParams.subscribe(queryParams => {
                this.initForm(queryParams.type);
            });
        } else {
            this.initForm('');
        }
    }

    initForm(type: string) {
        this.dictEditForm = this.fb.group({
            label: [{value: this.isCreate ? '' : this.dict.label, disabled: this.isView}, this.isView ? [] : [Validators.required]],
            value: [{value: this.isCreate ? '' : this.dict.value, disabled: this.isView}, this.isView ? [] : [Validators.required]],
            type: [{value: this.isCreate ? type : this.dict.type, disabled: !this.isCreate}, this.isCreate ? [Validators.required] : []]
        });
    }

    onSearchType(event: any) {
        if(event == '') {
            this.showInputTypeOption = false;
            return;
        }
        let extMatch: boolean = false;
        for (let i = 0; i < this.dictTypes.length; i++) {
            if(this.dictTypes[i] == event) {
                extMatch = true;
                break;
            }
        }
        if(extMatch) {
            this.showInputTypeOption = false;
        } else {
            this.inputDictType = event;
            this.showInputTypeOption = true;
        }
    }

    submitEditForm(event: any) {
        console.log(event, this.dictEditForm.value);
        let obj: any = this.dictEditForm.value;
        if(this.isEdit) {
            obj.id = this.dict.id;
            obj.type = this.dict.type;
        }
        this.dictService.save(obj, {
            success: resp => {
                console.log("保存成功");
                this.dictEditForm.reset();
                this.dictService.queryTypes(null, {
                    success: resp => {
                        this.dictTypes = resp.data;
                    }
                });
            },
            showSuccessMsg: true
        });
    }

    back(event: Event) {
        console.log(event);
        this.router.navigate(['/dict']);
        event.preventDefault();
    }

    ngOnDestroy(): void {
        console.log("destory");
    }

}
