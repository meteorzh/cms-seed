import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dict',
  templateUrl: './dict.component.html',
  styleUrls: ['./dict.component.css']
})
export class DictComponent implements OnInit {

  dictTypes: Array<any> = ["TEST"];

  page: any = {records: []};

  constructor() { }

  ngOnInit() {
  }

  search(event: any, ) {

  }

}
