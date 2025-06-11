with max_views as (
    select *
    from used_goods_board
    order by views desc
    limit 1
)

select concat('/home/grep/src/', f.board_id, "/", f.file_id, f.file_name, f.file_ext) as file_path
from max_views v join used_goods_file f on v.board_id = f.board_id
order by f.file_id desc

# select *
#     from used_goods_board
#     order by views desc
#     limit 1


# select *
#     from used_goods_file
#     where board_id = "B0008"

