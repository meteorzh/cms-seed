import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RoleService } from './role.service';

@Component({
    selector: 'app-role',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

    addForm: FormGroup;

    searchKey: string = "";

    page: any = {};

    loading: boolean = false;

    pageNo: number = 1;

    pageSize: number = 10;

    editCache: { [key: string]: any } = {};

    constructor(private fb: FormBuilder, private roleService: RoleService) { }

    ngOnInit() {
        this.addForm = this.fb.group({
            code: ['', [Validators.required]],
            name: ['', [Validators.required]],
            description: []
        });
        this.onPageChange(null);
    }

    submitForm(event: any, value: any) {
        this.roleService.save(value, {
            success: resp => {
                this.onPageChange(null);
                this.addForm.reset();
            },
            showSuccessMsg: true
        });
    }

    onSearchClick(event: any) {
        this.onPageChange(null, true);
    }

    onPageChange(event: any, reset?: boolean) {
        this.roleService.page(this.searchKey, this.pageNo, this.pageSize, {
            success: resp => {
                this.page = resp.data;
                this.updateEditCache();
            }
        });
    }

    startEditRow(role: any) {
        this.editCache[role.id].edit = true;
    }

    saveRow(role: any) {
        this.roleService.save(this.editCache[role.id].data, {
            success: resp => {
                let data = this.editCache[role.id].data;
                role.code = data.code;
                role.name = data.name;
                role.description = data.description;
                this.editCache[role.id].edit = false;
            },
            showSuccessMsg: true
        });
    }

    cancelEdit(role: any) {
        this.editCache[role.id].edit = false;
        this.editCache[role.id].data = {...role};
    }

    del(role: any) {
        this.roleService.del(role.id, {
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
