create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create unique index IX_AC4C7667 on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, targetFileVersionId);

create index IX_7F996123 on SyncDLObject (companyId, modifiedTime, repositoryId);
create unique index IX_E3F57BD6 on SyncDLObject (type_, typePK);