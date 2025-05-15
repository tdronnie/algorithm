SELECT r1.food_type, r1.rest_id, r1.rest_name, r1.favorites
from rest_info r1
join (
    select food_type, max(favorites) as max_fav
    from rest_info
    group by food_type
) r2 on r1.food_type = r2.food_type and r1.favorites = r2.max_fav
order by r1.food_type desc