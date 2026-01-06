with q as (
    select concat((
        case
            when month(differentiation_date) between 1 and 3 then 1
            when month(differentiation_date) between 4 and 6 then 2
            when month(differentiation_date) between 7 and 9 then 3
            else 4
        end
        ), "Q") as quarter, id
    from ecoli_data
)

select quarter, count(id) as ecoli_count
from q
group by quarter
order by quarter