----------------
---ROLE
----------------
insert into role(id_role,name) values(1,'admin');
insert into role(id_role,name) values(2,'promotor');

----------------
---USER
----------------
insert into user(id_user,email,pw,id_role,name)	values(1,'asd@asd.com','123',1,'User1');
insert into user(id_user,email,pw,id_role,name)	values(2,'aaa@asd.com','123',2,'User2');

----------------
---PROMOTOR
----------------
insert into promotor(id_promotor,name,id_user)	values(1,'pepsi',1);
insert into promotor(id_promotor,name,id_user)	values(2,'coca',1);
insert into promotor(id_promotor,name,id_user)	values(3,'sabritas',1);	

----------------
---CLIENT
----------------
insert into client(id_client,name,rfc,razon_social,email,calle,colonia,estado,coo,no_interior,no_exterior,municipio,pais,codigo_postal)	values(1,'paco','CUPU800825569','Arcos Dorados C.A.','juan@email.com'
	,'calle','colonia','estado','coo','100','n/a','municipio','colombia','01030');
insert into client(id_client,name,rfc,razon_social,email,calle,colonia,estado,coo,no_interior,no_exterior,municipio,pais,codigo_postal)	values(2,'mercedes','MERC800823456','Marta c.v.','mercedes@email.com'
	,'calle1','colonia1','estado1','coo1','200','n/a','municipio1','mexico','41030');
insert into client(id_client,name,rfc,razon_social,email,calle,colonia,estado,coo,no_interior,no_exterior,municipio,pais,codigo_postal)	values(3,'lulu','LULU807654321','Refresco lulu','lulu@email.com'
	,'calle2','colonia2','estado2','coo2','400','n/a','municipio2','peru','01930');
insert into client(id_client,name,rfc,razon_social,email,calle,colonia,estado,coo,no_interior,no_exterior,municipio,pais,codigo_postal)	values(4,'pepe','PELO800811111','Pepes asociados.','pepe@email.com'
	,'calle3','colonia3','estado3','coo3','500','n/a','municipio3','chile','08630');


insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(1,'Productos',null);
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(2,'Servicios',null);
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(3,'Alimentos,bebidas y tabaco',1);
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(4,'Electronicos',1);
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(5,'Limpieza',1);	
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(6,'Gestion',2);	
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(7,'Clubes generales',2);	
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(8,'Edificacion',2);	
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(9,'Clubes',7);
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(10,'Clubes Sociales',9);	
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(11,'Alimentos',3);	
insert into cat_producto_servicio(id_producto_servicio,value,id_parent)	values(12,'Cafe y te',11);	


commit;	