select count(*) as "fish_count"
from fish_info i join fish_name_info ni on i.fish_type = ni.fish_type
where ni.fish_name = "BASS" or ni.fish_name = "SNAPPER"