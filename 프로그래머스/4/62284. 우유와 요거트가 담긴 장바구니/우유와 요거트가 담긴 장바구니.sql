select cart_id
from cart_products
where name = 'Yogurt'
intersect
select cart_id
from cart_products
where name = 'Milk'
order by cart_id