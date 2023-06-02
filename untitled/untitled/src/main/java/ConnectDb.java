import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectDb implements Job {
    private static final Logger LOGGER = Logger.getLogger(TaskListener.class.getName());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//10.121.4.182:1521/report", "bi_report", "E#5qWC#$s");
            LOGGER.info("Connected to database");

            String command = "CALL pck_preparing_dat_dashboard.main(SYSDATE - 1)";

            CallableStatement cstmt =  con.prepareCall(command);

            cstmt.execute();
            cstmt.close();
            LOGGER.info("Finish!!!!!!");

        } catch(Exception ex) {
            try {
                throw ex;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        };

    }
}
