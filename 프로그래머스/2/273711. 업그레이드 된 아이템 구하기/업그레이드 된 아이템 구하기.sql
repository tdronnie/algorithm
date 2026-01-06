with origin as (
    select it.item_id
    from item_info ii join item_tree it on ii.item_id = it.parent_item_id
    where ii.rarity = "RARE"
)

select o.item_id, i.item_name, i.rarity
from origin o join item_info i on o.item_id = i.item_id
order by o.item_id desc
