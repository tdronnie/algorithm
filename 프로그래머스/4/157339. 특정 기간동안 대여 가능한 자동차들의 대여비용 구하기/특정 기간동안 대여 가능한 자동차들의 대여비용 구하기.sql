-- 조건 : 자동차 종류가 '세단' 또는 'SUV' 인 자동차 중 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차에 대해서
-- 결과 : 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 리스트를 출력

-- N : 1

-- 총 대여기간 동안의 대여금액 출력!
# select distinct c.car_id, c.car_type, round(c.daily_fee * (1-p.discount_rate/100) * 30) as fee
# from car_rental_company_rental_history as h join car_rental_company_car as c on h.car_id = c.car_id
# join car_rental_company_discount_plan p on c.car_type = p.car_type
# where c.car_type in ('세단','SUV') and not h.end_date between '2022-11-01' and '2022-11-30'
# and c.car_id in (
#     select c.car_id
#     from car_rental_company_car as c join car_rental_company_discount_plan as p
#     on c.car_type = p.car_type
#     where substring(p.duration_type, 1, 2) = 30 and round(c.daily_fee * (1-p.discount_rate/100) * 30) between 500000 and 1999999
#     )
# order by fee desc, c.car_type, c.car_id desc;

# select distinct *
# from car_rental_company_rental_history as h join car_rental_company_car as c on h.car_id = c.car_id
# join car_rental_company_discount_plan as p on c.car_type = p.car_type
# where c.car_type in ('세단','SUV') and not h.end_date between '2022-11-01' and '2022-11-30' and substring(p.duration_type, 1, 2) = 30;



select distinct c.car_id, c.car_type, round(c.daily_fee * (1-p.discount_rate/100) * 30) as fee
from car_rental_company_car as c join  car_rental_company_discount_plan as p on c.car_type = p.car_type
where c.car_type in ('세단','SUV') and p.duration_type='30일 이상' and round(c.daily_fee * (1-p.discount_rate/100) * 30) between 500000 and 2000000
and c.car_id not in (select car_id from car_rental_company_rental_history where start_date < '2022-12-01'and end_date >= '2022-11-01') 
order by fee desc, c.car_type, c.car_id desc;
