package com.jaeheonshim.privmsg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DatabaseManager {
    private Plugin plugin;
    private static final String dbName = "privmsg.db";

    private Connection connection;

    private static DatabaseManager instance;

    public static final String CREATE_GROUP_MEMBER_TABLE = "CREATE TABLE IF NOT EXISTS members (" +
            "uid TEXT PRIMARY KEY," +
            "groupId INTEGER" +
            ");";

    private static final String CREATE_GROUP_TABLE = "CREATE TABLE IF NOT EXISTS groups (" +
            "leaderUid TEXT" +
            ");";

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance() {
        if(instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void initialize(Plugin plugin) {
        this.plugin = plugin;
    }

    public Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + getDataFile());
            }

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Database failed to initialize", e);
        } catch (IOException e) {
           throw new RuntimeException("Failed to read database file", e);
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("Failed to load database driver into classpath", e);
        }
    }

    public File getDataFile() throws IOException {
        File dataFile = new File(plugin.getDataFolder(), dbName);
        if(!dataFile.exists()) {
            plugin.getDataFolder().mkdirs();
            dataFile.createNewFile();
        }

        return dataFile;
    }

    private void createDatabaseSchema() {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_GROUP_MEMBER_TABLE);
            stmt.execute(CREATE_GROUP_TABLE);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
