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
                        CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_XS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_XS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_XS,
                        CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_XS));
        // All Rewards
        ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_YS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_YS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_YS,
                        CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_YS));
        data.addSeries(xs, ys);

        chart.plot(data, bottomAxis, leftAxis);
    }

    public void createAllRewardsChart2(Sheet sheetChart, Sheet sheetValues, int triesSize) {
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
                        CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_XS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_XS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_XS,
                        CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_XS));
        // All Rewards
        ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheetValues,
                new CellRangeAddress(
                        CELL_RANGE_FIRST_ROW_CHART_ALL_REWARDS_YS,
                        CELL_RANGE_LAST_ROW_CHART_ALL_REWARDS_YS+triesSize-1,
                        CELL_RANGE_FIRST_COL_CHART_ALL_REWARDS_YS,
                        CELL_RANGE_LAST_COL_CHART_ALL_REWARDS_YS));
        data.addSeries(xs, ys);

        chart.plot(data, bottomAxis, leftAxis);
    }
}
