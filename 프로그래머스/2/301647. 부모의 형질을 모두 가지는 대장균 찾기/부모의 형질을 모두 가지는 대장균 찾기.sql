select a.id as ID, a.genotype as GENOTYPE, b.genotype as PARENT_GENOTYPE  
from ecoli_data a, ecoli_data b
where a.parent_id = b.id and a.genotype & b.genotype = b.genotype
order by ID;