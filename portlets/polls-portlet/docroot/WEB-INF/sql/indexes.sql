create index IX_34C7EF05 on Polls_PollsChoice (questionId);
create unique index IX_A1F22704 on Polls_PollsChoice (questionId, name);
create index IX_3A6EE84 on Polls_PollsChoice (uuid_);

create index IX_687C1055 on Polls_PollsQuestion (groupId);
create index IX_B693B41F on Polls_PollsQuestion (uuid_);
create index IX_33D0D6A9 on Polls_PollsQuestion (uuid_, companyId);
create unique index IX_D9C0F36B on Polls_PollsQuestion (uuid_, groupId);

create index IX_E1609DC9 on Polls_PollsVote (choiceId);
create index IX_41D37ECE on Polls_PollsVote (questionId);
create unique index IX_E6442908 on Polls_PollsVote (questionId, userId);

create index IX_9BDCE791 on _PollsChoice (questionId);
create unique index IX_61558690 on _PollsChoice (questionId, name);
create index IX_1323CE78 on _PollsChoice (uuid_);

create index IX_ADDFD649 on _PollsQuestion (groupId);
create index IX_DA586713 on _PollsQuestion (uuid_);
create index IX_A7DA8335 on _PollsQuestion (uuid_, companyId);
create unique index IX_992452F7 on _PollsQuestion (uuid_, groupId);

create index IX_C17FBC55 on _PollsVote (choiceId);
create index IX_96A52A5A on _PollsVote (questionId);
create unique index IX_A5A78894 on _PollsVote (questionId, userId);