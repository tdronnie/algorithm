select id,
case
    when ntile(4) over (order by size_of_colony) = 1 then 'LOW'
    when ntile(4) over (order by size_of_colony) = 2 then 'MEDIUM'
    when ntile(4) over (order by size_of_colony) = 3 then 'HIGH'
    else 'CRITICAL'
end as 'COLONY_NAME'
from ecoli_Data
order by id
