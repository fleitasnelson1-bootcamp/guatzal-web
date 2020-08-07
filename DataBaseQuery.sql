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
	Fecha datetime,
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