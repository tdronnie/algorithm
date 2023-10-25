# 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회
# 보호 시작일이 빠른 순으로 조회
select i.animal_id, i.name
from animal_ins as i join animal_outs as o
on i.animal_id = o.animal_id
where i.datetime > o.datetime
order by i.datetime;