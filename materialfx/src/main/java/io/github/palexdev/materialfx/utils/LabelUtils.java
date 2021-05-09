/*
 *     Copyright (C) 2021 Parisi Alessandro
 *     This file is part of MaterialFX (https://github.com/palexdev/MaterialFX).
 *
 *     MaterialFX is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     MaterialFX is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with MaterialFX.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.palexdev.materialfx.utils;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Utils class for JavaFX's {@code Label}s.
 */
public class LabelUtils {

    private LabelUtils() {
    }

    /**
     * Checks if the text of the specified {@code Label} is truncated.
     *
     * @param label The specified label
     */
    public static boolean isLabelTruncated(Label label) {
        String originalString = label.getText();
        Text textNode = (Text) label.lookup(".text");
        if (textNode != null) {
            String actualString = textNode.getText();
            return (!actualString.isEmpty() && !originalString.equals(actualString));
        }
        return false;
    }

    /**
     * Registers a listener to the specified {@code Label} which checks if the text
     * is truncated and updates the specified boolean property accordingly.
     *
     * @param isTruncated The boolean property to change
     * @param label       The specified label
     */
    public static void registerTruncatedLabelListener(BooleanProperty isTruncated, Label label) {
        label.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
            String originalString = label.getText();
            Text textNode = (Text) label.lookup(".text");
            String actualString = textNode.getText();

            isTruncated.set(!actualString.isEmpty() && !originalString.equals(actualString));
        });
    }

    /**
     * Computes the min width of a label to show all the text. Uses {@link NodeUtils#getNodeWidth(Region)}.
     *
     * @param font the label font
     * @param text the label text
     */
    public static double computeLabelWidth(Font font, String text) {
        Label helper = new Label(text);
        helper.setMaxWidth(Double.MAX_VALUE);
        helper.setFont(font);

        return NodeUtils.getNodeWidth(helper);
    }

    public static double computeLabelHeight(Font font, String text) {
        Label helper = new Label(text);
        helper.setMaxWidth(Double.MAX_VALUE);
        helper.setFont(font);
        return NodeUtils.getNodeHeight(helper);
    }

    public static double computeTextWidth(Font font, String text) {
        Text helper = new Text(text);
        helper.setFont(font);

        Group group = new Group(helper);
        Scene scene = new Scene(group);
        group.applyCss();
        group.layout();
        return helper.getLayoutBounds().getWidth();
    }

    public static double computeTextHeight(Font font, String text) {
        Text helper = new Text(text);
        helper.setFont(font);

        Group group = new Group(helper);
        Scene scene = new Scene(group);
        group.applyCss();
        group.layout();
        return helper.getLayoutBounds().getHeight();
    }
}
