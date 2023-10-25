# 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수와
#상품을 구매한 회원의 비율(=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)을 년, 월 별로 출력
# 상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림
# 년을 기준으로 오름차순 정렬해주시고 년이 같다면 월을 기준으로 오름차순 정렬

# select year(o.sales_date) as year, month(o.sales_date) as month,
# count(u.joined like '2021%' and o.user_id is not null),
# count(u.user_id) as purchased_users
# from user_info as u left join online_sale as o
# on u.user_id = o.user_id
# where u.joined like '2021%' and o.user_id is not null
# group by year(o.sales_date), month(o.sales_date)
# having round(count(u.joined like '2021%' and o.user_id is not null)/count(u.joined like '2021%'), 1)
# order by year, month;

#2021년에 가입한 전체 회원들 중 상품을 구매한 회원수
# select o.count(*)
# from user_info as u left join online_sale as o
# on u.user_id = o.user_id
# where u.joined like '2021%' and o.user_id is not null

select year(sales_date), month(sales_date), count(distinct o.user_id) as purchased_users,
round(count(distinct o.user_id)/
    (select count(user_id)
    from user_info
    where joined like '2021%'), 1) as purchased_ratio
#애초에 21년 가입자들만 조인시켜주기
from online_sale as o join
(
    select user_id
    from user_info
    where joined like '2021%') as u21
    on o.user_id = u21.user_id

group by year(sales_date), month(sales_date)
order by year(sales_date), month(sales_date);