use musicFour;

create table CATEGORY (
	CatID char(10) not null,
    CatInfo char(100) not null,
    CatName char(20) not null,
    primary key (CatID)
);

create table MUSICRANK(
	MRID char(10) not null,
    MRName char(20) not null,
    primary key (MRID)
);

create table MUSICIAN(
	MsnID char(10) not null,
    MsnName char(20) not null,
    MsnInfo char(100) not null,
    primary key (MuID)
);

create table MUSIC(
	MscID char(10) not null,
    MscContent blob not null,
    MscCover char(20),
    MscPrice double not null,
    MsnID char(10) not null,
    primary key (MscID),
    foreign key (MsnID) references MUSICIAN (MsnID)
    );
    
create table MV(
	MVID char(10) not null,
    MscID char(10) not null,
    primary key (MVID, MscID),
    foreign key (MscID) references MUSIC (MscID)
);

create table USERS(
	UID char(10) not null,
    UName char(20) not null,
    UPassword char(20) not null,
    UBirth date,
    UIntro char(100),
    UGender char(20),
    UVIPDuration datetime, 
    UVIPType char(20),
    primary key (UID)
);

create table TRANSACTIONS(
	TID char(10) not null,
    TQuantity int not null,
    UID char(10) not null,
    primary key (TID),
    foreign key (UID) references USERS (UID)
);

create table COMMENTS(
	ComID char(10) not null,
    ComDate date not null,
    ComContent char(100) not null,
    ReplyTo char(10),
    MscID char(10) not null,
    UID char(10) not null,
    primary key (ComID),
    foreign key (ReplyTo) references COMMENTS (ComID),
    foreign key (MscID) references MUSIC (MscID),
    foreign key (UID) references USERS (UID)
);

create table MUSICSHEET(
	MSID char(10) not null,
    MSName char(20) not null,
    MSInfo char(100) not null,
    UID char(10) not null,
    primary key (MSID),
    foreign key (UID) references USERS (UID)
);

create table PUBLISHER(
	PID char(10) not null,
    PName char(20) not null,
    PInfo char(100) not null,
    PStartDate date not null,
    primary key (PID)
);

create table ALBUM(
	AID char(10) not null,
    AName char(20) not null,
    AInfo char(100) not null,
    APrice double not null,
    APublishDate date not null,
    MsnID char(10) not null,
    PID char(10) not null,
    primary key (AID),
    foreign key (MsnID) references MUSICIAN (MsnID),
    foreign key (PID) references PUBLISHER (PID)
);

create table belongs_to(
	MscID char(10) not null,
    CatID char(10) not null,
    primary key (MscID, CatID),
    foreign key (MscID) references MUSIC (MscID),
    foreign key (CatID) references CATEGORY (CatID)
);

create table MR_include(
	MRID char(10) not null,
    MscID char(10) not null,
    Ranks int not null,
    primary key (MRID, MscID),
    foreign key (MRID) references MUSICRANK (MRID),
    foreign key (MscID) references MUSIC (MscID)
);

create table MS_include(
	MscID char(10) not null,
    MSID char(10) not null,
    primary key (MscID, MSID),
    foreign key (MscID) references MUSIC (MscID),
    foreign key (MSID) references MUSICSHEET (MSID)
);

create table M_sold(
	MscID char(10) not null,
    TID char(10) not null,
    primary key (MscID, TID),
    foreign key (MscID) references MUSIC (MscID),
    foreign key (TID) references TRANSACTIONS (TID));

create table M_like(
	MscID char(10) not null,
    UID char(10) not null,
    primary key (MscID, UID),
    foreign key (MscID) references MUSIC (MscID),
    foreign key (UID) references USERS (UID));

create table A_sold(
	TID char(10) not null,
    AID char(10) not null,
    primary key (TID, UID),
    foreign key (TID) references TRANSACTIONS (TID),
    foreign key (AID) references ALBUM (AID));

create table MS_like(
	MSID char(10) not null,
    UID char(10) not null,
    primary key (MSID, UID),
    foreign key (MSID) references MUSICSHEET (MSID),
    foreign key (UID) references USERS (UID));

create table A_like(
	UID char(10) not null,
    AID char(10) not null,
    primary key (AID, UID),
    foreign key (UID) references USERS (UID),
    foreign key (AID) references ALBUM (AID));

