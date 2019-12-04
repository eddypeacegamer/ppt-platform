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
          widths: [150, 300],
          body: [
            [{text: 'LINEAS AEREAS NACIONALES SA. DE CV.', style: 'tableHeader', colSpan: 2, alignment: 'left'},{}],
            [{text:'R.F.C',bold:true},{text:'LAN7008173R5',alignment: 'left'}],
            [{text:'REGIMEN FISCAL',bold:true},{text:'601 - GENERAL DE LEY PERSONAS MORALES',alignment: 'left'}],
            [{text:'LUGAR DE EXPEDICION',bold:true},{text:'08630',alignment: 'left'}]
          ]
        },
        styles: {
          
          tableHeader: {
            bold: true,
            fontSize: 13,
            color: 'black'
          }
        }
      }
    ]};
    pdfMake.createPdf(documentDefinition).open();
   }
}
