with year_max_size as (
    select year(differentiation_date) as year, max(size_of_colony) as max_ecoli
    from ecoli_data
    group by year(differentiation_date)
    
)

select ys.year, (ys.max_ecoli - ed.size_of_colony) as year_dev, ed.id
from year_max_size ys join ecoli_data ed on year(ed.differentiation_date) = ys.year
order by year, year_dev