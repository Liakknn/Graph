package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

    private Function function;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
    private float stepX;
    private float stepY;

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        Point start;
        Point end;
        //отрисовка сетки
        g.setColor(Color.GRAY);
        for (float x = minX; x <= maxX; x += stepX) {
            start = convert(x, minY);
            end = convert(x, maxY);
            g.setColor(Color.GRAY);
            g.drawLine(start.x, start.y, end.x, end.y);
        }
        g.setColor(Color.GRAY);
        for (float y = minY; y <= maxY; y += stepX) {
            start = convert(minX, y);
            end = convert(maxX, y);
            g.setColor(Color.GRAY);
            g.drawLine(start.x, start.y, end.x, end.y);
        }
        //отрисовка осей
        g.setColor(Color.BLACK);
        start = convert(minX, 0);
        end = convert(maxX, 0);
        g.drawLine(start.x, start.y, end.x, end.y);
        start = convert(0, minY);
        end = convert(0, maxY);
        g.drawLine(start.x, start.y, end.x, end.y);
        //отрисовка подписей
        g.setColor(Color.BLACK);
        for (float x = minX; x <= maxX; x += stepX) {
            start = convert(x, 0);
            g.drawString(Math.round(x) + "", start.x, start.y);
        }
        g.setColor(Color.BLACK);
        for (float y = minY; y <= maxY; y += stepY) {
            start = convert(0, y);
            g.drawString(Math.round(y) + "", start.x, start.y);
        }
        //отрисовка графика
        if (function != null) {
            g.setColor(Color.MAGENTA);
            for (int x = 0; x < getSize().width - 1; x++) {
                float userY1 = function.getValue(convertToUser(x));
                float userY2 = function.getValue(convertToUser(x + 1));
                start = convert(0, userY1);
                end = convert(0, userY2);
                if (Math.abs(end.y - start.y) < getSize().height) {
                    g.drawLine(x, start.y, x + 1, end.y);
                }
            }
        }

    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public float getMinX() {
        return minX;
    }

    public void setMinX(float minX) {
        this.minX = minX;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    public float getStepX() {
        return stepX;
    }

    public void setStepX(float stepX) {
        this.stepX = stepX;
    }

    public float getStepY() {
        return stepY;
    }

    public void setStepY(float stepY) {
        this.stepY = stepY;
    }

    private Point convert(float x, float y) {
        float h = getSize().height;
        float w = getSize().width;
        float m = w * (x - minX) / (maxX - minX); //координата по x
        float n = h * (maxY - y) / (maxY - minY); // координата по y
        return new Point((int) m, (int) n);

    }

    private float convertToUser(int x) {
        return (maxX - minX) * x / getSize().width;
    }

}
