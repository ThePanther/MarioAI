package la.application.exportManagement;

import la.common.Reward;
import la.common.Try;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

import static la.application.exportManagement.ExportCommon.*;

public class SheetFormater {
    private final Workbook wb;

    public SheetFormater(Workbook wb) {
        this.wb = wb;
    }

    /**********************************************************************************
     *  VALUES
     **********************************************************************************/

    public void createLabelValues(Sheet sheet, List<Reward> rewards ) {

        Row rowRewardsLabel = sheet.createRow(ROW_VALUE_REWARDS_BEGIN);
        // Die Reward Gruppe
        addCaption(rowRewardsLabel, COLUMN_VALUE_REWARDS_BEGIN, "RewardsGroup");
        // Alle Rewards
        for (int i = COLUMN_VALUE_REWARDS_BEGIN; i < rewards.size(); i++) {
            addCaption(rowRewardsLabel, i + 1, rewards.get(i).getName());
        }
        Row rowTries = sheet.createRow(ROW_VALUE_TRIES_BEGIN);
        // Die Tries
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN,    "ID");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +1, "Win");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +2, "Rewards");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +3, "Steps");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +4, "count [win]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +5, "count [death]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +6, "count [hurt]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +7, "count [kill]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +8, "count [elapsed_frame]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +9, "count [right]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +10,"count [left]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +11,"count [up]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +12,"count [down]");

        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +14, "count * reward [win]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +15, "count * reward [death]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +16, "count * reward [hurt]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +17, "count * reward [kill]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +18, "count * reward [elapsed_frame]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +19, "count * reward [right]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +20, "count * reward [left]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +21, "count * reward [up]");
        addCaption(rowTries, COLUMN_VALUE_TRIES_BEGIN +22, "count * reward [down]");
    }

    public void createContentValues( Sheet sheet, int rewardGroupID, List<Reward> rewards, List<Try> tries)  {
        Row rowRewards = sheet.createRow(ROW_VALUE_REWARDS_BEGIN + 1);
        addNumber(rowRewards, COLUMN_VALUE_REWARDS_BEGIN, rewardGroupID);
        for (int i = COLUMN_VALUE_REWARDS_BEGIN; i < rewards.size(); i++) {
            addNumber(rowRewards, i + 1, rewards.get(i).getReward());
        }
        
        for (int i = 0; i < tries.size(); i++) {
            Row rowTries = sheet.createRow(ROW_VALUE_TRIES_BEGIN + 1 + i);
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN, 	  tries.get(i).getId());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +1,  tries.get(i).getWin());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +2,  tries.get(i).getRewards());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +3,  tries.get(i).getSteps());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +4,  tries.get(i).getReward_win_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +5,  tries.get(i).getReward_death_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +6,  tries.get(i).getReward_hurt_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +7,  tries.get(i).getReward_kill_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +8,  tries.get(i).getReward_elapsed_frame_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +9,  tries.get(i).getReward_move_right_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +10, tries.get(i).getReward_move_left_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +11, tries.get(i).getReward_move_up_count());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +12, tries.get(i).getReward_move_down_count());

            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +14, tries.get(i).getReward_win_count() * rewards.get(0).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +15, tries.get(i).getReward_death_count() * rewards.get(1).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +16, tries.get(i).getReward_hurt_count() * rewards.get(2).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +17, tries.get(i).getReward_kill_count() * rewards.get(3).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +18, tries.get(i).getReward_elapsed_frame_count() * rewards.get(4).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +19, tries.get(i).getReward_move_right_count() * rewards.get(5).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +20, tries.get(i).getReward_move_left_count() * rewards.get(6).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +21, tries.get(i).getReward_move_up_count() * rewards.get(7).getReward());
            addNumber(rowTries, COLUMN_VALUE_TRIES_BEGIN +22, tries.get(i).getReward_move_down_count() * rewards.get(8).getReward());
        }
    }

    /**********************************************************************************
     *  Statistics
     **********************************************************************************/

    public void createLabelStatistics( Sheet sheet)   {
        // Wins
        Row rowWinsLabel = sheet.createRow(ROW_STATISTIC_WINS_BEGIN);
        Row rowWinsContent = sheet.createRow(ROW_STATISTIC_WINS_BEGIN + 1);
        addCaption(rowWinsLabel, COLUMN_STATISTIC_WINS_BEGIN,       "WINS");
        addCaption(rowWinsContent, COLUMN_STATISTIC_WINS_BEGIN,     "Max [Reward]");
        addCaption(rowWinsContent, COLUMN_STATISTIC_WINS_BEGIN + 1, "Min [Reward]");
        addCaption(rowWinsContent, COLUMN_STATISTIC_WINS_BEGIN + 2, "Durchschnitt [Reward]");
        addCaption(rowWinsContent, COLUMN_STATISTIC_WINS_BEGIN + 3, "Gesamt [Win]");
        addCaption(rowWinsContent, COLUMN_STATISTIC_WINS_BEGIN + 4, "Prozent [Win]");
        addCaption(rowWinsContent, COLUMN_STATISTIC_WINS_BEGIN + 5, "Max in Folge [Win]");
        // Loss
        Row rowLossLabel = sheet.createRow(ROW_STATISTIC_LOSS_BEGIN);
        Row rowLossContent = sheet.createRow(ROW_STATISTIC_LOSS_BEGIN + 1);
        addCaption(rowLossLabel, COLUMN_STATISTIC_LOSS_BEGIN,       "LOSS");
        addCaption(rowLossContent, COLUMN_STATISTIC_LOSS_BEGIN,     "Max [Reward]");
        addCaption(rowLossContent, COLUMN_STATISTIC_LOSS_BEGIN + 1, "Min [Reward]");
        addCaption(rowLossContent, COLUMN_STATISTIC_LOSS_BEGIN + 2, "Durchschnitt [Reward]");
        addCaption(rowLossContent, COLUMN_STATISTIC_LOSS_BEGIN + 3, "Gesamt [loss]");
        addCaption(rowLossContent, COLUMN_STATISTIC_LOSS_BEGIN + 4, "Prozent [loss]");
        addCaption(rowLossContent, COLUMN_STATISTIC_LOSS_BEGIN + 5, "Max in Folge [loss]");
        // Rewards
        Row rowRewardsLabel = sheet.createRow(ROW_STATISTIC_REWARDS_BEGIN);
        Row rowRewardsContent = sheet.createRow(ROW_STATISTIC_REWARDS_BEGIN + 1);
        addCaption(rowRewardsLabel, COLUMN_STATISTIC_REWARDS_BEGIN,       "REWARDS");
        addCaption(rowRewardsContent, COLUMN_STATISTIC_REWARDS_BEGIN,     "Max [Reward]");
        addCaption(rowRewardsContent, COLUMN_STATISTIC_REWARDS_BEGIN + 1, "Min [Reward]");
        addCaption(rowRewardsContent, COLUMN_STATISTIC_REWARDS_BEGIN + 2, "Durchschnitt [Reward]");
        // Steps
        Row rowStepsLabel = sheet.createRow(ROW_STATISTIC_STEPS_BEGIN);
        Row rowStepsContent = sheet.createRow(ROW_STATISTIC_STEPS_BEGIN + 1);
        addCaption(rowStepsLabel, COLUMN_STATISTIC_STEPS_BEGIN,         "STEPS");
        addCaption(rowStepsContent, COLUMN_STATISTIC_STEPS_BEGIN,       "Max [Step]");
        addCaption(rowStepsContent, COLUMN_STATISTIC_STEPS_BEGIN + 1,   "Min [Step]");
        addCaption(rowStepsContent, COLUMN_STATISTIC_STEPS_BEGIN + 2,   "Durchschnitt [Step]");

    }

    public void createContentStatistics( Sheet sheet, int tryCount)   {
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
        boolean first = true;
        for (int i = ROW_VALUE_TRIES_BEGIN+1; i < ROW_VALUE_TRIES_BEGIN+1+tryCount; i++){
            double winValue = Double.parseDouble(sheet.getRow(i).getCell(COLUMN_VALUE_TRIES_BEGIN + 1).toString().replace(",", "."));
            double reward = Double.parseDouble(sheet.getRow(i).getCell(COLUMN_VALUE_TRIES_BEGIN + 2).toString().replace(",", "."));

            double step = Double.parseDouble(sheet.getRow(i).getCell(COLUMN_VALUE_TRIES_BEGIN + 3).toString());


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

            rewardMax = (first) ? reward : Math.max(reward,rewardMax);
            rewardMin = (first) ? reward : Math.min(reward,rewardMin);
            rewardAverage += reward;

            stepMax = (first) ? step : Math.max(step,stepMax);
            stepMin = (first) ? step : Math.min(step,stepMin);
            stepAverage += step;
            first = false;
        }
        double winProzent = ((winGesamtInt/tryCount)*100);
        winAverage = winAverage/tryCount;
        double lossGesamtInt = tryCount-winGesamtInt;
        double lossProzent = ((lossGesamtInt/tryCount)*100);
        lossAverage= lossAverage/tryCount;
        rewardAverage = rewardAverage/tryCount;
        stepAverage = stepAverage/tryCount;

        // Wins
        Row rowWins = sheet.createRow(ROW_STATISTIC_WINS_BEGIN + 2);
        addNumber(rowWins, COLUMN_STATISTIC_WINS_BEGIN,     winMax);
        addNumber(rowWins, COLUMN_STATISTIC_WINS_BEGIN + 1, winMin);
        addNumber(rowWins, COLUMN_STATISTIC_WINS_BEGIN + 2, winAverage);
        addNumber(rowWins, COLUMN_STATISTIC_WINS_BEGIN + 3, winGesamtInt);
        addNumber(rowWins, COLUMN_STATISTIC_WINS_BEGIN + 4, winProzent);
        addNumber(rowWins, COLUMN_STATISTIC_WINS_BEGIN + 5, winMaxInFolge);
        // Loss
        Row rowLoss = sheet.createRow(ROW_STATISTIC_LOSS_BEGIN + 2);
        addNumber(rowLoss, COLUMN_STATISTIC_LOSS_BEGIN    , lossMax);
        addNumber(rowLoss, COLUMN_STATISTIC_LOSS_BEGIN + 1, lossMin);
        addNumber(rowLoss, COLUMN_STATISTIC_LOSS_BEGIN + 2, lossAverage);
        addNumber(rowLoss, COLUMN_STATISTIC_LOSS_BEGIN + 3, lossGesamtInt);
        addNumber(rowLoss, COLUMN_STATISTIC_LOSS_BEGIN + 4, lossProzent);
        addNumber(rowLoss, COLUMN_STATISTIC_LOSS_BEGIN + 5, lossMaxInFolge);
        // Rewards
        Row rowRewards = sheet.createRow(ROW_STATISTIC_REWARDS_BEGIN + 2);
        addNumber(rowRewards, COLUMN_STATISTIC_REWARDS_BEGIN    , rewardMax);
        addNumber(rowRewards, COLUMN_STATISTIC_REWARDS_BEGIN + 1, rewardMin);
        addNumber(rowRewards, COLUMN_STATISTIC_REWARDS_BEGIN + 2, rewardAverage);
        // Steps
        Row rowSteps = sheet.createRow(ROW_STATISTIC_STEPS_BEGIN + 2);
        addNumber(rowSteps, COLUMN_STATISTIC_STEPS_BEGIN    , stepMax);
        addNumber(rowSteps, COLUMN_STATISTIC_STEPS_BEGIN + 1, stepMin);
        addNumber(rowSteps, COLUMN_STATISTIC_STEPS_BEGIN + 2, stepAverage);

    }

    /**********************************************************************************
     *  Private Methoden
     **********************************************************************************/


    private void addCaption(Row row, int columnIndex, String value) {
        //Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);

        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setUnderline(Font.U_SINGLE);
        style.setFont(font);

        cell.setCellStyle(style);
    }

    private void addNumber(Row row, int columnIndex,
                           Integer value)   {
        //Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
    }
    private void addNumber(Row row, int columnIndex,
                           double value)    {
        //Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
    }

    private void addLabel(Row row, int columnIndex, String value){
        //Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
    }



}
