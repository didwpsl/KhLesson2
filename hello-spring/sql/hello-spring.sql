--=========================
--������ ���� - spring ���� ���� 
--=========================
alter session set "_oracle_script" = true; 

create user spring 
identified by spring 
default tablespace users;

alter user spring quota unlimited on users;

grant connect, resource to spring;

--=============================
--spring ����
--=============================
--dev ���̺�
create table dev (
    no number,
    name varchar2(50) not null,
    career number not null,
    email varchar2(200) not null,
    gender char(1),
    lang varchar2(100) not null, --vo String[] <--> varchar2 java,c,js
    constraint pk_dev_no primary key(no),
    constraint ck_dev_gender check(gender in ('M', 'F'))
);

create sequence seq_dev_no;
	select * from dev order by desc
commit;

--ȸ�� ���̺� ���� 
--member_role ���� ������ ������ ���̺��� ���� (spring-security)
create table member (
    id varchar2(15),
    password varchar2(300) not null,
    name varchar2(256) not null,
    gender char(1),
    birthday date,
    email varchar2(256),
    phone char(11) not null,
    address varchar2(512),
    hobby varchar2(256),
    enroll_date date default sysdate,
    enabled number default 1, --ȸ�� Ȱ��ȭ ���� 1:Ȱ��ȭ, 0: ��Ȱ��ȭ (spring-security)
    constraint pk_member_id primary key(id),
    constraint ck_member_gender check(gender in('M','F')),
    constraint ck_member_enabled check(enabled in(1,0))
);

	insert into spring.member values ('abcde','1234','�ƹ���','M',to_date('88-01-25','rr-mm-dd'),'abcde@naver.com','01012345678','����� ������','�,���,����',default,default);
	insert into spring.member values ('qwerty','1234','�踻��','F',to_date('78-02-25','rr-mm-dd'),'qwerty@naver.com','01098765432','����� ���Ǳ�','�,���',default,default);
	insert into spring.member values ('admin','1234','������','F',to_date('90-12-25','rr-mm-dd'),'admin@naver.com','01012345678','����� ������','����',default,default);
	commit;
    
select * from member;

