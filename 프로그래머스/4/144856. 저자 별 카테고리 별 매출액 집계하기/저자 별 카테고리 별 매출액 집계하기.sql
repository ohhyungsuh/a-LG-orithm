-- 코드를 입력하세요
SELECT b.author_id, author_name, category, sum(price * sales) as TOTAL_SALES
from book b
left join author a on b.author_id = a.author_id
left join book_sales s on b.book_id = s.book_id
where sales_date like '2022-01-%'
group by b.author_id, category
order by b.author_id, category desc