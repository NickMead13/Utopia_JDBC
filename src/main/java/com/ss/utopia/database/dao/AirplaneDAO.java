package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Airplane;
import com.ss.utopia.database.entity.AirplaneType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class AirplaneDAO extends BaseDAO<Airplane> {

    public AirplaneDAO(Connection connection) {
        super(connection);
    }

    public List<Airplane> readAllAirplanes() {
        return read("SELECT airplane.id, airplane.type_id, airplane_type.seats_first, airplane_type.seats_business, airplane_type.seats_economy FROM airplane " +
                "INNER JOIN airplane_type " +
                "ON airplane.type_id = airplane_type.id " +
                "ORDER BY airplane.id", null);
    }

    public List<Airplane> readAirplanesBySeatCount(int minSeats) {
        return read("SELECT airplane.id, airplane.type_id, airplane_type.max_capacity FROM airplane " +
                "INNER JOIN airplane_type " +
                "ON airplane.type_id = airplane_type.id " +
                "WHERE airplane_type.max_capacity > ? " +
                "ORDER BY airplane.id", new Object[] {minSeats});
    }

    @Override
    public List<Airplane> parseData(ResultSet resultSet) throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();

        while (resultSet.next()) {
            Airplane airplane = new Airplane();
            airplane.setId(resultSet.getInt("id"));
            AirplaneType type = new AirplaneType();
            type.setId(resultSet.getInt("type_id"));
            type.setMaxCapacity(resultSet.getInt("max_capacity"));
            airplane.setType(type);
            airplanes.add(airplane);
        }

        return airplanes;
    }

}
