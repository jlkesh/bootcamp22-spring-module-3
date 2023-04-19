drop view if exists task_view;
create view task_view as
select id, name, label
from tasks;