select round(avg(length), 2) as AVERAGE_LENGTH
from (select ifnull(length, 10) length
      from fish_info) info;


