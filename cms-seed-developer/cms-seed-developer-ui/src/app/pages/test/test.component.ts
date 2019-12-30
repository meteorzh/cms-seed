import { Component, OnInit } from '@angular/core';
import { data } from "./test.data";

@Component({
    selector: 'test',
    templateUrl: './test.component.html',
    styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

    data: any = data;

    constructor() { }

    ngOnInit(): void { }

    clickHeadCol(rowdata: any) {
        console.log("aaa");
        rowdata.collapse = !rowdata.collapse;
    }

    getLast(children: any[]): any {
        return children[children.length - 1];
    }
}
