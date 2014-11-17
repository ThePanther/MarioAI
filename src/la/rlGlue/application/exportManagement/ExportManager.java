package la.rlGlue.application.exportManagement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import context.ManagerFactory;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import la.rlGlue.common.Reward;
import la.rlGlue.common.RewardsGroup;
import la.rlGlue.common.Try;
import la.rlGlue.persistence.database.Database;

public class ExportManager {

	private String path;
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
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
		workbook.createSheet("Statistik", 0);
		WritableSheet excelSheet = workbook.getSheet(0);

		createLabel(excelSheet);
		createContent(excelSheet);

		workbook.write();
		workbook.close();
	}

	private void readDataFromDB() {
		RewardsGroup currentRewardGroup = db.getLastRewardsGroup();
		rewards = currentRewardGroup.getRewards();
		tries = db.getTries(currentRewardGroup);
		rewardGroupID = currentRewardGroup.getId();
		// TODO: aus DB lesen
		tryCount = tries.size();
		for (int i = 0; i < tryCount; i++) {
			Try t = tries.get(i);
			wins = wins + t.getWin();
			rewardSum = rewardSum + t.getRewards();
			steps = steps + t.getSteps();
		}
		rewardDiff = rewardSum / tryCount;

		System.out.println("CSV DATEI: \nREWARD GROUP: "
				+ currentRewardGroup.getId() + "\n");
		for (int i = 0; i < rewards.size(); i++) {
			Reward r = rewards.get(i);
			System.out.println("R " + r.getName() + ": " + r.getReward());
		}
		System.out.println("Anzahl Versuche: " + tryCount + "\n"
				+ "Anzahl Siege: " + wins + "\n" + "Anzahl Steps: " + steps
				+ "\n" + "Durchschnittliche Rewards: " + rewardDiff);
	}

	/***************************************************************************************************************/

	private File createFile() {
		String fileName = "/statistic_" + System.currentTimeMillis() + ".csv";
		File file = new File(path + fileName);
		return file;
	}

	private void createContent(WritableSheet sheet) throws WriteException,
			RowsExceededException {
		addNumber(sheet, 0, 1, rewardGroupID);
		for (int i = 0; i < rewards.size(); i++) {
			addNumber(sheet, i + 1, 1, rewards.get(i).getReward());
//			System.out.println("> R=" + rewards.size() + " i=" + i + " :="+ (rewards.size() - i));
		}

		for (int i = 0; i < tries.size(); i++) {
			addCaption(sheet, 0, 4+i, tries.get(i).getId() + "");
			addCaption(sheet, 1, 4+i, tries.get(i).getWin() + "");
			addCaption(sheet, 2, 4+i, tries.get(i).getRewards() + "");
			addCaption(sheet, 3, 4+i, tries.get(i).getSteps() + "");			
		}
		
	}

	private void createLabel(WritableSheet sheet) throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		times = new WritableCellFormat(times10pt);
		times.setWrap(true);

		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setAutosize(true);

		// Die Reward Gruppe
		addCaption(sheet, 0, 0, "RewardsGroup");
		// Alle Rewards
		for (int i = 0; i < rewards.size(); i++) {
			addCaption(sheet, i + 1, 0, rewards.get(i).getName());
		}
		// leere Spalte
		addCaption(sheet, rewards.size() + 1, 0, "");
		addCaption(sheet, 0, 3, "ID");
		addCaption(sheet, 1, 3, "Win");
		addCaption(sheet, 2, 3, "Rewards");
		addCaption(sheet, 3, 3, "Steps");

	}

	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}

	public static void main(String[] args) {
		new ExportManager(".");
	}

}
