# Write your MySQL query statement below
SELECT weather1.id
FROM Weather as weather1
INNER JOIN Weather AS weather2
ON DATEDIFF(weather1.recordDate, weather2.recordDate) = 1
AND weather1.temperature>weather2.temperature