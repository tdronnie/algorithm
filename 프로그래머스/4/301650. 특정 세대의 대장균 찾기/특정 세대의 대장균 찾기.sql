with recursive generation as (
    select id, parent_id, 1 as depth
    from ecoli_data
    where parent_id is null
    
    union all
    
    select ed.id, ed.parent_id, depth+1
    from ecoli_data ed join generation g on ed.parent_id = g.id
    where depth <= 3
    
)

select id
from generation
where depth = 3
order by id

