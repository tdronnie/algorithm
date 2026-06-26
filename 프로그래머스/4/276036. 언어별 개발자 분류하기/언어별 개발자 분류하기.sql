select (
 case
    when skill_code & (
            select sum(code)
            from skillcodes
            where category = "Front End"
        ) > 0
        and
        skill_code & (
            select code
            from skillcodes
            where name = "Python"
        ) > 0
    then 'A'
    when skill_code & (
            select code
            from skillcodes
            where name = "C#"
        ) > 0
    then 'B'
    when skill_code & (
            select sum(code)
            from skillcodes
            where category = "Front End"
        ) > 0
    then 'C'
    end
)
as grade, id, email
from developers
having grade is not null # mysql은 having에서 별칭 참조 허용, 실행순서가 select 이전이긴하지만
order by grade, id