-- 코드를 입력하세요
SELECT hour(datetime) hour, count(date_format(datetime, '%h')) count
from animal_outs
group by hour
having hour >= 9 and hour < 20
order by hour;