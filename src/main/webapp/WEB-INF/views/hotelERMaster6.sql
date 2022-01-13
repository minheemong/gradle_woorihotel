



select *from hotel;
select *from qna;
	

/* Drop Triggers */

DROP TRIGGER TRI_bookdetail_bdseq;
DROP TRIGGER TRI_book_booknum;
DROP TRIGGER TRI_qna_qnaseq;



/* Drop Tables */

DROP TABLE bookdetail CASCADE CONSTRAINTS;
DROP TABLE book CASCADE CONSTRAINTS;
DROP TABLE hotel CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE hotelmember CASCADE CONSTRAINTS;




/* Drop Sequences */

DROP SEQUENCE SEQ_bookdetail_bdseq;
DROP SEQUENCE SEQ_book_booknum;
DROP SEQUENCE SEQ_qna_qnaseq;




/* Create Sequences */

CREATE SEQUENCE SEQ_bookdetail_bdseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_book_booknum INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_qna_qnaseq INCREMENT BY 1 START WITH 1;



drop table hotelimg;



CREATE TABLE book
(
	booknum number(5) NOT NULL,
	id varchar2(20) NOT NULL,
	PRIMARY KEY (booknum)
);


CREATE TABLE bookdetail
(
	usernum number(3) NOT NULL,
	checkin date,
	checkout date,
	bdseq number(5) NOT NULL,
	result char(1) DEFAULT '0' NOT NULL,
	booknum number(5) NOT NULL,
	indate date DEFAULT sysdate,
	hotelnum number(5) NOT NULL,
	PRIMARY KEY (bdseq)
);


CREATE TABLE hotel
(
	hotelnum number(5) NOT NULL,
	persons number(3) NOT NULL,
	price number(8) NOT NULL,
	img varchar2(50) NOT NULL,
	roomsize number(4),
	kind varchar2(30) NOT NULL,
	PRIMARY KEY (hotelnum)
);


CREATE TABLE hotelmember
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	email varchar2(40) NOT NULL,
	zip_num varchar2(20) NOT NULL,
	address varchar2(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE qna
(
	title varchar2(300) NOT NULL,
	content varchar2(1000) NOT NULL,
	indate date DEFAULT sysdate,
	id varchar2(20) NOT NULL,
	reply varchar2(1000),
	rep char(1) DEFAULT '1',
	qnaseq number(5) NOT NULL,
	PRIMARY KEY (qnaseq)
);






/* Create Foreign Keys */

ALTER TABLE bookdetail
	ADD FOREIGN KEY (booknum)
	REFERENCES book (booknum)
;


ALTER TABLE bookdetail
	ADD FOREIGN KEY (hotelnum)
	REFERENCES hotel (hotelnum)
;


ALTER TABLE book
	ADD FOREIGN KEY (id)
	REFERENCES hotelmember (id)
;


ALTER TABLE qna
	ADD FOREIGN KEY (id)
	REFERENCES hotelmember (id)
;

create or replace view book_view
as
select b.booknum, d.bdseq, m.id, m.name, h.kind, d.usernum, d.checkin, d.checkout, d.result, h.price, h.hotelnum
from book b , bookdetail d, hotelmember m, hotel h
where b.booknum = d.booknum and b.id=m.id and h.hotelnum=d.hotelnum;

insert into hotelmember values('scott','1234','홍길동','010-1111-2222','abc@abc.com','130-110','서울시 성동구 성수동1가 1번지21호');
insert into hotelmember values('one','1234','홍길서','010-1323-2652','a53@abc.com','130-120','서울시 강남구 역삼2동 113/104 현대앞');
insert into hotelmember (id, pwd,name,phone,email,zip_num,address)
values('oh','1234','오용언','010-1111-2222','ab@abc.com','130-120','서울시 송파구 잠실2동 리센츠 아파트 201동 505호');
insert into hotelmember (id, pwd,name,phone,email,zip_num,address)
values('yong','1234','용','010-1111-2222','ab@abc.com','130-120','서울시 송파구 잠실2동 리센츠 아파트 201동 505호');
insert into hotelmember (id, pwd,name,phone,email,zip_num,address)
values('un','1234','언','010-1111-2222','ab@abc.com','130-120','서울시 송파구 잠실2동 리센츠 아파트 201동 505호');

insert into book values(seq_book_booknum.nextval,'scott');
insert into book values(seq_book_booknum.nextval,'one');
insert into book values(seq_book_booknum.nextval,'one');

insert into hotel values(1101,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1102,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1103,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1104,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1105,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1106,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1107,2,320000,'Deluxe1.jpg',40,'Deluxe');
insert into hotel values(1201,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1202,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1203,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1204,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1205,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1206,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1207,4,390000,'BusinessDeluxe1.jpg',56,'Business Deluxe');
insert into hotel values(1301,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1302,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1303,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1304,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1305,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1306,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1307,6,460000,'GrandCornerDeluxe1.jpg',72,'Grand Corner Deluxe');
insert into hotel values(1401,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
insert into hotel values(1402,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
insert into hotel values(1403,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
insert into hotel values(1404,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
insert into hotel values(1405,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
insert into hotel values(1406,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
insert into hotel values(1407,4,550000,'ExecuticeBusinessDeluxe1.jpg',64,'Executive Business Deluxe');
 



insert into bookdetail(usernum,checkin,checkout,bdseq,booknum,hotelnum)
values(2,to_date('2021-11-08'),to_date('2021-11-12'),seq_bookdetail_bdseq.nextval,1,1101);
insert into bookdetail(usernum,checkin,checkout,bdseq,booknum,hotelnum)
values(2,to_date('2021-11-08'),to_date('2021-11-12'),seq_bookdetail_bdseq.nextval,1,1102);
insert into bookdetail(usernum,checkin,checkout,bdseq,booknum,hotelnum)
values(2,to_date('2021-11-19'),to_date('2021-11-21'),seq_bookdetail_bdseq.nextval,1,1101);
insert into bookdetail(usernum,checkin,checkout,bdseq,booknum,hotelnum)
values(2,to_date('2021-11-19'),to_date('2021-11-21'),seq_bookdetail_bdseq.nextval,1,1102);
insert into bookdetail(usernum,checkin,checkout,bdseq,booknum,hotelnum)
values(2,to_date('2021-11-05'),to_date('2021-11-07'),seq_bookdetail_bdseq.nextval,2,1201);
insert into bookdetail(usernum,checkin,checkout,bdseq,booknum,hotelnum)
values(2,to_date('2021-11-12'),to_date('2021-11-17'),seq_bookdetail_bdseq.nextval,3,1301);

 insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'oh');
 insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'oh');
 insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'oh');
 insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'oh');
 insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'oh');

insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'yong');
insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'yong');
insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'yong');
insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'yong');
insert into qna (qnaseq, title, content, id) 
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'yong');
 
