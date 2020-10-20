import { Component, OnInit } from '@angular/core';
import { data } from "./test.data";
import { TestService } from './test.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { CommonObserver } from 'src/app/base/base-http.service';

@Component({
    selector: 'test',
    templateUrl: './test.component.html',
    styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

    data: any = data;

    testResult: any;

    constructor(private testService: TestService, private router: Router, private messageService: NzMessageService) { }

    ngOnInit(): void { }

    clickHeadCol(rowdata: any) {
        console.log("aaa");
        rowdata.collapse = !rowdata.collapse;
    }

    getLast(children: any[]): any {
        return children[children.length - 1];
    }

    test(event: any): void {
        this.testService.test().subscribe(new CommonObserver(this.router, this.messageService, resp => {
            this.testResult = resp.data
        }, true));
    }
}
