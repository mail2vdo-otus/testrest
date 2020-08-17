
     create table if not exists   client(clientid serial primary key, firstName varchar(100),surName varchar(100), birthDate Date, passport varchar(11), address varchar(100));
     create table if not exists deal(dealid serial primary key, code varchar(10), openDate Date,endDate Date, amount numeric(10,2), rate float, fundISO char(3),clientId int,  FOREIGN KEY (clientId) REFERENCES client (clientid));

     CREATE SEQUENCE if not exists reqseq START 1000;
     create table if not exists request(id serial,typecode char(10), message varchar(100),code varchar(10) ,opendate Date  default CURRENT_DATE, duedate Date,status varchar(10) default 'введено', dealid int, FOREIGN KEY (dealid) REFERENCES deal (dealid)) ;

create or replace function request_trigger ()
returns trigger language plpgsql as $$
begin
    new.code:= cast(nextval('reqseq') as varchar);
    new.opendate:=  CURRENT_DATE;
    return new;
end $$;

create trigger request_trigger
before insert or update on request
for each row execute procedure request_trigger();

          create table if not exists restuser(id serial, firstName varchar(100), surName varchar(100), birthDate Date) ;

