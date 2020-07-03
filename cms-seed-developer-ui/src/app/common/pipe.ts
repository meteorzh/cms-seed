import { Pipe, PipeTransform } from '@angular/core';

/**
 * 字符串替换管道
 */
@Pipe({name: 'strReplace'})
export class StrReplacePipe implements PipeTransform {

    transform(value: any, regExp: string, replace: string): any {
        if((typeof value != 'string') || regExp == null || regExp == "" || replace == null || replace == "") {
            return value;
        }
        return value.replace(new RegExp(regExp, 'g'), replace);
    }

}