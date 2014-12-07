package la.application.exportManagement;

/**
 * Created by Alex on 07.12.2014.
 */
public abstract class ExportCommon {
    public static final String PATH = "statistik";
    public static final String FILENAME_HEAD = "/statistic_";
    public static final String FILENAME_ENDING = ".xlsx";
    public static final String SHEET_VALUES = "Werte";
    public static final String SHEET_STATISTICS = "Statistik";

    public static final int COLUMN_VALUE_REWARDS_BEGIN = 0;
    public static final int ROW_VALUE_REWARDS_BEGIN = 0;

    public static final int COLUMN_STATISTIC_WINS_BEGIN = 0;
    public  static final int ROW_STATISTIC_WINS_BEGIN = 3;
    public  static final int COLUMN_STATISTIC_LOSS_BEGIN = 0;
    public  static final int ROW_STATISTIC_LOSS_BEGIN = ROW_STATISTIC_WINS_BEGIN+4;
    public  static final int COLUMN_STATISTIC_REWARDS_BEGIN = 0;
    public  static final int ROW_STATISTIC_REWARDS_BEGIN = ROW_STATISTIC_LOSS_BEGIN+4;
    public  static final int COLUMN_STATISTIC_STEPS_BEGIN = 0;
    public  static final int ROW_STATISTIC_STEPS_BEGIN = ROW_STATISTIC_REWARDS_BEGIN+4;

    public  static final int COLUMN_VALUE_TRIES_BEGIN = 0;
    public  static final int ROW_VALUE_TRIES_BEGIN = ROW_STATISTIC_STEPS_BEGIN+6;

}
