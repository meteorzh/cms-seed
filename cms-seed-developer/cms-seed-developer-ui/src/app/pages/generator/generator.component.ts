import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GeneratorService } from './generator.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { CommonObserver } from 'src/app/common/base-http.service';

@Component({
    selector: 'app-generator',
    templateUrl: './generator.component.html',
    styleUrls: ['./generator.component.css']
})
export class GeneratorComponent implements OnInit {

    genForm: FormGroup;

    constructor(private fb: FormBuilder, private generatorService: GeneratorService,
                private router: Router, private messageService: NzMessageService) { }

    ngOnInit() {
        // 查询默认配置
        this.generatorService.defaultGenCfg().subscribe(new CommonObserver(
            this.router, this.messageService,
            resp => {
                this.genForm = this.fb.group({
                    projectPath: ["", [Validators.required]],
                    author: [],
                    entitySuffix: [],
                    outputPackage: [],
                    moduleName: [],
                    tableName: ["", [Validators.required]],
                    tableNamePrefix: [],
                    template: ["", [Validators.required]],
                    url: [resp.data.dataSourceInfo.url, [Validators.required]],
                    driver: [resp.data.dataSourceInfo.driver, [Validators.required]],
                    username: [resp.data.dataSourceInfo.username, [Validators.required]],
                    password: [resp.data.dataSourceInfo.password, [Validators.required]]
                });
            }
        ));
        this.genForm = this.fb.group({
            projectPath: ["", [Validators.required]],
            author: [],
            entitySuffix: [],
            outputPackage: [],
            moduleName: [],
            tableName: ["", [Validators.required]],
            tableNamePrefix: [],
            template: ["", [Validators.required]],
            url: ["", [Validators.required]],
            driver: ["", [Validators.required]],
            username: ["", [Validators.required]],
            password: ["", [Validators.required]]
        });
    }

    submitForm(event: any, value: any) {
        let param: any = {
            projectPath: value.projectPath,
            author: value.author,
            entitySuffix: value.entitySuffix,
            outputPackage: value.outputPackage,
            moduleName: value.moduleName,
            tableName: value.tableName,
            tableNamePrefix: value.tableNamePrefix,
            template: value.template,
            dataSourceInfo: {
                url: value.url,
                driver: value.driver,
                username: value.username,
                password: value.password
            }
        };
        this.generatorService.generate(param).subscribe(new CommonObserver(
            this.router, this.messageService, null, true
        ));
    }

}
