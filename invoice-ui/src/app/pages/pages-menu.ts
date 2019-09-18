import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'shopping-cart-outline',
    link: '/pages/dashboard',
    home: true,
  },
  {
    title: 'MODULOS',
    group: true,
  },
  {
    title: 'Promotor',
    icon: 'layout-outline',
    children: [
      {
        title: 'clientes',
        link: '/pages/promotor/clientes'
      },
      {
        title: 'reportes',
        link: '/pages/promotor/reportes'
      },
      {
        title: 'pre-cfdi',
        link: '/pages/promotor/precfdi'
      }

    ]
  },
  {
    title: 'Operaciones',
    icon: 'layout-outline',
    children: [
      {
        title: 'clientes',
        link: '/pages/operaciones/clientes'
      },{
        title: 'empresas',
        link: '/pages/operaciones/empresas'
      },{
        title: 'revision',
        link: '/pages/operaciones/revision'
      },{
        title: 'devoluciones',
        link: '/pages/operaciones/devoluciones'
      },{
        title: 'pagos',
        link: '/pages/operaciones/pagos'
      },{
        title: 'reportes',
        link: '/pages/operaciones/reportes'
      },{
        title: 'carga-xml',
        link: '/pages/operaciones/carga-xml'
      }
    ]
  },
  {
    title: 'Contabilidad',
    icon: 'layout-outline',
    children: [
      {
        title: 'inicio',
        link: '/pages/contabilidad'
      }
    ]
  },
  {
    title: 'Tesoreria',
    icon: 'layout-outline',
    children: [
      {
        title: 'inicio',
        link: '/pages/tesoreria'
      }
    ]
  },
  
];
