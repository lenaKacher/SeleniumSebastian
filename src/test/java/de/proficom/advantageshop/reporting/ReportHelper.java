package de.proficom.advantageshop.reporting;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * Help class for the creation of the reports
 */
public class ReportHelper {

    private ReportHelper() {
    }

    /**
     * Creates a table in the report.
     *
     * @param pInfos - Two dimensional Array
     */
    public static void createTable(String[][] pInfos) {
        if (pInfos != null && pInfos.length != 0) {
            ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createTable(pInfos));
        }
    }

    /**
     * Creates a simple Info entry in the Report.
     *
     * @param pInfoName   - Label der Info
     * @param pInfoDetail - Info
     */
    public static void createInfoLog(String pInfoName, String pInfoDetail) {
        if (!StringUtils.isEmpty(pInfoDetail)) {
            String aLogEintrag = pInfoName + " : " + pInfoDetail;
            ExtentTestManager.getTest().log(Status.INFO, aLogEintrag);
        }
    }

    /**
     * Creates a block in the report
     *
     * @param pBlockName     - Name of the Block
     * @param pBlockContents - Contents
     */
    public static void createCodeBlock(String pBlockName, String pBlockContents) {
        if (!StringUtils.isEmpty(pBlockContents)) {
            String aLogEintrag = "=== " + pBlockName + " ===\n\n" + pBlockContents;
            ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(aLogEintrag));
        }
    }

    /**
     * Adds to the report a screenshot
     *
     * @param pScreenshotName - Name
     * @param pBase64String   - Screenshot as Base64 String
     */
    public static void addScreenshot(String pScreenshotName, String pBase64String) {
        if (!StringUtils.isEmpty(pBase64String)) {
            String aHtmlImage =
                    "<a href=\"data:image/png;base64," + pBase64String + "\" data-featherlight=\"image\"> " +
                            "<img src=\"data:image/png;base64," + pBase64String + "\" width=\"50%\" height=\"45%\" id=\"" + pScreenshotName + "\" />" +
                            "</a>";

            ExtentTestManager.getTest().log(Status.INFO, aHtmlImage);
        }
    }
}