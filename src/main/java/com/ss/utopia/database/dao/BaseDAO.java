package com.ss.utopia.database.dao;

import java.sql.*;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public abstract class BaseDAO<T> {

    protected final Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Updates an object in a database table, and return an auto increment variable if one exists
     *
     * @param query sql prepared statement
     * @param vals query values
     */
    protected int update(String query, Object[] vals) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (vals != null) {
                int i = 1;
                for (Object o : vals) {
                    pstmt.setObject(i++, o);
                }
            }
            pstmt.executeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException throwables) {
            System.err.println(throwables.toString());
        }
        return 0;
    }

    /**
     * Returns a list of parsed data from a table based on query
     * @param query sql prepared statement
     * @param vals query values
     * @return list of parsed objects
     */
    protected List<T> read(String query, Object[] vals) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            if (vals != null) {
                int i = 1;
                for (Object o : vals) {
                    pstmt.setObject(i++, o);
                }
            }
            ResultSet resultSet = pstmt.executeQuery();
            List<T> results = parseData(resultSet);
            resultSet.close();
            return results;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Convert query results to a list of objects of generic type T
     * @param resultSet sql query results
     * @return list of parsed objects
     */
    protected abstract List<T> parseData(ResultSet resultSet) throws SQLException;

}
