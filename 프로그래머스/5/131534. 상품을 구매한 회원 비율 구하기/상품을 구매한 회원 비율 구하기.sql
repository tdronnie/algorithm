# 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수와
#상품을 구매한 회원의 비율(=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)을 년, 월 별로 출력
# 상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림
# 년을 기준으로 오름차순 정렬해주시고 년이 같다면 월을 기준으로 오름차순 정렬

# 회원이 산 상품이 복수개 일 수 있기 때문에 distinct 처리
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
