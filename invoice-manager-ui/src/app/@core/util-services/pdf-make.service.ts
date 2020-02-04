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
          widths: ['*', 190, 190],
          body: [
            [{text: 'LOGO', bold: true, fontSize: 30,alignment: 'left',color:'red'}, 
              {table: {
                widths: [190],
                body: [
                  [{text: 'LINEAS AEREAS NACIONALES SA. DE CV.', style: 'tableHeader', alignment: 'left'}],
                  [{text:[{text:'R.F.C',bold:true},'LAN7008173R5'],style:'normal'}],
                  [{text:[{text:'REGIMEN FISCAL',bold:true},'601 - GENERAL DE LEY PERSONAS MORALES'],style:'normal'}],
                  [{text:[{text:'LUGAR DE EXPEDICION',bold:true},'08630'],style:'normal'}],
                ],
              },
              alignment: 'left',
              layout: 'noBorders',
              }, 
              {table: {
                widths: ['*'],
                body: [
                  [{text :'FACTURA', bold:true, fontSize:12, color:'red'}],
                  [{text:'779ff6ad-fb46-4db3-ba6c-29df1336cd5',style:'normal'}],
                  [{text:'Folio Fiscal',bold:true,style:'normal'}],
                  [{text:'4ACE5690-1626-11EA-BADB-058C994FD806',style:'normal'}],
                  [{text:'Fecha y hora de emisión',bold:true,style:'normal'}],
                  [{text:'2019-12-03T16:47:45',style:'normal'}]
                ]
              },
              layout: 'noBorders',
              alignment: 'right'
            }],
          ],
        },
        alignment: 'center',
        layout: 'noBorders',
        margin: [0, 10,0, 50]
      },{
        table:{
          widths: [100, '*'],
          body: [
                  [{text: 'Datos cliente', style: 'tableHeader', colSpan: 2, alignment: 'left'},{}],
                  [{text:'Razón Social :',bold:true,style:'normal'},{text:'RESTAURANTECENTROCASTELLANO,S.A.DEC.V',alignment: 'left',style:'normal'}],
                  [{text:'R.F.C :',bold:true,style:'normal'},{text:'RCC920604445',alignment: 'left',style:'normal'}],
                  [{text:'Uso CFDI :',bold:true,style:'normal'},{text:'P01 - Por definir',alignment: 'left',style:'normal'}]
                ],
              },
          alignment: 'left',
          layout: 'noBorders'
      },{
        table:{
          widths: [50,50,'*',70,80,50,50],
          body: [
                  [{text: 'Cantidad', style: 'tableHeader'},{text: 'ClaveSAT', style: 'tableHeader'},{text: 'Descripción', style: 'tableHeader'},{text: 'Unidad', style: 'tableHeader'},{text: 'PrecioUnitario', style: 'tableHeader'},{text: 'Descuento', style: 'tableHeader'},{text: 'Importe', style: 'tableHeader'}],
                  [{text:'1',style:'normal'},{text:'70171702',style:'normal'},{text:'Revisión documentos perido 2019',style:'normal'},{text:'E48 - Unidad de Servicio',style:'normal'},{text:'30,000.00',style:'normal'},{text:'0.00',style:'normal'},{text:'30,000.00',style:'normal'}],
                  [{},{},{},{},{},{text:'SUBTOTAL',bold:true,style:'normal'},{text:'$ 30,000.00',alignment: 'right',style:'normal'}],
                  [{},{},{},{},{},{text:'IVA 16.00%',bold:true,style:'normal'},{text:'$4,800.00',alignment: 'right',style:'normal'}],
                  [{},{},{},{},{},{text:'TOTAL',bold:true,style:'normal'},{text:'$ 34,800.00',alignment: 'right',style:'normal'}]
                ]
        },
        alignment: 'center',
        layout: 'noBorders',
        margin: [0, 10, 0, 180]
      },{
        table:{
          widths: ['*', 'auto'],
          body:[
            [{text:'Importe con letra :',bold:true,style:'normal', alignment: 'left'},{text:'TREINTA Y CUATRO MIL OCHOCIENTOS PESOS 00/100 M.N.',bold:true,style:'normal', alignment: 'left'}]
          ]
        }
      },{
        table:{
          widths: ['*', 'auto'],
          body:[
            [{text:[{text:'Forma de pago : ', bold:true},'99 - POR DEFINIR'],style:'normal'},{text:[{text:'No. CSD del Emisor : ', bold:true},'20001000000300022815'],style:'normal'}],
            [{text:[{text:'Condiciones de pago : ', bold:true}],style:'normal'},{text:[{text:'No. CSD del SAT : ', bold:true},'20001000000300022323'],style:'normal'}],
            [{text:[{text:'Método de pago : ', bold:true},'PPD - PAGO EN PARCIALIDADES O DIFERIDO'],style:'normal'},{text:[{text:'Fecha y hora de certificación : ', bold:true},'2019-12-03T17:40:30'],style:'normal'}],
            [{text:[{text:'Tipo de Comprobante : ', bold:true},'I - INGRESO'],style:'normal'},{text:[{text:'Moneda : ', bold:true},'MXN'],style:'normal'}],
          ]
        },
        layout: 'noBorders',
      },{
        table: {
          widths: [100, 400],
          body: [
            [{text: 'QR', bold: true, fontSize: 30,alignment: 'left',color:'red'}, 
             {stack:[
               {text:'SELLO DIGITAL DEL EMISOR',style:'normal',color:'blue'},
               {text:'dgRG58NMIB7BdRGGN3Ba8aNSTukdIL5/d1XzppI1vh4Oq5CH1aemwS22y3kAW3XlOr92wXMp3xubFrZksV0jk5oxZ/WNv5ab+8Ntk6vWGmWBwEI/pJaA4Bpoye5qEztN8iUQzEgnmrZ0S989+BQi2tGEGtfI+cv8xb8PHHRhgm/iscs/A9VaBHfzdJS4Hs8E9x/PzQKPMgMMIG6J35NjTD8115OmwJ7C0TzoiPQ7TvqRU3fou1myxSdoa3c68avr1nkiy5WrEIL0WMzs817XYNLsq9oTwxn1jfN1VXLdoHhbYIv47Gei5kFIx3ghtK7MGHWC+ZnwXUa0iAJZY0Ukg==',style:'normal',fontSize: 6},
               {text:'SELLO DEL SAT',style:'normal',color:'blue'},
               {text:'JrItWA18GLL9j/cJwPQSay+wlCzCkwWHdjK43LymMKNP1aWZDXMBL03EyIB6r0/fULv1e3WvG7Fr39avy59eUryhURE1OSHjzDSMOhIFLN8LrOmvKW6OMZLBtGbEYHaTDHa4/MMGuIeBPSFzQubqInh6G/kKIM2hZsB2kz+mmRVcw0iSmb5ra6VZmWHjb28IRk1cnd8ladqx4VxmPMRrqYx0OCEVEc3l0Z7IpNkmVee/+jpIMZRPLFSqFUItT5dxeyqCD9OmtyWEyZgZhanLi41N2fKonTcbL3J4wvVdV+827uVAR7J9U8UrGDs6Lk01Jp5Vo8tbqar4bPujgXcs8A==',style:'normal',fontSize: 6},
               {text:'CADENA ORIGINAL DEL COMPLEMENTO DE CERTIFICACION DIGITAL DEL SAT',style:'normal',color:'blue'},
               {text:'||1.1|4ACE5690-1626-11EA-BADB-058C994FD806|2019-12-03T17:40:30|FMO1007168C6|dgRG58NMIB7BdRGGN3Ba8aNSTukdIL5/d1XzppI1vh4Oq5CH1aemwS22y3kAW3XlOr92wXMp3xubFrZksV0jk5oxZ/WNv5ab+8Ntk6vWGmWBwEI/pJaA4Bpoye5qEztN8iUQzEgnmrZ0S989+BQi2tGEGtfI+cv8xb8PHHRhgm/isc s/A9VaBHfzdJS4Hs8E9x/PzQKPMMMIG6J35NjTD8115OmwJ7C0TzoiPQ7TvqRU3fou1myxSdoa3c68avr1nkiy5WrEIL0WMzs817XYNLsq9oTwxn1jfN1VXLdoHhbYIv47Gei5kFIx3ghtK7MGHWC+ZnwXUa0iAJZY0Ukg==|20001000000300022323||',style:'normal',fontSize: 6},
             ],
             alignment: 'left'
              }, 
            ],
          ],
        },
        layout: 'noBorders',
        margin: [0, 10, 0, 0]
      },
      {text:'ESTE DOCUMENTO ES UNA REPRESENTACION IMPRESA DE UN CFDI',alignment: 'center',color:'blue'}
    ],
    styles: {
        normal: {
          fontSize: 8,
          color: 'black'
        },
        tableHeader: {
          bold: true,
          fontSize: 10,
          color: 'blue'
        }
      }
    };
    pdfMake.createPdf(documentDefinition).open();
   }
}
