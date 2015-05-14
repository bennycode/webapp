package com.welovecoding;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SystemOutTableTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void shouldAutomaticallyAddANewRow() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    table.putInLastRow("test");
    table.putInLastRow("testtest");
    table.putInLastRow("testtesttest");

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo("test testtest testtesttest \n"));
  }

  @Test
  public void shouldFillWithSpaces() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    table.putInLastRow("test");
    table.putInLastRow("testtest");
    table.putInLastRow("testtesttest");

    table.addRow();
    table.putInLastRow("testtesttest");
    table.putInLastRow("testtest");
    table.putInLastRow("test");

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo("test         testtest testtesttest \ntesttesttest testtest test         \n"));
  }

  @Test
  public void shouldPrintEmptyTable() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo(""));
  }

  @Test
  public void shouldPrintEmptyLines() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    table.putInLastRow(null);
    table.addRow();
    table.addRow();

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo(" \n \n \n"));
  }

  @Test
  public void shouldFillEmptyRows() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    table.putInLastRow(null);
    table.addRow();
    table.addRow();
    table.putInLastRow("testtesttest");
    table.putInLastRow("testtest");
    table.putInLastRow("test");

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo("                           \n                           \ntesttesttest testtest test \n"));
  }

  @Test
  public void shouldReplaceNewLines() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    table.putInLastRow(null);
    table.addRow();
    table.putInLastRow("test\ntesttest");
    table.putInLastRow("testtest");
    table.putInLastRow("test");

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo("                           \ntesttesttest testtest test \n"));
  }

  @Test
  public void shouldReplaceTabsWithFourSpaces() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    table.putInLastRow(null);
    table.addRow();
    table.putInLastRow("test\ttesttest");
    table.putInLastRow("testtest");
    table.putInLastRow("test");

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo("                               \ntest    testtest testtest test \n"));
  }

  @Test
  public void shouldParseTheSame() {
    System.out.println(name.getMethodName());

    SystemOutTable table = new SystemOutTable();
    String tableToParse = "test         testtest testtesttest \ntesttesttest testtest test         ";
    table.parseBy(tableToParse, "\n", " ");

    String tableString = table.toString();
    System.out.println(table);

    assertThat(tableString, equalTo("test         testtest testtesttest \ntesttesttest testtest test         \n"));
  }

}
