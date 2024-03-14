-- 코드를 입력하세요
SELECT ingredient_type, sum(total_order)
from icecream_info i join first_half h on i.flavor=h.flavor
group by ingredient_type
order by total_order asc;