select ii.item_id, ii.item_name, ii.rarity
from item_info ii join item_tree it on ii.item_id = it.item_id
where ii.item_id  not in (select parent_item_id from item_tree where parent_item_id is not null)
order by ii.item_id desc