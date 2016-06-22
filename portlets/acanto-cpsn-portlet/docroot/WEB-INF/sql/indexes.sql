create index IX_7775F55B on GB_Account (groupId);
create index IX_5EAF22A5 on GB_Account (uuid_);
create index IX_58E13863 on GB_Account (uuid_, companyId);
create unique index IX_8D127BA5 on GB_Account (uuid_, groupId);