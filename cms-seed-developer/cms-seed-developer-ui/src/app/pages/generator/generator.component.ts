import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-generator',
    templateUrl: './generator.component.html',
    styleUrls: ['./generator.component.css']
})
export class GeneratorComponent implements OnInit {

    genForm: FormGroup;

    constructor(private fb: FormBuilder) { }

    ngOnInit() {
        this.genForm = this.fb.group({
            projectPath: ["", [Validators.required]],
            author: [],
            entitySuffix: [],
            outputPackage: [],
            moduleName: [],
            tableName: ["", [Validators.required]],
            tableNamePrefix: []
        });
    }

    submitForm(event: any, value: any) {

    }

}
