create database csc_4001;
use csc_4001;

create table registry(
	RID char(30) not null,
    RPassword char(30) not null,
    primary key (RID)
);

create table users(
	UID char(15) not null,
    UName char(30),
    UBirth date,
    UIntro varchar(500),
    UGender char(20),
    RID char(30) not null,
    primary key (UID),
	foreign key (RID) references registry (RID)
);

create table musician(
	MsnID char(15) not null,
    MsnName char(30),
    MsnInfo varchar(500),
    primary key (MsnID)
);

create table music(
	MscID char(15) not null,
    MscName char(30) not null,
    MscCover varchar(100),
    MscLyrics char(20),
    MscDownload varchar(100),
    MscPlayTime int,
    MscPublishDate date,
    MsnID char(15) not null,
    primary key (MscID),
    foreign key (MsnID) references musician (MsnID)
    );

create table comments(
	ComID char(15) not null,
    ComDate date,
    ComContent varchar(500),
    ReplyTo char(15),
    MscID char(15) not null,
    UID char(15) not null,
    primary key (ComID),
    foreign key (ReplyTo) references comments (ComID),
    foreign key (MscID) references music (MscID),
    foreign key (UID) references users (UID)
);

create table musicsheet(
	MSID char(15) not null,
    MSName char(30),
    MSInfo varchar(500),
    UID char(15) not null,
    primary key (MSID),
    foreign key (UID) references users (UID)
);

create table publisher(
	PID char(15) not null,
    PName char(20),
    PInfo varchar(500),
    PStartDate date,
    primary key (PID)
);

create table album(
	AID char(15) not null,
    AName char(20),
    AInfo varchar(500),
    APublishDate date,
    MsnID char(15) not null,
    PID char(15) not null,
    primary key (AID),
    foreign key (MsnID) references musician(MsnID),
    foreign key (PID) references publisher(PID)
);

create table MS_include(
	MscID char(15) not null,
    MSID char(15) not null,
    primary key (MscID, MSID),
    foreign key (MscID) references music (MscID),
    foreign key (MSID) references musicsheet (MSID)
);

create table M_like(
	MscID char(15) not null,
    UID char(15) not null,
    primary key (MscID, UID),
    foreign key (MscID) references music (MscID),
    foreign key (UID) references users (UID));

create table MS_like(
	MSID char(15) not null,
    UID char(15) not null,
    primary key (MSID, UID),
    foreign key (MSID) references musicsheet (MSID),
    foreign key (UID) references users (UID));

create table A_like(
	UID char(15) not null,
    AID char(15) not null,
    primary key (AID, UID),
    foreign key (UID) references users (UID),
    foreign key (AID) references album (AID));

