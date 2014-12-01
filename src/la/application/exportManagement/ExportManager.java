package la.application.exportManagement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import context.ManagerFactory;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import la.common.Reward;
import la.common.RewardsGroup;
import la.common.Try;
import la.persistence.database.Database;

public class ExportManager {
    private final String PATH = "statistik";
    private final String FILENAME_HEAD = "/statistic_";
    private final String FILENAME_ENDING = ".csv";
    private final String SHEET_VALUES = "Werte";
    private final String SHEET_STATISTICS = "Statistik";

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
			createCSVFile();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public void createCSVFile() throws IOException, WriteException {
		File file = createFile();
		readDataFromDB();

		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("de", "DE"));
		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet(SHEET_VALUES, 0);
        workbook.createSheet(SHEET_STATISTICS, 1);
        WritableSheet sheetValues = workbook.getSheet(0);
        //WritableSheet sheetStatistics = workbook.getSheet(1);

        CSVSheetFormat csvSheetFormat = new CSVSheetFormat();
        csvSheetFormat.createLabelValues(sheetValues,rewards);
        csvSheetFormat.createContentValues(sheetValues,rewardGroupID,rewards,tries);
        csvSheetFormat.createLabelStatistics(sheetValues);
        csvSheetFormat.createContentStatistics(sheetValues, tries.size());

		workbook.write();
		workbook.close();
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

        /* // AUSGABE
		System.out.println("CSV DATEI: \nREWARD GROUP: "+ currentRewardGroup.getId() + "\n");
		for (int i = 0; i < rewards.size(); i++) {
			Reward r = rewards.get(i);
			System.out.println("R " + r.getName() + ": " + r.getReward());
		}
		System.out.println("Anzahl Versuche: " + tryCount + "\n"+ "Anzahl Siege: " + wins + "\n" + "Anzahl Steps: " + steps+ "\n" + "Durchschnittliche Rewards: " + rewardDiff);
		*/
	}

	/***************************************************************************************************************/

	private File createFile() {
		String fileName = FILENAME_HEAD + System.currentTimeMillis() + FILENAME_ENDING;

		File file = new File(path + fileName);
		return file;
	}



	public static void main(String[] args) {

        new ExportManager("");
	}

}
