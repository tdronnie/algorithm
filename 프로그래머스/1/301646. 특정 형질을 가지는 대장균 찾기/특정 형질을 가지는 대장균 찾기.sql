select count(*) as COUNT
from (select parent_id, conv(genotype, 10, 2) as genotype
     from ecoli_data) g
where g.genotype like "%_01" or g.genotype like "%10_" or g.genotype like "1";