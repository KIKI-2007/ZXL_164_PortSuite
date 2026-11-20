package com.zxl.backup164.core;
import java.text.SimpleDateFormat;
import java.util.Date;
/** Deterministic timestamp naming helper for backup archives. */
public final class BackupNames {
 private BackupNames() {}
 public static String create(String worldName, Date date) {
  String safe=(worldName==null?"world":worldName).replaceAll("[^A-Za-z0-9._-]", "_");
  return safe + "-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(date==null?new Date():date) + ".zip";
 }
}
