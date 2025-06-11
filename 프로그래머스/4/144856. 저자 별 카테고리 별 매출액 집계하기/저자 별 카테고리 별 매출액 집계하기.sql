# select a.author_id, a.author_name, b.category, (bs.sales * b.price) as total_sales
# from book b join author a on b.author_id = a.author_id
# join book_sales bs on b.book_id = bs.book_id
# where sales_date like "2022-01%"
# group by a.author_id, b.category
# order by a.author_id, b.category desc

# 2022년 1월에 판매된 book_id, author_id, 판매량
# with book_total_sales as (
#     select b.book_id, b.author_id, (sum(bs.sales) * b.price) as total_sales
#     from book b join book_sales bs on b.book_id = bs.book_id
#     where bs.sales_date like "2022-01%"
#     group by b.book_id

# )

# select a.author_id, a.author_name, b.category, (sum(bs.sales) * b.price) as total_sales
# from book b join book_sales bs on b.book_id = bs.book_id
# join author a on b.author_id = a.author_id
# where bs.sales_date like "2022-01%"
# group by b.book_id
# order by a.author_id, b.category desc

select a.author_id, a.author_name, b. category, sum(bs.sales * b.price) as total_sales
from book_sales bs join book b on bs.book_id = b.book_id
join author a on b.author_id = a.author_id
where bs.sales_date like "2022-01%"
group by a.author_id, b.category
order by a.author_id, b.category desc

