import { Injectable } from '@angular/core';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import { Factura } from '../../models/factura/factura';

@Injectable({
  providedIn: 'root'
})
export class PdfMakeService {

  constructor() { }


  generatePdf(factura:Factura){
    console.log('generating PDF of ', factura);

    const documentDefinition = { content: [
        {
        table: {
          widths: [100, '*', 300],
          body: [
            
            [{text: 'LOGO', bold: true, fontSize: 30,alignment: 'left',color:'red'}, 
              {}, 
              {table: {
                body: [
                  ['FACTURA'],
                  ['779ff6ad-fb46-4db3-ba6c-29df1336cd5'],
                  ['Folio Fiscal'],
                  ['4ACE5690-1626-11EA-BADB-058C994FD806'],
                  ['Fecha y hora de emisión'],
                  ['2019-12-03T16:47:45']
                ]
              },
              alignment: 'right'
            }]
          ]
        },
        margin: [0, 10, 0, 0],
      },
      {
        table: {
          widths: ['*', '*'],
          body: [
            [{text: 'LINEAS AEREAS NACIONALES SA. DE CV.', style: 'tableHeader', colSpan: 2, alignment: 'left'},{}],
            [{text:'R.F.C',bold:true,style:'normal'},{text:'LAN7008173R5',alignment: 'left',style:'normal'}],
            [{text:'REGIMEN FISCAL',bold:true,style:'normal'},{text:'601 - GENERAL DE LEY PERSONAS MORALES',alignment: 'left',style:'normal'}],
            [{text:'LUGAR DE EXPEDICION',bold:true,style:'normal'},{text:'08630',alignment: 'left',style:'normal'}]
          ]
        },  
      }
    ],
    styles: {
        normal: {
          fontSize: 8,
          color: 'black'
        },
        tableHeader: {
          bold: true,
          fontSize: 11,
          color: 'black'
        }
      }
    };
    pdfMake.createPdf(documentDefinition).open();
   }
}
