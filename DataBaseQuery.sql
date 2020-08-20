CREATE DATABASE Guatzak
go
use Guatzak

create table Usuario(
	Id_user int IDENTITY(1,1),
	Nombre_user varchar(50),
	Contraseña_user varchar(100),
	Salt_user varchar(30),
	constraint pk_us primary key (Id_user)
)

create table Tipo_sala(
	Id_tipo int,
	Nombre_sala varchar(50),
	constraint pk_ts primary key (Id_tipo)
)

create table Sala(
	Id_sala int IDENTITY(1,1),
	Id_tipo int,
	Nombre_sala varchar(100),
	constraint pk_sal primary key (Id_sala),
	constraint fk_ti foreign key (Id_tipo) references Tipo_sala(Id_tipo)
)

create table Mensaje(
	Id_men int IDENTITY(1,1),
	Id_user int,
	Id_sala int,
	Mensaje varchar(300),
	Fecha datetime,
	constraint pk_me primary key (Id_men),
	constraint fk_use foreign key (Id_user) references Usuario(Id_user),
	constraint fk_sal foreign key (Id_sala) references Sala(Id_sala)
)

create table Sala_usuario(
	Id_su int IDENTITY(1,1),
	Id_sala int,
	Id_usuario int,
	constraint pk_su primary key (Id_su),
	constraint fk_sa foreign key (Id_sala) references Sala(Id_sala),
	constraint fk_us foreign key (Id_usuario) references Usuario(Id_user)
)



create procedure get_salas
@id int
as
	begin tran
	select *
	from Sala S
	join Sala_usuario U
	on S.Id_sala = U.Id_sala
	where U.Id_usuario = @id;
	If @@Error<>0 
	BEGIN
	PRINT 'Ha ecorrido un error'
	--Se lo comunicamos al usuario y deshacemos la transacción
	--todo volverá a estar como si nada hubiera ocurrido
	ROLLBACK TRAN
	END
	commit tran
go

exec get_salas 4431587

alter procedure get_mensajes
@sala int
as
	begin tran
	select U.Nombre_user, M.Fecha, M.Mensaje
	from Mensaje M
	join Usuario U
	on U.Id_user = M.Id_user
	where M.Id_sala = @sala
	order by M.Fecha
	If @@Error<>0 
	BEGIN
	PRINT 'Ha ecorrido un error'
	--Se lo comunicamos al usuario y deshacemos la transacción
	--todo volverá a estar como si nada hubiera ocurrido
	ROLLBACK TRAN
	END
	commit tran
go

exec get_mensajes 19


insert into Usuario values ('William','$2a$10$ouWF2IVBpYBfzYwWyFgNW.hroNrKPoXZxFPmF9x0RG6riA0fW3WwK','$2a$10$ouWF2IVBpYBfzYwWyFgNW.')

insert into Tipo_sala values (1 , 'Chat')
insert into Tipo_sala values (2 , 'Grupo')

insert into Sala values (1,'Testeo 2')

insert into Sala_usuario values (1,1)

select * from Usuario
select * from Sala

delete from Mensaje

insert into Mensaje values (1,19,'Hola',GETDATE())
SELECT * FROM Mensaje