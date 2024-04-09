package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface AddVehicleStrategy {
    PreparedStatement prepare(Connection connection) throws SQLException;
}
