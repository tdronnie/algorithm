select month(h.start_date) as month, h.car_id, count(*) as records
from car_rental_company_rental_history h
where h.start_date between '2022-08-01' and '2022-10-31'
and h.car_id in (
    select car_id
    from car_rental_company_rental_history
    where start_date between '2022-08-01' and '2022-10-31'
    group by car_id
    having count(*) >= 5
)
group by month(h.start_date), h.car_id
having count(*) <> 0
order by month(h.start_date), h.car_id desc