export const MENU_ITEMS = [
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
            },
            {
                title: 'pagos',
                link: '/pages/promotor/pagos'
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
            }, {
                title: 'empresas',
                link: '/pages/operaciones/empresas'
            }, {
                title: 'revision',
                link: '/pages/operaciones/revision'
            }, {
                title: 'devoluciones',
                link: '/pages/operaciones/devoluciones'
            }, {
                title: 'pagos',
                link: '/pages/operaciones/pagos'
            }, {
                title: 'reportes',
                link: '/pages/operaciones/reportes'
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
//# sourceMappingURL=pages-menu.js.map