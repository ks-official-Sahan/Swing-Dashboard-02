package ewision.sahan.chart.blankchart;

import java.awt.Graphics2D;

/**
 *
 * @author ks.official.sahan
 */
public abstract class BlankPlotChatRender {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);
}
