import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GeneratorService } from './generator.service';

@Component({
    selector: 'app-generator',
    templateUrl: './generator.component.html',
    styleUrls: ['./generator.component.css']
})
export class GeneratorComponent implements OnInit {

    genForm: FormGroup;

    constructor(private fb: FormBuilder, private generatorService: GeneratorService) { }

    ngOnInit() {
        // 查询默认配置
        this.generatorService.defaultGenCfg({
            success: resp => {
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
        });
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
        this.generatorService.generate(param, {
            showSuccessMsg: true
        })
    }

}
