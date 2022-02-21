package main.java;

import main.java.entities.PubmedArticleSet;

import java.sql.*;

public class PostgreSQLConnector {

    public void establishConnection() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "studentportal", "studentportal");
            System.out.println("Opened database successfully");

            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PAPERS" +
                    "(PMID INT PRIMARY KEY         NOT NULL," +
                    " ARTICLE_TITLE  VARCHAR(1000)  NOT NULL, " +
                    " FIRST_AUTHOR   VARCHAR(500)   NOT NULL, " +
                    " PUBLISHER      VARCHAR(500), " +
                    " PUBLISHED_DATE DATE, " +
                    " UPLOADER       VARCHAR(100))";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void insertIntoPapers(final PubmedArticleSet pubmedArticleSet) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "studentportal", "studentportal");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            String sql = "INSERT INTO PAPERS (PMID, ARTICLE_TITLE, FIRST_AUTHOR, PUBLISHER, PUBLISHED_DATE, UPLOADER) "
                    + "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Connection finalConnection = connection;
            pubmedArticleSet.getPubmedArticles().stream().forEach(a -> {
                try {
                    String publishedDate = a.getMedlineCitation().getDateCompleted().getYear() + "-" + a.getMedlineCitation().getDateCompleted().getMonth() + "-" + a.getMedlineCitation().getDateCompleted().getDay();
                    preparedStatement.setInt(1, a.getMedlineCitation().getPMID());
                    preparedStatement.setString(2, a.getMedlineCitation().getArticle().getArticleTitle());
                    preparedStatement.setString(3, a.getMedlineCitation().getArticle().getAuthorList() != null ? (a.getMedlineCitation().getArticle().getAuthorList().getAuthors().get(0).getForeName() + " " + a.getMedlineCitation().getArticle().getAuthorList().getAuthors().get(0).getLastName()) : "No authors found");
                    preparedStatement.setString(4, a.getMedlineCitation().getArticle().getJournal().getTitle());
                    preparedStatement.setDate(5, java.sql.Date.valueOf(publishedDate));
                    preparedStatement.setString(6, "Venkat");
                    Boolean insert = preparedStatement.execute();
                    finalConnection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records inserted successfully");
    }
}

