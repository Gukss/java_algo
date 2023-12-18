-- 코드를 입력하세요
select pt_name, pt_no, gend_cd, age, 
case 
when
    tlno is null
then 
    "NONE"
else
    tlno
end
from patient
where age <= 12 and gend_cd = 'W'
order by age desc, pt_name asc

# SELECT pt_name, pt_no, gend_cd, age, case 
# when tlno is null 
# then 'NONE' 
# else tlno
# end tlno
# from patient
# where age <= 12 and gend_cd = 'W'
# order by age desc, pt_name asc