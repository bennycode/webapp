package com.welovecoding;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaDumper {

	private static final Logger log = LoggerFactory.getLogger(SchemaDumper.class.getName());

	private static final String SHEMA_PATTERN = "PUBLIC";
	private static final String COLUMN_NAME_PATTERN = null;
	private static final String TABLE_NAME_PATTERN = null;
	private static final String[] TABLE_TYPES = new String[]{"TABLE"};

	private static final String LABEL_TABLE_NAME = "TABLE_NAME";
	private static final String LABEL_COLUMN_NAME = "COLUMN_NAME";
	private static final String LABEL_TYPE_NAME = "TYPE_NAME";
	private static final String LABEL_IS_NULLABLE = "IS_NULLABLE";

	public static void dumpSchema(String catalogName, Connection connection) throws SQLException {
		List<String> tables = getTables(catalogName, connection.getMetaData());

		for (String table : tables) {
			getColumnsAsTable(catalogName, table, connection.getMetaData());
		}
	}

	private static List<String> getTables(String catalogName, DatabaseMetaData meta) throws SQLException {
		List<String> tables = new ArrayList<>();

		ResultSet result = meta.getTables(catalogName.toUpperCase(), SHEMA_PATTERN, TABLE_NAME_PATTERN, TABLE_TYPES);

		while (result.next()) {
			for (int i = 1; i < result.getMetaData().getColumnCount(); i++) {
				if (LABEL_TABLE_NAME.equals(result.getMetaData().getColumnLabel(i))) {
					tables.add(result.getString(i));
				}
			}
		}

		log.debug("Found " + tables.size() + " tables.");
		return tables;
	}

	private static void getColumns(String catalogName, String table, DatabaseMetaData meta) throws SQLException {

		ResultSet result = meta.getColumns(catalogName.toUpperCase(), SHEMA_PATTERN, table.toUpperCase(), COLUMN_NAME_PATTERN);
		StringBuilder sb = new StringBuilder();
		String prevTableName = "";
		while (result.next()) {
			for (int i = 1; i < result.getMetaData().getColumnCount(); i++) {
				if (LABEL_TABLE_NAME.equals(result.getMetaData().getColumnLabel(i)) && !prevTableName.equals(result.getString(i))) {
					prevTableName = result.getString(i);
					sb.append(result.getString(i));
					sb.append("\n");
				}
				if (LABEL_COLUMN_NAME.equals(result.getMetaData().getColumnLabel(i))) {
					sb.append("\t");
					sb.append(result.getString(i));
					sb.append(" ");
				}
				if (LABEL_TYPE_NAME.equals(result.getMetaData().getColumnLabel(i))) {
					sb.append(result.getMetaData().getColumnLabel(i)).append(": ").append(result.getString(i));
					sb.append(" ");
				}
				if (LABEL_IS_NULLABLE.equals(result.getMetaData().getColumnLabel(i))) {
					sb.append(result.getMetaData().getColumnLabel(i)).append(": ").append(result.getString(i));
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		log.debug(sb.toString());
	}

	private static void getColumnsAsTable(String catalogName, String table, DatabaseMetaData meta) throws SQLException {

		ResultSet result = meta.getColumns(catalogName.toUpperCase(), SHEMA_PATTERN, table.toUpperCase(), COLUMN_NAME_PATTERN);
		StringBuilder sb = new StringBuilder();
		String prevTableName = "";

		SystemOutTable soutTable = new SystemOutTable();
		while (result.next()) {
			soutTable.addRow();
			for (int i = 1; i < result.getMetaData().getColumnCount(); i++) {
				if (LABEL_TABLE_NAME.equals(result.getMetaData().getColumnLabel(i)) && !prevTableName.equals(result.getString(i))) {
					prevTableName = result.getString(i);
					log.debug(result.getString(i));
				}
				if (LABEL_COLUMN_NAME.equals(result.getMetaData().getColumnLabel(i))) {
					soutTable.putInLastRow(result.getMetaData().getColumnLabel(i) + ": " + result.getString(i));
				}
				if (LABEL_TYPE_NAME.equals(result.getMetaData().getColumnLabel(i))) {
					soutTable.putInLastRow(result.getMetaData().getColumnLabel(i) + ": " + result.getString(i));
				}
				if (LABEL_IS_NULLABLE.equals(result.getMetaData().getColumnLabel(i))) {
					soutTable.putInLastRow(result.getMetaData().getColumnLabel(i) + ": " + result.getString(i));
				}
			}
		}
		log.debug(soutTable.toString());
	}

}
