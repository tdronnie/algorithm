select count(*) as fish_count, fish_name
from fish_name_info fni join fish_info fi on fni.fish_type = fi.fish_type
group by fni.fish_name
order by fish_count desc