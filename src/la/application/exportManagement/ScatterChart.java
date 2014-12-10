package la.application.exportManagement;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;

import static la.application.exportManagement.ExportCommon.*;


public class ScatterChart {
    private final Workbook wb;

    public ScatterChart(Workbook wb) {
        this.wb = wb;
    }

    public void createAllRewardsChart(Sheet sheetChart, Sheet sheetValues, int triesSize) {
        Drawing drawing = sheetChart.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(
                ANCHOR_DX1_CHART_ALL_REWARDS,
                ANCHOR_DY1_CHART_ALL_REWARDS,
                ANCHOR_DX2_CHART_ALL_REWARDS,
                ANCHOR_DY2_CHART_ALL_REWARDS,
                ANCHOR_COL1_CHART_ALL_REWARDS,
                ANCHOR_ROW1_CHART_ALL_REWARDS,
                ANCHOR_COL2_CHART_ALL_REWARDS,
                ANCHOR_ROW2_CHART_ALL_REWARDS);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        // All Steps
        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_ALL_STEPS_XS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_STEPS_XS +triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_STEPS_XS,
                        CELL_RANGE_LAST_COL_CHART_ALL_STEPS_XS));
        // All Rewards
        ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_YS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_YS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_YS,
                        CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_YS));
        data.addSeries(xs, ys);
        data.getSeries().get(0).setTitle("All Rewards");

        chart.plot(data, bottomAxis, leftAxis);
    }

    public void createAnyRewardsChart(Sheet sheetChart, Sheet sheetValues, int triesSize) {
        Drawing drawing = sheetChart.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(
                ANCHOR_DX1_CHART_ANY_REWARDS,
                ANCHOR_DY1_CHART_ANY_REWARDS,
                ANCHOR_DX2_CHART_ANY_REWARDS,
                ANCHOR_DY2_CHART_ANY_REWARDS,
                ANCHOR_COL1_CHART_ANY_REWARDS,
                ANCHOR_ROW1_CHART_ANY_REWARDS,
                ANCHOR_COL2_CHART_ANY_REWARDS,
                ANCHOR_ROW2_CHART_ANY_REWARDS);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);


        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        // All Steps
        ChartDataSource<Number> xsSteps = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_ALL_STEPS_XS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_STEPS_XS +triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_STEPS_XS,
                        CELL_RANGE_LAST_COL_CHART_ALL_STEPS_XS));
        // WINS
        ChartDataSource<Number> ysWins = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_WINS,
                        CELL_RANGE_LAST_ROW_CHART_YS_WINS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_WINS,
                        CELL_RANGE_LAST_COL_CHART_YS_WINS));
        // DEATH
        ChartDataSource<Number> ysDeath = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_DEATH,
                        CELL_RANGE_LAST_ROW_CHART_YS_DEATH+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_DEATH,
                        CELL_RANGE_LAST_COL_CHART_YS_DEATH));
        // HURT
        ChartDataSource<Number> ysHurt = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_HURT,
                        CELL_RANGE_LAST_ROW_CHART_YS_HURT+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_HURT,
                        CELL_RANGE_LAST_COL_CHART_YS_HURT));
        // KILL
        ChartDataSource<Number> ysKill = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_KILL,
                        CELL_RANGE_LAST_ROW_CHART_YS_KILL+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_KILL,
                        CELL_RANGE_LAST_COL_CHART_YS_KILL));
        // EFRAME
        ChartDataSource<Number> ysEFrame = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_EFRAME,
                        CELL_RANGE_LAST_ROW_CHART_YS_EFRAME+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_EFRAME,
                        CELL_RANGE_LAST_COL_CHART_YS_EFRAME));
        // RIGHT
        ChartDataSource<Number> ysRight = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_RIGHT,
                        CELL_RANGE_LAST_ROW_CHART_YS_RIGHT+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_RIGHT,
                        CELL_RANGE_LAST_COL_CHART_YS_RIGHT));
        // LEFT
        ChartDataSource<Number> ysLeft = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_LEFT,
                        CELL_RANGE_LAST_ROW_CHART_YS_LEFT+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_LEFT,
                        CELL_RANGE_LAST_COL_CHART_YS_LEFT));
        // UP
        ChartDataSource<Number> ysUp = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_UP,
                        CELL_RANGE_LAST_ROW_CHART_YS_UP+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_UP,
                        CELL_RANGE_LAST_COL_CHART_YS_UP));
        // DOWN
        ChartDataSource<Number> ysDown = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_YS_DOWN,
                        CELL_RANGE_LAST_ROW_CHART_YS_DOWN+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_YS_DOWN,
                        CELL_RANGE_LAST_COL_CHART_YS_DOWN));


        data.addSeries(xsSteps, ysWins);
        data.addSeries(xsSteps, ysDeath);
        data.addSeries(xsSteps, ysHurt);
        data.addSeries(xsSteps, ysKill);
        data.addSeries(xsSteps, ysEFrame);
        data.addSeries(xsSteps, ysRight);
        data.addSeries(xsSteps, ysLeft);
        data.addSeries(xsSteps, ysUp);
        data.addSeries(xsSteps, ysDown);
        data.getSeries().get(0).setTitle("Wins");
        data.getSeries().get(1).setTitle("Death");
        data.getSeries().get(2).setTitle("Hurt");
        data.getSeries().get(3).setTitle("Kill");
        data.getSeries().get(4).setTitle("EFrame");
        data.getSeries().get(5).setTitle("Right");
        data.getSeries().get(6).setTitle("Left");
        data.getSeries().get(7).setTitle("Up");
        data.getSeries().get(8).setTitle("Down");

        chart.plot(data, bottomAxis, leftAxis);
    }

    public void createRewardStepsChart(Sheet sheetRewardStep, int triesSize) {
        Drawing drawing = sheetRewardStep.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(
                ANCHOR_DX1_CHART_REWARD_STEPS,
                ANCHOR_DY1_CHART_REWARD_STEPS,
                ANCHOR_DX2_CHART_REWARD_STEPS,
                ANCHOR_DY2_CHART_REWARD_STEPS,
                ANCHOR_COL1_CHART_REWARD_STEPS,
                ANCHOR_ROW1_CHART_REWARD_STEPS,
                ANCHOR_COL2_CHART_REWARD_STEPS,
                ANCHOR_ROW2_CHART_REWARD_STEPS);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        // All Steps
        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheetRewardStep,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_REWARD_STEPS_XS,
                        CELL_RANGE_LAST_ROW_CHART_REWARD_STEPS_XS +triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_REWARD_STEPS_XS,
                        CELL_RANGE_LAST_COL_CHART_REWARD_STEPS_XS));
        // All Rewards
        ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheetRewardStep,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_REWARD_STEPS_YS,
                        CELL_RANGE_LAST_ROW_CHART_REWARD_STEPS_YS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_REWARD_STEPS_YS,
                        CELL_RANGE_LAST_COL_CHART_REWARD_STEPS_YS));
        data.addSeries(xs, ys);
        data.getSeries().get(0).setTitle("Rewards");

        chart.plot(data, bottomAxis, leftAxis);
    }
}
