package la.application.exportManagement;

import jxl.CellView;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import la.common.Reward;
import la.common.Try;

import java.util.ArrayList;
import java.util.List;

public class CSVSheetFormat {

    private final int COLUMN_VALUE_REWARDS_BEGIN = 0;
    private final int ROW_VALUE_REWARDS_BEGIN = 0;
    private final int COLUMN_VALUE_TRIES_BEGIN = 0;
    private final int ROW_VALUE_TRIES_BEGIN = 3;

    private final int COLUMN_STATISTIC_WINS_BEGIN = 0;
    private final int ROW_STATISTIC_WINS_BEGIN = 0;
    private final int COLUMN_STATISTIC_LOSS_BEGIN = 0;
    private final int ROW_STATISTIC_LOSS_BEGIN = 4;
    private final int COLUMN_STATISTIC_REWARDS_BEGIN = 0;
    private final int ROW_STATISTIC_REWARDS_BEGIN = 8;
    private final int COLUMN_STATISTIC_STEPS_BEGIN = 0;
    private final int ROW_STATISTIC_STEPS_BEGIN = 12;


    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;

    public CSVSheetFormat() throws WriteException {
        WritableFont timesFont1 = new WritableFont(WritableFont.TIMES, 10);
        times = new WritableCellFormat(timesFont1);
        times.setWrap(true);

        WritableFont timesFont2 = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(timesFont2);
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);
    }

    /**********************************************************************************
     *  VALUES
     **********************************************************************************/

    public void createLabelValues(WritableSheet sheet, List<Reward> rewards) throws WriteException {
        // Die Reward Gruppe
        addCaption(sheet, COLUMN_VALUE_REWARDS_BEGIN, ROW_VALUE_REWARDS_BEGIN, "RewardsGroup");
        // Alle Rewards
        for (int i = COLUMN_VALUE_REWARDS_BEGIN; i < rewards.size(); i++) {
            addCaption(sheet, i + 1, ROW_VALUE_REWARDS_BEGIN, rewards.get(i).getName());
        }
        // Die Tries
        addCaption(sheet, COLUMN_VALUE_TRIES_BEGIN,    ROW_VALUE_TRIES_BEGIN, "ID");
        addCaption(sheet, COLUMN_VALUE_TRIES_BEGIN +1, ROW_VALUE_TRIES_BEGIN, "Win");
        addCaption(sheet, COLUMN_VALUE_TRIES_BEGIN +2, ROW_VALUE_TRIES_BEGIN, "Rewards");
        addCaption(sheet, COLUMN_VALUE_TRIES_BEGIN +3, ROW_VALUE_TRIES_BEGIN, "Steps");
    }

    public void createContentValues(WritableSheet sheet, int rewardGroupID, List<Reward> rewards, List<Try> tries) throws WriteException {
        addNumber(sheet, COLUMN_VALUE_REWARDS_BEGIN, ROW_VALUE_REWARDS_BEGIN +1, rewardGroupID);
        for (int i = COLUMN_VALUE_REWARDS_BEGIN; i < rewards.size(); i++) {
            addNumber(sheet, i + 1, ROW_VALUE_REWARDS_BEGIN +1, rewards.get(i).getReward());
        }

        for (int i = 0; i < tries.size(); i++) {
            addNumber(sheet, COLUMN_VALUE_TRIES_BEGIN,    ROW_VALUE_TRIES_BEGIN +1+i, tries.get(i).getId());
            addNumber(sheet, COLUMN_VALUE_TRIES_BEGIN +1, ROW_VALUE_TRIES_BEGIN +1+i, tries.get(i).getWin());
            addNumber(sheet, COLUMN_VALUE_TRIES_BEGIN +2, ROW_VALUE_TRIES_BEGIN +1+i, tries.get(i).getRewards());
            addNumber(sheet, COLUMN_VALUE_TRIES_BEGIN +3, ROW_VALUE_TRIES_BEGIN +1+i, tries.get(i).getSteps());
        }
    }

    /**********************************************************************************
     *  Statistics
     **********************************************************************************/

    public void createLabelStatistics(WritableSheet sheetStatistics) throws WriteException {
        // Wins
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN, ROW_STATISTIC_WINS_BEGIN, "WINS");
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN, ROW_STATISTIC_WINS_BEGIN + 1, "Max");
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 1, ROW_STATISTIC_WINS_BEGIN + 1, "Min");
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 2, ROW_STATISTIC_WINS_BEGIN + 1, "Durchschnitt");
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 3, ROW_STATISTIC_WINS_BEGIN + 1, "Gesamt");
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 4, ROW_STATISTIC_WINS_BEGIN + 1, "Prozent");
        addCaption(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 5, ROW_STATISTIC_WINS_BEGIN + 1, "Max in Folge");
        // Loss
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN, ROW_STATISTIC_LOSS_BEGIN, "LOSS");
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN, ROW_STATISTIC_LOSS_BEGIN + 1, "Max");
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 1, ROW_STATISTIC_LOSS_BEGIN + 1, "Min");
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 2, ROW_STATISTIC_LOSS_BEGIN + 1, "Durchschnitt");
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 3, ROW_STATISTIC_LOSS_BEGIN + 1, "Gesamt");
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 4, ROW_STATISTIC_LOSS_BEGIN + 1, "Prozent");
        addCaption(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 5, ROW_STATISTIC_LOSS_BEGIN + 1, "in Folge");
        // Rewards
        addCaption(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN, ROW_STATISTIC_REWARDS_BEGIN, "REWARDS");
        addCaption(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN, ROW_STATISTIC_REWARDS_BEGIN + 1, "Max");
        addCaption(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN + 1, ROW_STATISTIC_REWARDS_BEGIN + 1, "Min");
        addCaption(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN + 2, ROW_STATISTIC_REWARDS_BEGIN + 1, "Durchschnitt");
        // Steps
        addCaption(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN, ROW_STATISTIC_STEPS_BEGIN, "STEPS");
        addCaption(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN, ROW_STATISTIC_STEPS_BEGIN + 1, "Max");
        addCaption(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 1, ROW_STATISTIC_STEPS_BEGIN + 1, "Min");
        addCaption(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 2, ROW_STATISTIC_STEPS_BEGIN + 1, "Durchschnitt");
        addCaption(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 3, ROW_STATISTIC_STEPS_BEGIN + 1, "Gesamt");
        addCaption(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 4, ROW_STATISTIC_STEPS_BEGIN + 1, "Prozent");

    }
    public void createContentStatistics(WritableSheet sheetValues, WritableSheet sheetStatistics, int tryCount) throws WriteException {
        double winMax = 0;
        double winMin = 0;
        double winAverage = 0;
        double winGesamtInt = 0;
        int winInFolge = 0;
        int winMaxInFolge = 0;
        int lossInFolge = 0;
        int lossMaxInFolge = 0;
        double lossMax = 0;
        double lossMin = 0;
        double lossAverage = 0;
        double rewardMax = 0;
        double rewardMin = 0;
        double rewardAverage = 0;
        double stepMax = 0;
        double stepMin = 0;
        double stepAverage = 0;

        boolean firstWin = true;
        boolean firstLoss = true;
        for (int i = ROW_VALUE_TRIES_BEGIN+1; i < ROW_VALUE_TRIES_BEGIN+tryCount; i++){
            double winValue = Double.parseDouble(sheetValues.getCell(COLUMN_VALUE_TRIES_BEGIN+1,i).getContents().replace(",","."));
            double reward = Double.parseDouble(sheetValues.getCell(COLUMN_VALUE_TRIES_BEGIN+2,i).getContents().replace(",","."));
            int step = Integer.parseInt(sheetValues.getCell(COLUMN_VALUE_TRIES_BEGIN+3,i).getContents());

            if (winValue == 1){

                winInFolge++;
                winMax = (firstWin) ? reward : Math.max(winMax, reward);
                winMin = (firstWin) ? reward : Math.min(winMin, reward);
                winAverage += reward;

                lossInFolge = 0;
                firstWin = false;
            }else{
                winInFolge = 0;

                lossMax = (firstLoss) ? reward : Math.max(lossMax, reward);
                lossMin = (firstLoss) ? reward : Math.min(lossMin, reward);
                lossAverage += reward;
                lossInFolge++;
                firstLoss = false;
            }

            winGesamtInt += winValue;
            winMaxInFolge = Math.max(winInFolge,winMaxInFolge);

            lossMaxInFolge = Math.max(lossInFolge,lossMaxInFolge);

            rewardMax = Math.max(reward,rewardMax);
            rewardMin = Math.min(reward,rewardMax);
            rewardAverage += reward;

            stepMax = Math.max(step,stepMax);
            stepMin = Math.min(step,stepMax);
            stepAverage += step;
        }
        double winProzent = ((winGesamtInt/tryCount)*100);
        winAverage = winAverage/tryCount;
        double lossGesamtInt = tryCount-winGesamtInt;
        double lossProzent = ((lossGesamtInt/tryCount)*100);
        lossAverage= lossAverage/tryCount;
        rewardAverage = rewardAverage/tryCount;
        stepAverage = stepAverage/tryCount;
        double stepGesamtInt = stepAverage;
        double stepProzent = ((stepGesamtInt/tryCount)*100);

        // Wins
        addNumber(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN    , ROW_STATISTIC_WINS_BEGIN + 2, winMax);
        addNumber(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 1, ROW_STATISTIC_WINS_BEGIN + 2, winMin);
        addNumber(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 2, ROW_STATISTIC_WINS_BEGIN + 2, winAverage);
        addNumber(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 3, ROW_STATISTIC_WINS_BEGIN + 2, winGesamtInt);
        addNumber(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 4, ROW_STATISTIC_WINS_BEGIN + 2, winProzent);
        addNumber(sheetStatistics, COLUMN_STATISTIC_WINS_BEGIN + 5, ROW_STATISTIC_WINS_BEGIN + 2, winMaxInFolge);
        // Loss
        addNumber(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN    , ROW_STATISTIC_LOSS_BEGIN + 2, lossMax);
        addNumber(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 1, ROW_STATISTIC_LOSS_BEGIN + 2, lossMin);
        addNumber(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 2, ROW_STATISTIC_LOSS_BEGIN + 2, lossAverage);
        addNumber(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 3, ROW_STATISTIC_LOSS_BEGIN + 2, lossGesamtInt);
        addNumber(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 4, ROW_STATISTIC_LOSS_BEGIN + 2, lossProzent);
        addNumber(sheetStatistics, COLUMN_STATISTIC_LOSS_BEGIN + 5, ROW_STATISTIC_LOSS_BEGIN + 2, lossMaxInFolge);
        // Rewards
        addNumber(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN    , ROW_STATISTIC_REWARDS_BEGIN + 2, rewardMax);
        addNumber(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN + 1, ROW_STATISTIC_REWARDS_BEGIN + 2, rewardMin);
        addNumber(sheetStatistics, COLUMN_STATISTIC_REWARDS_BEGIN + 2, ROW_STATISTIC_REWARDS_BEGIN + 2, rewardAverage);
        // Steps
        addNumber(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN    , ROW_STATISTIC_STEPS_BEGIN + 2, stepMax);
        addNumber(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 1, ROW_STATISTIC_STEPS_BEGIN + 2, stepMin);
        addNumber(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 2, ROW_STATISTIC_STEPS_BEGIN + 2, stepAverage);
        addNumber(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 3, ROW_STATISTIC_STEPS_BEGIN + 2, stepGesamtInt);
        addNumber(sheetStatistics, COLUMN_STATISTIC_STEPS_BEGIN + 4, ROW_STATISTIC_STEPS_BEGIN + 2, stepProzent);

    }

    /**********************************************************************************
     *  Private Methoden
     **********************************************************************************/


    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws  WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
                           Integer integer) throws WriteException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }
    private void addNumber(WritableSheet sheet, int column, int row,
                           double rewards) throws WriteException  {
        Number number;
        number = new Number(column, row, rewards, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }
}
