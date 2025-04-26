SELECT category, sum(sales) as total_sales
from book b, book_sales bs
where b.book_id = bs.book_id and bs.sales_date like "2022-01%"
group by b.category
order by category;