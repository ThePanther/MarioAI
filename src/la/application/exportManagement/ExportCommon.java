package la.application.exportManagement;

public abstract class ExportCommon {
    public static final String PATH = "statistik";
    public static final String FILENAME_HEAD = "/statistic_";
    public static final String FILENAME_ENDING = ".xlsx";
    public static final String SHEET_VALUES = "Werte";
    public static final String SHEET_CHART = "Charts";

    public static final int COLUMN_VALUE_REWARDS_BEGIN = 0;
    public static final int ROW_VALUE_REWARDS_BEGIN = 0;

    public  static final int COLUMN_STATISTIC_WINS_BEGIN = 0;
    public  static final int ROW_STATISTIC_WINS_BEGIN = 3;
    public  static final int COLUMN_STATISTIC_LOSS_BEGIN = 0;
    public  static final int ROW_STATISTIC_LOSS_BEGIN = ROW_STATISTIC_WINS_BEGIN+4;
    public  static final int COLUMN_STATISTIC_REWARDS_BEGIN = 0;
    public  static final int ROW_STATISTIC_REWARDS_BEGIN = ROW_STATISTIC_LOSS_BEGIN+4;
    public  static final int COLUMN_STATISTIC_STEPS_BEGIN = 0;
    public  static final int ROW_STATISTIC_STEPS_BEGIN = ROW_STATISTIC_REWARDS_BEGIN+4;

    public  static final int COLUMN_VALUE_TRIES_BEGIN = 0;
    public  static final int ROW_VALUE_TRIES_BEGIN = ROW_STATISTIC_STEPS_BEGIN+6;

    public  static final int ANCHOR_DX1_CHART_ALL_REWARDS = 0;
    public  static final int ANCHOR_DY1_CHART_ALL_REWARDS = 0;
    public  static final int ANCHOR_DX2_CHART_ALL_REWARDS = 0;
    public  static final int ANCHOR_DY2_CHART_ALL_REWARDS = 0;
    public  static final int ANCHOR_COL1_CHART_ALL_REWARDS = 3;
    public  static final int ANCHOR_ROW1_CHART_ALL_REWARDS = 3;
    public  static final int ANCHOR_COL2_CHART_ALL_REWARDS = ANCHOR_COL1_CHART_ALL_REWARDS+20;
    public  static final int ANCHOR_ROW2_CHART_ALL_REWARDS = ANCHOR_ROW1_CHART_ALL_REWARDS+10;

    public  static final int CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_XS = ROW_VALUE_TRIES_BEGIN+1;
    public  static final int CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_XS = ROW_VALUE_TRIES_BEGIN+1;
    public  static final int CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_XS = 3;
    public  static final int CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_XS = 3;

    public  static final int CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_YS = ROW_VALUE_TRIES_BEGIN+1;
    public  static final int CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_YS = ROW_VALUE_TRIES_BEGIN+1;
    public  static final int CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_YS = 2;
    public  static final int CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_YS = 2;
}
