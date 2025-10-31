select info.id, result.fish_name, result.length
from fish_info info join
(
    select fni.fish_name, fi.fish_type, max(fi.length) as length
    from fish_info fi join fish_name_info fni on fi.fish_type = fni.fish_type
    group by fi.fish_type, fni.fish_name
) result on info.length = result.length and info.fish_type = result.fish_type
order by info.id
