SELECT distinct crcc.car_id
from car_rental_company_car crcc, car_rental_company_rental_history crcrh
where crcc.car_id = crcrh.car_id
and crcc.car_type = "세단" and MONTH(crcrh.start_date) = 10
order by crcc.car_id desc;