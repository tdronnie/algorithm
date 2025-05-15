select f1.category, f1.price as max_price, f1.product_name
from food_product f1
join
    (
    SELECT category, max(price) as max_price
    from food_product
    where category in ("과자", "국", "김치", "식용유")
    group by category
    ) f2 on f1.category = f2.category and f1.price = f2.max_price
order by price desc

