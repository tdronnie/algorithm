with apnt as (
    select *
    from appointment
    where apnt_ymd like "2022-04-13%" and apnt_cncl_yn = "N" and mcdp_cd = "CS"
    order by apnt_ymd
)

select a.apnt_no, p.pt_name, p.pt_no, a.mcdp_cd, d.dr_name, a.apnt_ymd
from doctor d join apnt a on d.dr_id = a.mddr_id
    join patient p on a.pt_no = p.pt_no
order by a.apnt_ymd
