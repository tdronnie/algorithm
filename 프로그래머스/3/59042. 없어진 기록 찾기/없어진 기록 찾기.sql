# 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물의 ID와 이름을 ID 순으로 조회
select o.animal_id, o.name
from animal_ins as i right join animal_outs as o
on i.animal_id = o.animal_id
where i.animal_id is null;