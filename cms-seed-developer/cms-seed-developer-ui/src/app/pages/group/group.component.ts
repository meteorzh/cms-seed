import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { GroupService } from './group.service';

@Component({
    selector: 'app-group',
    templateUrl: './group.component.html',
    styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

    addForm: FormGroup;

    page: any = {};

    loading: boolean = false;

    pageNo: number = 1;

    pageSize: number = 10;

    editCache: { [key: string]: any } = {};

    constructor(private fb: FormBuilder, private groupService: GroupService) { }

    ngOnInit() {
        this.addForm = this.fb.group({
            name: ['', [Validators.required]],
            description: []
        });
        this.onPageChange(null);
    }

    submitForm(event: any, value: any) {
        this.groupService.save(value, {
            success: resp => {
                this.onPageChange(null);
                this.addForm.reset();
            },
            showSuccessMsg: true
        });
    }

    onPageChange(event: any, reset?: boolean) {
        this.groupService.page(this.pageNo, this.pageSize, {
            success: resp => {
                this.page = resp.data;
                this.updateEditCache();
            }
        });
    }

    startEditRow(group: any) {
        this.editCache[group.id].edit = true;
    }

    saveRow(group: any) {
        this.groupService.save(this.editCache[group.id].data, {
            success: resp => {
                let data = this.editCache[group.id].data;
                group.name = data.name;
                group.description = data.description;
                this.editCache[group.id].edit = false;
            },
            showSuccessMsg: true
        });
    }

    cancelEdit(group: any) {
        this.editCache[group.id].edit = false;
        this.editCache[group.id].data = {...group};
    }

    del(group: any) {
        this.groupService.del(group.id, {
            success: resp => {
                this.onPageChange(null);
            }
        });
    }

    private updateEditCache() {
        this.page.records.forEach(item => {
            this.editCache[item.id] = {
                edit: false,
                data: { ...item }
            }
        });
    }

}
