create table GB_Account (
	uuid_ VARCHAR(75) null,
	accountId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	password_ VARCHAR(75) null
);