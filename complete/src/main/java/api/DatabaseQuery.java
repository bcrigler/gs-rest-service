package api;

import api.utilities.Converter;
import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseQuery {

    private ResultSet rs;

    public DatabaseQuery(String query) {

        Properties props = getConnectionData();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String passwd = props.getProperty("db.passwd");

        String queryString = query;

        try (Connection con = DriverManager.getConnection(url, user, passwd);
             PreparedStatement pst = con.prepareStatement(queryString);
             ResultSet rs = pst.executeQuery()) {

             setResults(rs);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DatabaseQuery.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /** Get's around the lack of a return on the constructor and returns it from this
     *
     * @param results
     * @return void
     */
    private void setResults(ResultSet results) {
        this.rs = results;
    }

    /** Get's the result set from the query
     *
     * @return jsonArray
     */
    public JSONArray getResults() {
        Converter converter = new Converter();
        try {
            return converter.convertToJSON(this.rs);
        } catch(Exception ex) {
            Logger lgr = Logger.getLogger(DatabaseQuery.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return new JSONArray();
        }
    }

    /** Gets the DB Connection Credentials
     *
     * @return Properties
     */
    private static Properties getConnectionData() {

        Properties props = new Properties();

        String fileName = "src/main/resources/application.properties";

        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (IOException ex) {
            Logger lgr = Logger.getLogger(DatabaseQuery.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return props;
    }

}
