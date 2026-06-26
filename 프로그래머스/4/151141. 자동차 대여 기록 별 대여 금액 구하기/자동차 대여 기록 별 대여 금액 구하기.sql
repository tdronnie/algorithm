# car_type이 트럭인 car_id에 대한 대여기간, 일일 요금 구하기, 대여기간을 적용할인플랜 최소 날짜로 변경
with days as (
    select c.car_id, h.history_id, c.daily_fee, datediff(h.end_date, h.start_date)+1 as dur,
        (case
            when datediff(h.end_date, h.start_date)+1 >= 90 then "90일 이상"
            when datediff(h.end_date, h.start_date)+1 >= 30 then "30일 이상"
            when datediff(h.end_date, h.start_date)+1 >= 7 then "7일 이상"
            else null
        end
         ) as fix_dur
    from CAR_RENTAL_COMPANY_CAR c join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
    where c.car_type = "트럭"
)

select d.history_id, floor((daily_fee * (1 - coalesce(dis.discount_rate, 0)/100) * d.dur)) as fee
from days d left join CAR_RENTAL_COMPANY_DISCOUNT_PLAN dis
    on d.fix_dur = dis.duration_type and dis.car_type = "트럭"
order by fee desc, d.history_id desc
