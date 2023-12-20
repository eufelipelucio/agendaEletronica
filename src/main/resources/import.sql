insert into tb_contatos(id,nome,email,fone)values(1,'Maria','maria@gmail.com','48995587822');
insert into tb_contatos(id,nome,email,fone)values(2,'Felipe','felipe@gmail.com','48996787214');

insert into tb_local(id,nome,rua,num,bairro,cidade,uf,cep)values(1, 'Stage','Rua Nova Olinda',21,'Liberdade','São Luís','MA','65000-000');
insert into tb_local(id,nome,rua,num,bairro,cidade,uf,cep)values(2, 'Spot','Servidão',35,'Tapera','Floripa','SC','88000-000');

insert into tb_compromissos(id,descricao,data,hora,contato_id,local_id)values(1,'Jogar bocha','2023-11-11','13:00:00',2,1);
insert into tb_compromissos(id,descricao,data,hora,contato_id,local_id)values(2,'Jogar volei','2023-11-11','13:00:00',2,1);
