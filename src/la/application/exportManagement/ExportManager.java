package la.application.exportManagement;

import context.ManagerFactory;
import la.common.Reward;
import la.common.RewardsGroup;
import la.common.Try;
import la.persistence.database.Database;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static la.application.exportManagement.ExportCommon.*;


public class ExportManager {

    private String path = PATH;

	private Database db = ManagerFactory.getManager(Database.class);

	private int rewardGroupID;
	private List<Reward> rewards;
	private int tryCount;
	private double rewardDiff;
	private int wins = 0;
	private double rewardSum = 0;
	private int steps = 0;
	private List<Try> tries;

	public ExportManager(String path) {

        if (path.isEmpty())
            this.path = PATH;
        else
            this.path = path;

		try {
			createFile();
		} catch (IOException e){

        }
	}

    public void createFile() throws IOException {
        readDataFromDB();
        Workbook wb = new XSSFWorkbook();
        Sheet sheetValues = wb.createSheet(SHEET_VALUES);
        Sheet sheetChart = wb.createSheet(SHEET_CHART);

        SheetFormater sheetFormater = new SheetFormater(wb);
        sheetFormater.createLabelValues(sheetValues, rewards);
        sheetFormater.createContentValues(sheetValues, rewardGroupID, rewards, tries);
        sheetFormater.createLabelStatistics(sheetValues);
        sheetFormater.createContentStatistics(sheetValues, tries.size());

        ScatterChart chart = new ScatterChart(wb);
        chart.createAllRewardsChart(sheetChart, sheetValues, tries.size());
        chart.createAnyRewardsChart(sheetChart, sheetValues, tries.size());

        FileOutputStream fileOut = new FileOutputStream(createFileName());
        wb.write(fileOut);
        fileOut.close();
    }


	/***************************************************************************************************************/

    private String createFileName(){
        return path + FILENAME_HEAD + System.currentTimeMillis() + FILENAME_ENDING;
    }

    private void readDataFromDB() {
        RewardsGroup currentRewardGroup = db.getLastRewardsGroup();
        rewards = currentRewardGroup.getRewards();
        tries = db.getTries(currentRewardGroup);
        rewardGroupID = currentRewardGroup.getId();
        tryCount = tries.size();
        for (int i = 0; i < tryCount; i++) {
            Try t = tries.get(i);
            wins = wins + t.getWin();
            rewardSum = rewardSum + t.getRewards();
            steps = steps + t.getSteps();
        }
        rewardDiff = rewardSum / tryCount;

    }

	public static void main(String[] args) {
        new ExportManager("");
	}

}
