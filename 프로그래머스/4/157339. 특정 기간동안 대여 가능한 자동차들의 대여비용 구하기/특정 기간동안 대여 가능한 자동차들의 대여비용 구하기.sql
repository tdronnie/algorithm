-- 조건 : 자동차 종류가 '세단' 또는 'SUV' 인 자동차 중 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차에 대해서
-- 결과 : 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 리스트를 출력


select distinct c.car_id, c.car_type, round(c.daily_fee * (1-p.discount_rate/100) * 30) as fee
from car_rental_company_car as c join  car_rental_company_discount_plan as p on c.car_type = p.car_type
where c.car_type in ('세단','SUV') and p.duration_type='30일 이상' and round(c.daily_fee * (1-p.discount_rate/100) * 30) between 500000 and 2000000
and c.car_id not in (select car_id
                     from car_rental_company_rental_history
                     where start_date < '2022-12-01'and end_date >= '2022-11-01') 
order by fee desc, c.car_type, c.car_id desc;


#왜 안될까?
# select distinct c.car_id, c.car_type, round(c.daily_fee * (1-p.discount_rate/100) * 30) as fee, h.start_date, h.end_date
# from car_rental_company_car as c join  car_rental_company_discount_plan as p on c.car_type = p.car_type
# join car_rental_company_rental_history as h on c.car_id = h.car_id
# where c.car_type in ('세단','SUV') and p.duration_type='30일 이상' and round(c.daily_fee * (1-p.discount_rate/100) * 30) between 500000 and 1999999 and h.car_id not in (h.start_date < '2022-12-01'and h.end_date >= '2022-11-01')
# order by fee desc, c.car_type, c.car_id desc;

#날짜필터에 걸리는  car_id만 뽑으려면 기록테이블에서 따로 해당조건에 해당하는 car_id인 것만을 서브쿼리로 뽑기....주목하려는 컬럼과 조건이 걸린 컬럼이 다르면 서브쿼리 이용
# select *
# from car_rental_company_rental_history
# where car_id not in (start_date <= '2022-11-30', end_date >= '2022-11-01'); #같은 컬럼끼리 비교를 해주어야 함.. car_id가 해당 날짜 안에 있는 경우..?불가능! 같은 날짜 컬럼으로 비교해줘서 car_id뽑아야함

#이런 식으로
# select *
# from car_rental_company_rental_history
# where car_id not in (select car_id
#                     from car_rental_company_rental_history
#                     where start_date <= '2022-11-30'and end_date >= '2022-11-01');
                     
