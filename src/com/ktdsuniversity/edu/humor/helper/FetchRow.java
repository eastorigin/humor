package com.ktdsuniversity.edu.humor.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface FetchRow {

	public void fetch(ResultSet rs) throws SQLException;
}
