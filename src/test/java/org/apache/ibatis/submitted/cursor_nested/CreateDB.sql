
--
--    Copyright 2009-2012 The MyBatis Team
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

drop table if exists users;

create table users (
  id int,
  name varchar(20),
  group_id int,
  rol_id int
);

insert into users values(1, 'User1', 1, 1);
insert into users values(1, 'User1', 1, 2);
insert into users values(1, 'User1', 2, 1);
insert into users values(1, 'User1', 2, 2);
insert into users values(1, 'User1', 2, 3);
insert into users values(2, 'User2', 1, 1);
insert into users values(2, 'User2', 1, 2);
insert into users values(2, 'User2', 1, 3);
insert into users values(3, 'User3', 1, 1);
insert into users values(3, 'User3', 2, 1);
insert into users values(3, 'User3', 3, 1);
insert into users values(4, 'User4', 1, 1);
insert into users values(4, 'User4', 1, 2);
insert into users values(4, 'User4', 2, 1);
insert into users values(4, 'User4', 2, 2);