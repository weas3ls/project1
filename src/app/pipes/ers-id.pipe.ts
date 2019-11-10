import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'ersId'
})
export class ErsIdPipe implements PipeTransform {

    transform(value: number): any {
        let number = value.toString();
        const ers = 'ERS-';
        for (let i = number.length; i < 5; i++) {
            number = "0"+number;
        }
        const newNumber = ers+number;
        return newNumber;
    }
}
