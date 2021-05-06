# 1.可以并列,但是并列会站一个排名 1 2 2 4
select
    subject,
    name,
    score,
    rank() over (distribute by subject sort by score)
from score;

# 2.可以并列,但是并列不会占一个排名 1 2 2 3
select
    subject,
    name,
    score,
    dense_rank() over (distribute by subject sort by score)
from score;

# 3.不允许并列排序
select
    subject,
    name,
    score,
    row_number()  over (distribute by subject sort by score)
from score;