insert into qna (qnaseq, title, content, id)  
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'un');
insert into qna (qnaseq, title, content, id)  
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'un');
insert into qna (qnaseq, title, content, id)  
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'un');
insert into qna (qnaseq, title, content, id)  
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'un');
insert into qna (qnaseq, title, content, id)  
values(seq_qna_qnaseq.nextval, '배송관리 문의입니다', '현재 배송상태와 예상 배송일을 답변 부탁드립니다', 'un');


drop table hotelimg;

/* --------------------------------새로 추가된것들-------------------------------------------------------------------------------------------------------------------------*/
/* 새로추가한 호텔 이미지 테이블 */

create table hotelimg
(
kind varchar2(100) not null ,
img1 varchar2(100),
img2 varchar2(100),
img3 varchar2(100),
img4 varchar2(100),
PRIMARY KEY (kind)
);
/* 새로추가한 호텔 이미지 테이블의 이미지 이름 */
insert into hotelimg values('Deluxe','Deluxe1','Deluxe2','Deluxe3','Deluxe4');
insert into hotelimg values('BusinessDeluxe','BusinessDeluxe1','BusinessDeluxe2','BusinessDeluxe3','BusinessDeluxe4');
insert into hotelimg values('GrandCornerDeluxe','GrandCornerDeluxe1','GrandCornerDeluxe2','GrandCornerDeluxe3','GrandCornerDeluxe4');
insert into hotelimg values('ExecuticeBusinessDeluxe','ExecuticeBusinessDeluxe1','ExecuticeBusinessDeluxe2','ExecuticeBusinessDeluxe3','ExecuticeBusinessDeluxe4');

/* 환불 */
alter table bookdetail add(refund number(8) default 0 not null);
select * from hotel;
create or replace view book_view
as
select b.booknum, d.bdseq, m.id, m.name, h.kind, d.usernum, d.checkin, d.checkout, d.result, d.refund, h.price, h.hotelnum
from book b , bookdetail d, hotelmember m, hotel h
where b.booknum = d.booknum and b.id=m.id and h.hotelnum=d.hotelnum;

	