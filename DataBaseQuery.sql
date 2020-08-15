CREATE DATABASE Guatzak
go
use Guatzak

create table Usuario(
	Id_user int,
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
	Id_sala int,
	Id_tipo int,
	Nombre_sala varchar(100),
	constraint pk_sal primary key (Id_sala),
	constraint fk_ti foreign key (Id_tipo) references Tipo_sala(Id_tipo)
)

create table Mensaje(
	Id_men int,
	Id_user int,
	Id_sala int,
	Mensaje varchar(300),
	Fecha datetime,
	constraint pk_me primary key (Id_men),
	constraint fk_use foreign key (Id_user) references Usuario(Id_user),
	constraint fk_sal foreign key (Id_sala) references Sala(Id_sala)
)

create table Sala_usuario(
	Id_su int,
	Id_sala int,
	Id_usuario int,
	constraint pk_su primary key (Id_su),
	constraint fk_sa foreign key (Id_sala) references Sala(Id_sala),
	constraint fk_us foreign key (Id_usuario) references Usuario(Id_user)
)

insert into Usuario values (1,'William','$2a$10$ouWF2IVBpYBfzYwWyFgNW.hroNrKPoXZxFPmF9x0RG6riA0fW3WwK','$2a$10$ouWF2IVBpYBfzYwWyFgNW.')

select *
from Sala S
join Sala_usuario U
on S.Id_sala = U.Id_sala
where U.Id_usuario = 'Algo';

alter procedure get_salas
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